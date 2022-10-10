package com.mypfinance.accountsvc.service;

import com.mypfinance.accountsvc.models.domain.Account;
import com.mypfinance.accountsvc.repository.AccountRepository;
import com.mypfinance.budgettrackersvc.exception.RequestNotValidException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;

    @Override
    public Account getAccountInfo(String username) throws UsernameNotFoundException {

        Optional<Account> account =  repository.getAccountByUsername(username);

        if(account.isEmpty()) {
            throw new UsernameNotFoundException(String.format("Account with this username [%s] does not exist.", username));
        }
        return account.get();
    }

    @Override
    public void accountExists(String username, String email) throws RequestNotValidException {

        Optional<Account> accountByEmail =  repository.getAccountByEmail(email);

        if(accountByEmail.isPresent()) {
            throw new RequestNotValidException("$.email",
                    String.format("Account with this email [%s] already exists.", email),
                    HttpStatus.BAD_REQUEST);
        }

        Optional<Account> accountByUsername =  repository.getAccountByUsername(username);

        if(accountByUsername.isPresent()) {
            throw new RequestNotValidException("$.username",
                    String.format("Account with this username [%s] already exists.", username),
                    HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    @Transactional
    public Account updateAccount(Account account) {
        return null;
    }

    @Override
    @Transactional
    public Account saveAccount(Account account) {

        return repository.save(account);
    }
}
