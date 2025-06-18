package com.smo.price.infrastructure.exception.errors;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String messageLog;
    private final String messageResponse;
    private final transient Object additionalData;

    public ApiException(HttpStatus httpStatus, String messageLog, String messageResponse, Object additionalData) {
        super(messageLog);
        this.httpStatus = httpStatus;
        this.errorCode = httpStatus.toString();
        this.messageLog = messageLog;
        this.messageResponse = messageResponse;
        this.additionalData = additionalData;
    }

}