package com.mypfinance.accountsvc.security;

import ch.qos.logback.core.encoder.EchoEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import java.net.http.HttpRequest;

@Configuration
@Slf4j
public class MyPFinanceSecurity {

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
                                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                    } catch (Exception e) {
                        log.error("Encountered a problem with the account's authorization.");
                    }
                }
                ).httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public JsonObjectAuthenticationFilter authenticationFilter() throws Exception {
        JsonObjectAuthenticationFilter filter = new JsonObjectAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(authSuccessHandler);
        filter.setAuthenticationManager(authenticationManager);
        return filter;
    }
}
