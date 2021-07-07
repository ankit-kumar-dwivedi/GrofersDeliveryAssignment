package com.deliveryScheduler.demo.exception;

import com.deliveryScheduler.demo.dto.Response;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Log4j2
@ControllerAdvice
public class RestExceptionResolver extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { ApiException.class })
    protected ResponseEntity<Response<?>> handleKnownException(ApiException e, WebRequest request) {
        log.warn(e.getMessage(), e);
        return new ResponseEntity<>(new Response<>(e.getMessage(), false, null), HttpStatus.OK);
    }

    @ExceptionHandler(value = { Exception.class })
    protected ResponseEntity<Response<?>> handleUnknownException(Exception e, WebRequest request) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new Response<>(e.getMessage(), false, null), HttpStatus.OK);
    }

}
