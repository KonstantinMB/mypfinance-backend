package com.mypfinance.accountsvc.security;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.mypfinance.accountsvc.service.AccountDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

import java.net.http.HttpRequest;

@Configuration
@Slf4j
public class MyPFinanceSecurity {


    private final AuthenticationManager authenticationManager;

    private final SuccessfulAuthentication authSuccessHandler;

    private final AccountDetailsService detailsService;

    private final String secret;

    public MyPFinanceSecurity(AuthenticationManager authenticationManager, SuccessfulAuthentication authSuccessHandler, AccountDetailsService detailsService, String secret) {
        this.authenticationManager = authenticationManager;
        this.authSuccessHandler = authSuccessHandler;
        this.detailsService = detailsService;
        this.secret = secret;
    }

    private SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.
                cors().and().csrf().disable()
                .authorizeHttpRequests((auth) -> {
                    try {
                        auth
                                .antMatchers().permitAll()
                                .anyRequest()
                                .authenticated()
                                .and()
                                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                                .and()
                                .addFilter(authenticationFilter())
                                .addFilter(new JwtAuthorizationFilter(authenticationManager,
                                        detailsService, secret))
                                .exceptionHandling()
                                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
                    } catch (Exception e) {
                        log.error("Encountered a problem with the account's authorization.");
                    }
                }
                ).httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public JsonAuthenticationFilter authenticationFilter() throws Exception {
        JsonAuthenticationFilter filter = new JsonAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(authSuccessHandler);
        filter.setAuthenticationManager(authenticationManager);
        return filter;
    }
}
