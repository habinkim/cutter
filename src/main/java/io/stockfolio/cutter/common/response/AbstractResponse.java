package io.stockfolio.cutter.common.response;

public sealed interface AbstractResponse permits Response, ExceptionResponse {

    String getMessage();

    String getCode();

}
