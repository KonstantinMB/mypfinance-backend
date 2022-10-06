package com.mypfinance.accountsvc.api;

import com.mypfinance.exception.ResourceNotFoundException;
import com.mypfinance.accountsvc.models.dto.AccountResponse;
import com.mypfinance.accountsvc.models.AccountMapper;
import com.mypfinance.accountsvc.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1/budget-tracker/account")
public class AccountApi {

    private final AccountService service;

    private final AccountMapper mapper;


    public AccountApi(AccountService accountService, AccountMapper mapper) {
        this.service = accountService;
        this.mapper = mapper;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountResponse> getAccountInfo() throws ResourceNotFoundException {

//        TODO:
        String username = null;

        return ResponseEntity.ok(mapper.mapAccountToAccountResponse(service.getAccountInfo(username)));
    }
}
