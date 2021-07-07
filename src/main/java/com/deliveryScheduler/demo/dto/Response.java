package com.deliveryScheduler.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Ankit Dwivedi
 * @version 1.0, 06/07/21
 **/

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> extends BaseResponse {

    private T data;

    public Response(T data) {
        this.data = data;
    }

    public Response() {
        super();
    }

    public Response(boolean success, String code, String message, T data) {
        super(message, code, success);
        this.data = data;
    }

    public Response(boolean success, String message) {
        super(message, success);
    }

    public Response(boolean success, String message, T data) {
        super(message, success);
        this.data = data;
    }

    public Response(String errorCode, boolean success, T data) {
        super(errorCode, success);
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
