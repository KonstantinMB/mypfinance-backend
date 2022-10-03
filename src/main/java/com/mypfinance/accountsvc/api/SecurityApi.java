package com.mypfinance.accountsvc.api;

import com.mypfinance.exception.ResourceNotFoundException;
import com.mypfinance.accountsvc.models.dto.AccountDto;
import com.mypfinance.accountsvc.models.dto.AccountResponse;
import com.mypfinance.accountsvc.models.AccountMapper;
import com.mypfinance.accountsvc.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1/account-svc")
public class SecurityApi {

    private final AccountService service;

    private final AccountMapper mapper;


    public SecurityApi(AccountService accountService, AccountMapper mapper) {
        this.service = accountService;
        this.mapper = mapper;
    }

    @PostMapping(value = "/registration", produces = APPLICATION_JSON_VALUE , consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountResponse> registerAccount(@RequestBody AccountDto request)
            throws ResourceNotFoundException {

        return null;
    }
}
