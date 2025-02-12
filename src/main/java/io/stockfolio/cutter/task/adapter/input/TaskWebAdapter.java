package io.stockfolio.cutter.task.adapter.input;

import io.stockfolio.cutter.common.config.Uris;
import io.stockfolio.cutter.common.response.Response;
import io.stockfolio.cutter.common.response.ResponseMapper;
import io.stockfolio.cutter.common.stereotype.WebAdapter;
import io.stockfolio.cutter.task.application.port.input.QueueTrimTaskUseCase;
import io.stockfolio.cutter.task.domain.behavior.TrimRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@WebAdapter
@RequiredArgsConstructor
public class TaskWebAdapter {

    private final QueueTrimTaskUseCase queueTrimTaskUseCase;

    private final ResponseMapper responseMapper;


    @PostMapping(value = Uris.TASK_TRIM_V1)
    public ResponseEntity<Response<?>> trim(@Valid @RequestBody final TrimRequest request) {
        queueTrimTaskUseCase.queueTrimTask(request);
        return responseMapper.ok();
    }


}
