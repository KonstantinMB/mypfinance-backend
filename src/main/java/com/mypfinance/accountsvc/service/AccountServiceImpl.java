package com.mypfinance.accountsvc.service;

import com.mypfinance.exception.RequestNotValidException;
import com.mypfinance.exception.ResourceNotFoundException;
import com.mypfinance.accountsvc.models.domain.Account;
import com.mypfinance.accountsvc.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;

    private final BCryptPasswordEncoder passwordEncoder;

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
