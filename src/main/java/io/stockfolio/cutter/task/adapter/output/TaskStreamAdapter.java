package io.stockfolio.cutter.task.adapter.output;

import io.stockfolio.cutter.task.application.port.output.AppendTrimTaskPort;
import io.stockfolio.cutter.task.domain.behavior.TrimRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStreamCommands;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.connection.stream.StreamInfo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Iterator;

/**
 * @see <a href="https://kingjakeu.github.io/study/2022/02/07/about-redis-stream/">Redis Stream (레디스 스트림) 기본 정리</a>
 * @see <a href="https://docs.spring.io/spring-data/redis/reference/redis/redis-streams.html">Spring Data Redis Streams</a>
 * @see <a href="https://howtodoinjava.com/spring-data/redis-streams-processing/">Spring Data Redis Streams Processing</a>
 */
@Component
@RequiredArgsConstructor
public class TaskStreamAdapter implements AppendTrimTaskPort {

    private final RedisConnectionFactory redisConnectionFactory;
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public void appendTrimTask(TrimRequest request) {

    }

    private void createStreamConsumerGroup(final String streamKey, final String consumerGroupName) {
        // Stream이 존재 하지 않으면, MKSTREAM 옵션을 통해 만들고, ConsumerGroup또한 생성한다
        if (Boolean.FALSE.equals(redisTemplate.hasKey(streamKey))) {

            RedisStreamCommands streamCommands = redisConnectionFactory.getConnection().streamCommands();

            streamCommands.xGroupCreate(streamKey.getBytes(), consumerGroupName, ReadOffset.from("0"), true);
        }

        // Stream 존재시, ConsumerGroup 존재 여부 확인 후 ConsumerGroupd을 생성한다
        else {
            if (!isStreamConsumerGroupExist(streamKey, consumerGroupName))
                createConsumerGroup(streamKey, consumerGroupName);
        }
    }

    private String createConsumerGroup(final String streamKey, final String consumerGroupName) {
        return redisTemplate.opsForStream()
                .createGroup(streamKey, ReadOffset.from("0"), consumerGroupName);
    }

    /**
     * ConsumerGroup 존재 여부 확인
     *
     * @param streamKey         stream key
     * @param consumerGroupName consumer group name
     * @return 존재 여부
     */
    public boolean isStreamConsumerGroupExist(final String streamKey, final String consumerGroupName) {
        Iterator<StreamInfo.XInfoGroup> iterator = redisTemplate.opsForStream()
                .groups(streamKey).stream().iterator();

        while (iterator.hasNext()) {
            StreamInfo.XInfoGroup xInfoGroup = iterator.next();
            if (xInfoGroup.groupName().equals(consumerGroupName)) return true;
        }

        return false;
    }

}
