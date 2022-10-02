package com.mypfinance.budgettrackersvc.models.mapper;

import com.mypfinance.budgettrackersvc.models.domain.Account;
import com.mypfinance.budgettrackersvc.models.dto.AccountDto;
import com.mypfinance.budgettrackersvc.models.dto.AccountResponse;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AccountMapper {

    public Account mapAccountDtoToAccount(AccountDto dto) {

        return Account.builder()
                .id(UUID.randomUUID().toString())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .balance(dto.getBalance())
                .registrationDate(dto.getRegistrationDate())
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
