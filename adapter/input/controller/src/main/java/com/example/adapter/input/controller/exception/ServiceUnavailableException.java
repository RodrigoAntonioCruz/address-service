package com.example.adapter.input.controller.exception;

import org.springframework.http.HttpStatus;

import java.io.Serial;

public class ServiceUnavailableException extends BusinessException {
    @Serial
    private static final long serialVersionUID = 8592567739725516521L;

    public ServiceUnavailableException(String object) {
        super.setHttpStatusCode(HttpStatus.SERVICE_UNAVAILABLE);
        super.setMessage(object);
    }
}