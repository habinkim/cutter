package io.stockfolio.cutter;

import io.stockfolio.cutter.common.response.BasePayload;
import io.stockfolio.cutter.common.response.MessageCode;
import io.stockfolio.cutter.common.response.Response;
import io.stockfolio.cutter.common.response.ResponseMapper;
import io.stockfolio.cutter.common.stereotype.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@WebAdapter
@RequiredArgsConstructor
public class TestController {

    private final ResponseMapper responseMapper;

    @GetMapping("/test")
    public ResponseEntity<Response<BasePayload>> test() {
        return responseMapper.ok(MessageCode.SUCCESS);
    }

}
