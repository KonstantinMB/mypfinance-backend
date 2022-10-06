package com.mypfinance.accountsvc.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mypfinance.accountsvc.models.domain.Account;
import com.mypfinance.accountsvc.service.AccountService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
@Slf4j
@PropertySource("classpath:application.properties")
public class CustomAuth extends UsernamePasswordAuthenticationFilter {

    private final int jwtExpirationTime;

    private final String jwtSecret;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ConfigProperties configProp;

    @Autowired
    private AuthenticationManager authenticationManager;

    public CustomAuth() {
        this.jwtExpirationTime = Integer.parseInt(configProp.getConfigValue("jwt.expiration.time"));
        this.jwtSecret = configProp.getConfigValue("jwt.secret");
    }

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        log.info("Username is: {}", username);
        log.info("Password is : {}", password);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        Map<String, Object> error = new HashMap<>();
        error.put("time", new Date().getTime());
        error.put("error_type", UNAUTHORIZED);
        error.put("status_code", UNAUTHORIZED.value());
        error.put("message", failed.getMessage());
        new ObjectMapper().writeValue(response.getOutputStream(), error);
        throw new AccessDeniedException("Username and/or password is incorrect. Please provide valid credentials.");
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        User user = (User) authentication.getPrincipal();
        Algorithm algo = Algorithm.HMAC256(jwtSecret.getBytes());

        String accessToken = JWT.create()
                .withClaim("accountId", accountService.getAccountInfo(user.getUsername()).getId())
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .withExpiresAt(Instant.ofEpochMilli(ZonedDateTime.now(ZoneId.systemDefault())
                        .toInstant().toEpochMilli() + jwtExpirationTime))
                .withIssuer(request.getRequestURL().toString())
                .sign(algo);

        Map<String, String> tokens = new HashMap<>();
        tokens.put("token", accessToken);

        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }
}
