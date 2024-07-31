package io.stockfolio.cutter.common.response;

import io.stockfolio.cutter.common.util.i18n.MessageSourceUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import static io.stockfolio.cutter.common.response.MessageCode.SUCCESS;


@Component
@RequiredArgsConstructor
public class ResponseMapper {

    private final MessageSourceUtil messageSourceUtil;

    public ResponseEntity<Response<?>> ok() {
        return ResponseEntity.ok()
                .body(new Response<>(messageSourceUtil.getMessage(SUCCESS.getCode()), SUCCESS.getCode(), null));
    }

    public <T extends BasePayload> ResponseEntity<Response<T>> ok(final MessageCode messageCode, final T source) {
        return ResponseEntity
                .status(messageCode.getHttpStatus())
                .body(new Response<>(messageSourceUtil.getMessage(messageCode.getCode()), messageCode.getCode(), source));
    }

    public <T extends BasePayload> ResponseEntity<Response<T>> ok(final MessageCode messageCode) {
        return ok(messageCode, null);
    }

    public <T extends BasePayload> ResponseEntity<Response<T>> ok(final T source) {
        return ok(SUCCESS, source);
    }

    public ResponseEntity<ExceptionResponse> error(final MessageCode messageCode) {
        return ResponseEntity
                .status(messageCode.getHttpStatus())
                .body(new ExceptionResponse(messageSourceUtil.getMessage(messageCode.getCode()), messageCode.getCode()));
    }

    public ResponseEntity<ExceptionResponse> error(final MessageCode messageCode, final String additionalMessage) {
        return ResponseEntity
                .status(messageCode.getHttpStatus())
                .body(new ExceptionResponse(
                        messageSourceUtil.getMessage(messageCode.getCode()) + System.lineSeparator() + additionalMessage,
                        messageCode.getCode())
                );
    }

}
