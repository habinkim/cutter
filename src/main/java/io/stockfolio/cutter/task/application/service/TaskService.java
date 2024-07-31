package io.stockfolio.cutter.task.application.service;

import io.stockfolio.cutter.common.exception.CommonApplicationException;
import io.stockfolio.cutter.common.stereotype.UseCase;
import io.stockfolio.cutter.resource.application.port.output.GetResourcePort;
import io.stockfolio.cutter.resource.domain.value.SavedResource;
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

    private final GetResourcePort getResourcePort;
    private final AppendTrimTaskPort appendTrimTaskPort;


    @Override
    public void queueTrimTask(final TrimRequest request) {
        SavedResource savedResource = getResourcePort.getResource(request.ulid())
                .orElseThrow(CommonApplicationException.RESOURCE_NOT_FOUND);
        validateTrimRange(request, savedResource);

        appendTrimTaskPort.appendTrimTask(request);
    }

    private static void validateTrimRange(final TrimRequest request, final SavedResource savedResource) {
        if (request.trimStart() > savedResource.duration() || request.trimEnd() > savedResource.duration())
            throw CommonApplicationException.TRIM_RANGE_INVALIDATE.get();
    }

}
