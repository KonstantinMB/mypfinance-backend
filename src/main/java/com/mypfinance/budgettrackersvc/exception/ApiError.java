package com.mypfinance.budgettrackersvc.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {

    private final LocalDateTime timestamp = LocalDateTime.now();
    private int httpStatus;
    private String error;
    private String message;
    private String field;

    public static RestResponseBuilder builder(){
        return new RestResponseBuilder();
    }

}
