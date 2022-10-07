package com.mypfinance.accountsvc.service;

import com.mypfinance.accountsvc.models.domain.Account;
import com.mypfinance.accountsvc.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public Account getAccountInfo(String username) throws UsernameNotFoundException {

        Optional<Account> account =  repository.getAccountByUsername(username);

        if(account.isEmpty()) {
            throw new UsernameNotFoundException(String.format("Account with this username [%s] does not exist.", username));
        }
        return account.get();
    }

    @Override
    @Transactional
    public Account updateAccount(Account account) {
        return null;
    }

    @Override
    @Transactional
    public Account saveAccount(Account account) {

        String username = account.getUsername();
        if(repository.getAccountByUsername(username).isPresent()){
            throw new UsernameNotFoundException(String.format("Account with this username [%s] does not exist.", username));
        }

        account.setPassword(passwordEncoder.encode(account.getPassword()));


        return repository.save(account);
    }
}
