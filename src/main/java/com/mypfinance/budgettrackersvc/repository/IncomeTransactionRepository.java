package com.mypfinance.budgettrackersvc.repository;

import com.mypfinance.budgettrackersvc.models.domain.IncomeTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IncomeTransactionRepository extends JpaRepository<IncomeTransaction, UUID> {

    @Query("SELECT c "
            + "FROM IncomeTransaction c "
            + "WHERE c.id = ?1")
    Optional<IncomeTransaction> getTransactionById(String id);

    @Query("SELECT c "
            + "FROM IncomeTransaction c "
            + "WHERE c.categoryName = ?1")
    List<IncomeTransaction> getTransactionsByCategoryName(String categoryName);
}
