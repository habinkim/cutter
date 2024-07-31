package io.stockfolio.cutter.task.adapter.output;

import io.stockfolio.cutter.task.application.port.output.AppendTrimTaskPort;
import io.stockfolio.cutter.task.domain.behavior.TrimRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskStreamAdapter implements AppendTrimTaskPort {


    @Override
    public void appendTrimTask(TrimRequest request) {

    }

}
