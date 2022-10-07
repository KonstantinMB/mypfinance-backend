package com.mypfinance.accountsvc.service;

import com.mypfinance.accountsvc.models.domain.Account;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface AccountService {

    Account getAccountInfo(String username) throws UsernameNotFoundException;

    Account saveAccount(Account account);

    Account updateAccount(Account account);
}
