package io.github.enzopavani.orcat.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.util.List;

public record ErrorResponse(Integer status, String msg, List<FieldError> errors) {

    public static ErrorResponse defaultResponse(String msg) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), msg, List.of());
    }

    public static ErrorResponse conflict(String msg) {
        return new ErrorResponse(HttpStatus.CONFLICT.value(), msg, List.of());
    }
}
