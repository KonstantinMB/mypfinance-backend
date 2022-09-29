package com.mypfinance.budgettrackersvc.repository;

import com.mypfinance.budgettrackersvc.models.domain.ExpenseTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ExpenseTransactionRepository extends JpaRepository<ExpenseTransaction, UUID> {

    @Query("SELECT c "
            + "FROM ExpenseTransaction c "
            + "WHERE c.id = ?1")
    Optional<ExpenseTransaction> getTransactionById(UUID id);

    @Query("SELECT c "
            + "FROM ExpenseTransaction c "
            + "WHERE c.categoryName = ?1")
    List<ExpenseTransaction> getTransactionsByCategoryName(String categoryName);
}
