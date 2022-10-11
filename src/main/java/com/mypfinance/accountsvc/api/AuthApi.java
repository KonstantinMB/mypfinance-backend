package com.mypfinance.accountsvc.api;

import com.mypfinance.accountsvc.models.domain.LoginRequest;
import com.mypfinance.accountsvc.models.dto.AccountDto;
import com.mypfinance.accountsvc.models.dto.AccountResponse;
import com.mypfinance.accountsvc.models.mapper.AccountMapper;
import com.mypfinance.accountsvc.security.JwtTokenGeneration;
import com.mypfinance.accountsvc.service.AccountService;
import com.mypfinance.budgettrackersvc.exception.RequestNotValidException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class AuthApi {

    private final JwtTokenGeneration tokenGen;

    private final AccountService service;

    private final AuthenticationManager authManager;

    private final AccountMapper mapper;

    public AuthApi(JwtTokenGeneration tokenGen,
                   AccountService service,
                   AuthenticationManager authManager,
                   AccountMapper mapper) {
        this.tokenGen = tokenGen;
        this.service = service;
        this.authManager = authManager;
        this.mapper = mapper;
    }

    @PostMapping(value = "/login", produces = APPLICATION_JSON_VALUE , consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getToken(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));
        String token = tokenGen.generateToken(authentication);
        return ResponseEntity.ok(token);
    }

    @PostMapping(value = "/register", produces = APPLICATION_JSON_VALUE , consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountResponse> registerAccount(@RequestBody AccountDto request) throws RequestNotValidException {

        service.accountExists(request.getUsername(), request.getEmail());

        return ResponseEntity.ok(mapper.mapAccountToAccountResponse(
                service.saveAccount(
                        mapper.mapAccountDtoToAccount(request))));
    }
}
