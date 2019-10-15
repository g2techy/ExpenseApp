package com.g2.tradingApp.api;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ApiResponse {

    private static final Integer STATUS_CODE_SUCCESS = 200;
    private static final Integer STATUS_CODE_FAILED = 400;
    private static final Integer STATUS_CODE_ERROR = 500;

    private Integer statusCode;

    private String message;

    private Object data;

    private List<String> errors;

    protected ApiResponse(Integer statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    protected ApiResponse(Integer statusCode, String message, Object data) {
        this(statusCode, message);
        this.data = data;
    }

    protected ApiResponse(Integer statusCode, String message, Object data, List<String> errors) {
        this(statusCode, message, data);
        this.errors = errors;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public List<String> getErrors() {
        return errors;
    }

    public static ApiResponse getSuccessResponse(final Object data) {
        return getSuccessResponse("", data);
    }

    public static ApiResponse getSuccessResponse(final String message, final Object data) {
        return createApiResponse(STATUS_CODE_SUCCESS, message, data, null);
    }

    public static ApiResponse getFailedResponse(final String message) {
        return createApiResponse(STATUS_CODE_FAILED, message, null, null);
    }

    public static ApiResponse getErrorResponse(final String message) {
        return createApiResponse(STATUS_CODE_ERROR, message, null, null);
    }

    public static ApiResponse getErrorResponse(final Exception e) {
        List<String> errors = Arrays.asList(e.getLocalizedMessage());
        return createApiResponse(STATUS_CODE_ERROR, e.getMessage(), null, errors);
    }

    private static ApiResponse createApiResponse(Integer statusCode, String message, Object data, List<String> errors) {
        ApiResponse apiResponse = new ApiResponse(statusCode, message, data, errors);
        return apiResponse;
    }

}
