package com.mypfinance.budgettrackersvc.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ResourceNotFoundException extends Exception {

    private final String message;
    private final HttpStatus status;

    public ResourceNotFoundException(String message, HttpStatus status) {

        this.message = message;
        this.status = status;
    }

}
