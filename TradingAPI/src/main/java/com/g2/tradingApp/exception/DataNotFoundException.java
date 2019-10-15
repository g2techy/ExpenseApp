package com.g2.tradingApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DataNotFoundException extends BusinessException {

    public DataNotFoundException(String message) {
        super(message);
    }

}
