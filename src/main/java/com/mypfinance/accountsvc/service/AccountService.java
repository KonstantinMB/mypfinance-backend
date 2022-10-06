package com.mypfinance.accountsvc.service;

import com.mypfinance.accountsvc.models.domain.Account;
import com.mypfinance.exception.RequestNotValidException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface AccountService {

    Account getAccountInfo(String username) throws UsernameNotFoundException;

    Account saveAccount(Account account) throws RequestNotValidException;

    Account updateAccount(Account account) throws RequestNotValidException;
}
