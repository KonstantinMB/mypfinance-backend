package com.mypfinance.accountsvc.repository;

import com.mypfinance.accountsvc.models.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

    @Query("SELECT a "
            + "FROM Account a "
            + "WHERE a.id = ?1")
    Optional<Account> getAccountById(String id);

    @Query("SELECT a "
            + "FROM Account a "
            + "WHERE a.username = ?1")
    Optional<Account> getAccountByUsername(String username);

    @Query("SELECT a "
            + "FROM Account a "
            + "WHERE a.email = ?1")
    Optional<Account> getAccountByEmail(String email);
}
