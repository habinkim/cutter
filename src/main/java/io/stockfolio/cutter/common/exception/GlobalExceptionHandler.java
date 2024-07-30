package io.stockfolio.cutter.common.exception;

import io.stockfolio.cutter.common.response.ExceptionResponse;
import io.stockfolio.cutter.common.response.ResponseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static io.stockfolio.cutter.common.constant.Constants.DOT;
import static io.stockfolio.cutter.common.response.MessageCode.ERROR;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final ResponseMapper responseMapper;

    protected static String getPropertyName(String propertyPath) {
        return propertyPath.substring(propertyPath.lastIndexOf(DOT) + 1);
    }

    @ExceptionHandler(CommonApplicationException.class)
    protected ResponseEntity<ExceptionResponse> commonApplicationException(CommonApplicationException e) {
        return e.getMessageCode() == null ?
                responseMapper.error(ERROR) : responseMapper.error(e.getMessageCode());
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ExceptionResponse> exception(Exception e) {
        log.error("Unrecognised Exception", e);
        return responseMapper.error(ERROR);
    }

}
