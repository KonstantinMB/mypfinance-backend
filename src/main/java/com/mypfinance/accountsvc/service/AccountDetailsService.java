package com.mypfinance.accountsvc.service;

import com.mypfinance.accountsvc.models.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountDetailsService implements UserDetailsService {

    private final AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountService.getAccountInfo(username);

        return new User(account.getUsername(), account.getPassword(), account.isEnabled(), true,
                true, true, account.getAuthorities());
    }
}
