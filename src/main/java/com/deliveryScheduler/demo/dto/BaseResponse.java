package com.deliveryScheduler.demo.dto;

import org.springframework.http.HttpStatus;

import java.time.Instant;

/**
 * @author Ankit Dwivedi
 * @version 1.0, 06/07/21
 **/

public abstract class BaseResponse {

    private String message;

    private String errorCode;

    private boolean success;

    private Long serverTime = Instant.now().getEpochSecond();

    public BaseResponse() {
        this.success = true;
        this.message = HttpStatus.OK.name();
    }

    public BaseResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public BaseResponse(String message, String errorCode, boolean success) {
        this.message = message;
        this.errorCode = errorCode;
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}

