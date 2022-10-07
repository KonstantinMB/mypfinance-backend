package com.mypfinance.accountsvc.api;

import com.mypfinance.accountsvc.security.JwtTokenGeneration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
public class AuthApi {

    private final JwtTokenGeneration tokenGen;

    public AuthApi(JwtTokenGeneration tokenGen) {
        this.tokenGen = tokenGen;
    }

    @PostMapping("/token")
    public ResponseEntity<String> getToken(Authentication authentication) {

        String token = tokenGen.generateToken(authentication);
        log.info("A token request has been fulfilled!");
        return ResponseEntity.ok(token);
    }
}
