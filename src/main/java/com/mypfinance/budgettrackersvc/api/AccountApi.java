package com.mypfinance.budgettrackersvc.api;

import com.mypfinance.budgettrackersvc.exception.ResourceNotFoundException;
import com.mypfinance.budgettrackersvc.models.dto.AccountDto;
import com.mypfinance.budgettrackersvc.models.dto.AccountResponse;
import com.mypfinance.budgettrackersvc.models.dto.TransactionDto;
import com.mypfinance.budgettrackersvc.models.mapper.AccountMapper;
import com.mypfinance.budgettrackersvc.service.AccountService;
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

    @GetMapping(value = "/{accountId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountResponse> getAccountInfo(@PathVariable String accountId) throws ResourceNotFoundException {

        return ResponseEntity.ok(mapper.mapAccountToAccountResponse(service.getAccountInfo(accountId)));
    }
}
