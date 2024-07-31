package io.stockfolio.cutter.resource.adapter.output.transcode;

import io.stockfolio.cutter.common.stereotype.TranscodeAdapter;
import io.stockfolio.cutter.resource.application.port.output.GetResourceDurationPort;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;

import java.io.IOException;

@TranscodeAdapter
@RequiredArgsConstructor
public class ResourceTranscodeAdapter implements GetResourceDurationPort {

    private final FFprobe ffprobe;

    /**
     * @param savedPath 저장된 파일 경로
     * @return resourceDuration (seconds)
     */
    @Override
    public Integer getResourceDuration(@NotBlank final String savedPath) {
        FFmpegProbeResult probeResult;

        try {
            probeResult = ffprobe.probe(savedPath);
        } catch (IOException e) {
            throw new TranscodeException("Failed to probe file", e);
        }

        return (int) Math.round(probeResult.getFormat().duration * 1000);
    }
}
