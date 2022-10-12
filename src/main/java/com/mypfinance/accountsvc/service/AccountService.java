package com.mypfinance.accountsvc.service;

import com.mypfinance.accountsvc.exception.ResourceNotFoundException;
import com.mypfinance.accountsvc.models.domain.Account;
import com.mypfinance.accountsvc.exception.RequestNotValidException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface AccountService {

    Account getAccountInfo(String username) throws UsernameNotFoundException;

    Account getAccountInfoById(String id) throws ResourceNotFoundException;

    void accountExists(String username, String email) throws RequestNotValidException;

    Account saveAccount(Account account);

    Account updateAccount(Account account);
}
