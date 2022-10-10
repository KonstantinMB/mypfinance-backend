package com.mypfinance.accountsvc.models.mapper;

import com.mypfinance.accountsvc.models.domain.Account;
import com.mypfinance.accountsvc.models.dto.AccountDto;
import com.mypfinance.accountsvc.models.dto.AccountResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class AccountMapper {

    private final BCryptPasswordEncoder passwordEncoder;

    public AccountMapper(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    public Account mapAccountDtoToAccount(AccountDto dto) {

        LocalDateTime registrationDate = LocalDateTime.now(Clock.systemUTC());
        return Account.builder()
                .id(UUID.randomUUID().toString())
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .balance(dto.getBalance())
                .registrationDate(registrationDate)
                .build();
    }

    public AccountResponse mapAccountToAccountResponse(Account domain) {

        return AccountResponse.builder()
                .username(domain.getUsername())
                .firstName(domain.getFirstName())
                .lastName(domain.getLastName())
                .email(domain.getEmail())
                .balance(domain.getBalance())
                .build();
    }
}
