package com.mypfinance.budgettrackersvc.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

//    TODO: Move to account-svc
    @ExceptionHandler(ResponseStatusException.class)
    ResponseEntity<?> processResponseStatusException(ResponseStatusException exception, WebRequest request) {

        return ApiError.builder()
                .exception(exception)
                .path(request.getDescription(false).substring(4))
                .message(exception.getMessage())
                .status(BAD_REQUEST)
                .entity();
    }

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
