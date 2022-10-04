package com.mypfinance.accountsvc.security;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginCredentials {

    private String username;

    private String password;
}
