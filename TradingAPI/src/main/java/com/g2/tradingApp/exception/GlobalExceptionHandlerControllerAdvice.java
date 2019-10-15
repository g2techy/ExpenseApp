package com.g2.tradingApp.exception;

import com.g2.tradingApp.api.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandlerControllerAdvice {

    @ExceptionHandler({RuntimeException.class, BusinessException.class})
    public ResponseEntity<ApiResponse> handleRuntimeException(RuntimeException ex){
        ApiResponse apiResponse = ApiResponse.getErrorResponse(ex);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

}
