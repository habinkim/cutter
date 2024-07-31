package io.stockfolio.cutter.task.application.port.input;

import io.stockfolio.cutter.task.domain.behavior.TrimRequest;

@FunctionalInterface
public interface QueueTrimTaskUseCase {
    void queueTrimTask(final TrimRequest request);
}
