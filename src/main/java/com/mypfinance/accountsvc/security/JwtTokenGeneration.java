package com.mypfinance.accountsvc.security;

import com.mypfinance.accountsvc.models.domain.Account;
import com.mypfinance.accountsvc.service.AccountService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Service
public class JwtTokenGeneration {

    private final JwtEncoder encoder;

    private final AccountService accountService;

    public JwtTokenGeneration(JwtEncoder encoder, AccountService accountService) {
        this.encoder = encoder;
        this.accountService = accountService;
    }

    public String generateToken(Authentication authentication) {

        Account account = accountService.getAccountInfo(authentication.getName());

        Instant now = Instant.now();
        String roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .claim("accountId", account.getId())
                .claim("roles", roles)
                .build();
        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
