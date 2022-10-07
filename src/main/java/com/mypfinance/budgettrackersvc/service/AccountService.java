package com.mypfinance.budgettrackersvc.service;

import com.mypfinance.budgettrackersvc.exception.RequestNotValidException;
import com.mypfinance.budgettrackersvc.exception.ResourceNotFoundException;
import com.mypfinance.budgettrackersvc.models.domain.Account;

import java.util.Optional;

public interface AccountService {

    Account getAccountInfo(String accountId) throws ResourceNotFoundException;

    Account saveAccount(Account account) throws RequestNotValidException;
}
