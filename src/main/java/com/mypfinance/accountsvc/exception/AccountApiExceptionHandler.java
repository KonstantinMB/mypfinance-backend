package com.mypfinance.accountsvc.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class AccountApiExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(RequestNotValidException.class)
    public ResponseEntity<ApiError> processRequestNotValidException(RequestNotValidException exception) {

        return ApiError.builder()
                .exception(exception)
                .path(exception.getFieldName())
                .message(exception.getMessage())
                .status(BAD_REQUEST)
                .entity();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> processResourceNotFoundException(ResourceNotFoundException exception) {

        return ApiError.builder()
                .exception(exception)
                .message(exception.getMessage())
                .status(BAD_REQUEST)
                .entity();
    }

}
