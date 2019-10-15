package com.g2.tradingApp.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BusinessException extends RuntimeException {

    private static Logger LOGGER = LoggerFactory.getLogger(BusinessException.class);

    public BusinessException() {
        super();
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
        LOGGER.error("Message: " + message);
        LOGGER.error("StackTrace: " + cause.getCause());
    }

    public BusinessException(String message) {
        super(message);
        LOGGER.error("Message: " + message);
    }

    public BusinessException(Throwable cause) {
        super(cause);
        LOGGER.error("StackTrace: " + cause.getCause());
    }

}
