package com.mypfinance.budgettrackersvc.repository;

import com.mypfinance.budgettrackersvc.models.domain.Category;
import com.mypfinance.budgettrackersvc.models.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    @Query("SELECT c "
            + "FROM Transaction c "
            + "WHERE c.id = ?1")
    Transaction getTransactionById(UUID id);

    @Query("SELECT c "
            + "FROM Transaction c "
            + "WHERE c.categoryName = ?1")
    List<Transaction> getTransactionsByCategoryName(String categoryName);
}
