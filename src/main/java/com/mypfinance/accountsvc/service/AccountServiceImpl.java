package com.mypfinance.accountsvc.service;

import com.mypfinance.exception.RequestNotValidException;
import com.mypfinance.accountsvc.models.domain.Account;
import com.mypfinance.accountsvc.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService, UserDetailsService {

    private final AccountRepository repository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> account = repository.findAccountByUsername(username);
        if(account.isEmpty()) {
            throw new UsernameNotFoundException(String.format("Account with this username [%s] does not exist.", username));
        }

        return new User(account.get().getUsername(), account.get().getPassword(), account.get().isEnabled(), true,
                true, true, account.get().getAuthorities());
    }

    @Override
    public Account getAccountInfo(String username) throws UsernameNotFoundException {

        Optional<Account> account =  repository.findAccountByUsername(username);

        if(account.isEmpty()) {
            throw new UsernameNotFoundException(String.format("Account with this username [%s] does not exist.", username));
        }
        return account.get();
    }

    @Override
    public Account updateAccount(Account account) throws RequestNotValidException {
        return null;
    }

    @Override
    public Account saveAccount(Account account) throws RequestNotValidException {

        String username = account.getUsername();
        if(repository.findAccountByUsername(username).isPresent()){
            throw new UsernameNotFoundException(String.format("Account with this username [%s] does not exist.", username));
        }

        account.setPassword(passwordEncoder.encode(account.getPassword()));


        return repository.save(account);
    }
}
