package com.deliveryScheduler.demo.exception;

public class ApiException extends RuntimeException {

    private static final long serialVersionUID = -7282914106678361114L;

    private String            reasonCode;

    public ApiException(String message) {
        super(message);
    }

    public ApiException(String reasonCode, String message) {
        super(message);
        this.reasonCode = reasonCode;
    }

    public String getReasonCode() {
        return reasonCode;
    }

}
