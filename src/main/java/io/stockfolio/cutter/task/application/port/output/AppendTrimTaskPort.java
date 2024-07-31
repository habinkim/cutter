package io.stockfolio.cutter.task.application.port.output;

import io.stockfolio.cutter.task.domain.behavior.TrimRequest;

public interface AppendTrimTaskPort {

    void appendTrimTask(final TrimRequest request);

}
