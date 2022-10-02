package com.mypfinance.budgettrackersvc.repository;

import com.mypfinance.budgettrackersvc.models.domain.ExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ExpenseCategoryRepository extends JpaRepository<ExpenseCategory, String> {

    @Query("SELECT c "
            + "FROM ExpenseCategory c "
            + "WHERE c.id = ?1")
    Optional<ExpenseCategory> getCategoryById(String id);

    @Query("SELECT c "
            + "FROM ExpenseCategory c "
            + "WHERE c.name = ?1")
    Optional<ExpenseCategory> getCategoryByName(String name);

    @Query("SELECT c "
            + "FROM ExpenseCategory c")
    List<ExpenseCategory> getAllCategories();


}
