package com.mypfinance.accountsvc.repository;

import com.mypfinance.accountsvc.models.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

    Optional<Account> findAccountByUsername(String username);
}
