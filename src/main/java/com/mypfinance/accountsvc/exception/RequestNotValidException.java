package com.mypfinance.accountsvc.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class RequestNotValidException extends Exception {

    private final String fieldName;

    private final String message;

    private final HttpStatus status;

    public RequestNotValidException(String fieldName, String message, HttpStatus status) {

        this.fieldName = fieldName;
        this.message = message;
        this.status = status;
    }

}
