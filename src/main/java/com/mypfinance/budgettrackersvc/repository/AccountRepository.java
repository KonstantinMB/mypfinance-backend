package com.mypfinance.budgettrackersvc.repository;

import com.mypfinance.budgettrackersvc.models.domain.Account;
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
}
