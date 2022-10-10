package com.mypfinance.accountsvc.models.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Data
public class AccountDto {

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String email;

    private BigDecimal balance;

}
