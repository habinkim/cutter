package io.stockfolio.cutter.common.exception;

import io.stockfolio.cutter.common.response.ExceptionResponse;
import io.stockfolio.cutter.common.response.ResponseMapper;
import io.stockfolio.cutter.resource.adapter.output.storage.StorageException;
import io.stockfolio.cutter.resource.adapter.output.transcode.TranscodeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static io.stockfolio.cutter.common.response.MessageCode.*;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final ResponseMapper responseMapper;

    @ExceptionHandler(CommonApplicationException.class)
    protected ResponseEntity<ExceptionResponse> commonApplicationException(CommonApplicationException e) {
        return e.getMessageCode() == null ?
                responseMapper.error(ERROR) : responseMapper.error(e.getMessageCode());
    }

    @ExceptionHandler(StorageException.class)
    protected ResponseEntity<ExceptionResponse> storageException(StorageException e) {
        return responseMapper.error(STORAGE_ERROR, e.getMessage());
    }

    @ExceptionHandler(TranscodeException.class)
    protected ResponseEntity<ExceptionResponse> transactionalException(TranscodeException e) {
        return responseMapper.error(TRANSCODING_ERROR, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ExceptionResponse> exception(Exception e) {
        log.error("Unrecognised Exception", e);
        return responseMapper.error(ERROR);
    }

}
