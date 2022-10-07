package com.mypfinance.budgettrackersvc.api;

import com.mypfinance.accountsvc.service.AccountService;
import com.mypfinance.budgettrackersvc.exception.ResourceNotFoundException;
import com.mypfinance.accountsvc.models.dto.AccountDto;
import com.mypfinance.accountsvc.models.dto.AccountResponse;
import com.mypfinance.accountsvc.models.mapper.AccountMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1")
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

        return ResponseEntity.ok(mapper.mapAccountToAccountResponse(
                service.saveAccount(mapper.mapAccountDtoToAccount(request))));
    }
}
