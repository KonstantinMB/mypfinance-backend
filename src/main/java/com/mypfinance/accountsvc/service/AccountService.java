package com.mypfinance.accountsvc.service;

import com.mypfinance.accountsvc.models.domain.Account;
import com.mypfinance.budgettrackersvc.exception.RequestNotValidException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public interface AccountService {

    Account getAccountInfo(String username) throws UsernameNotFoundException;

    void accountExists(String username, String email) throws RequestNotValidException;

    Account saveAccount(Account account);

    Account updateAccount(Account account);
}
