package com.mypfinance.accountsvc.service;

import com.mypfinance.accountsvc.models.domain.Account;
import com.mypfinance.exception.RequestNotValidException;
import com.mypfinance.exception.ResourceNotFoundException;

public interface AccountService {

    Account getAccountInfo(String accountId) throws ResourceNotFoundException;

    Account saveAccount(Account account) throws RequestNotValidException;
}
