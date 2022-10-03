package com.mypfinance.accountsvc.models.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class AccountResponse {

    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private BigDecimal balance;

}
