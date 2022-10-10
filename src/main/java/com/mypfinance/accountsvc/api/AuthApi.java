package com.mypfinance.accountsvc.api;

import com.mypfinance.accountsvc.models.dto.AccountDto;
import com.mypfinance.accountsvc.models.dto.AccountResponse;
import com.mypfinance.accountsvc.models.mapper.AccountMapper;
import com.mypfinance.accountsvc.security.JwtTokenGeneration;
import com.mypfinance.accountsvc.service.AccountService;
import com.mypfinance.budgettrackersvc.exception.RequestNotValidException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class AuthApi {

    private final JwtTokenGeneration tokenGen;

    private final AccountService service;

    private final AccountMapper mapper;

    public AuthApi(JwtTokenGeneration tokenGen, AccountService service, AccountMapper mapper) {
        this.tokenGen = tokenGen;
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping("/auth/token")
    public ResponseEntity<String> getToken(Authentication authentication) {

        String token = tokenGen.generateToken(authentication);
        log.info("A token request has been fulfilled!");
        return ResponseEntity.ok(token);
    }

    @PostMapping(value = "/register", produces = APPLICATION_JSON_VALUE , consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountResponse> registerAccount(AccountDto request) throws RequestNotValidException {

        service.accountExists(request.getUsername(), request.getEmail());

        return ResponseEntity.ok(mapper.mapAccountToAccountResponse(
                service.saveAccount(
                        mapper.mapAccountDtoToAccount(request))));
    }
}
