package com.assignment.blog.common.api;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ResponseEntity<T> {
    private Integer status = 200;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;
    private T result;

    public static <T> ResponseEntity<T> create(T result) {
        ResponseEntity<T> responseEntity = new ResponseEntity<>();
        responseEntity.result = result;
        return responseEntity;
    }

    public static ResponseEntity<?> createError(String message, Integer status) {
        ResponseEntity<Object> responseEntity = new ResponseEntity<>();
        responseEntity.status = status;
        responseEntity.message = message;
        return responseEntity;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getResult() {
        return result;
    }
}
