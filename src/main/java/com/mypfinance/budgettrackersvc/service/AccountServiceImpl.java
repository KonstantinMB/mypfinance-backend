package com.mypfinance.budgettrackersvc.service;

import com.mypfinance.budgettrackersvc.exception.RequestNotValidException;
import com.mypfinance.budgettrackersvc.exception.ResourceNotFoundException;
import com.mypfinance.budgettrackersvc.models.domain.Account;
import com.mypfinance.budgettrackersvc.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;

    @Override
    public Account getAccountInfo(String accountId) throws ResourceNotFoundException {

        Optional<Account> account =  repository.getAccountById(accountId);

        if(account.isEmpty()) {
            throw new ResourceNotFoundException(String.format("No account with ID %s has been found.", accountId), BAD_REQUEST);
        }
        return account.get();
    }

    @Override
    public Account saveAccount(Account account) throws RequestNotValidException {

        if(repository.getAccountById(account.getId()).isPresent()){
            throw new RequestNotValidException("&.id", "This account already exists.", BAD_REQUEST);
        }
        return repository.save(account);
    }
}
