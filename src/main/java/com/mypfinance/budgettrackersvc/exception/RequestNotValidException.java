package com.mypfinance.budgettrackersvc.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class RequestNotValidException extends Exception {

    private final String fieldName;

    private final HttpStatus status;

    public RequestNotValidException(String fieldName, String message, HttpStatus status) {

        super(message);
        this.fieldName = fieldName;
        this.status = status;
    }

}
