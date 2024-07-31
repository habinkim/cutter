package io.stockfolio.cutter.task.application.service;

import io.stockfolio.cutter.common.stereotype.UseCase;
import io.stockfolio.cutter.task.application.port.input.QueueTrimTaskUseCase;
import io.stockfolio.cutter.task.application.port.output.AppendTrimTaskPort;
import io.stockfolio.cutter.task.domain.behavior.TrimRequest;
import lombok.RequiredArgsConstructor;

/**
 * Task를 적재하고 실행하는 서비스
 */
@UseCase
@RequiredArgsConstructor
public class TaskService implements QueueTrimTaskUseCase {

    private final AppendTrimTaskPort appendTrimTaskPort;


    @Override
    public void queueTrimTask(final TrimRequest request) {

        appendTrimTaskPort.appendTrimTask(request);

    }

}
