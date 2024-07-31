package io.stockfolio.cutter.task.adapter.input;

import io.stockfolio.cutter.common.response.ResponseMapper;
import io.stockfolio.cutter.common.stereotype.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;

@WebAdapter
@RequiredArgsConstructor
public class TaskWebAdapter {

    private final ResponseMapper responseMapper;


}
