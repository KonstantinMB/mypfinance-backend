package com.mypfinance.budgettrackersvc.repository;

import com.mypfinance.budgettrackersvc.models.domain.IncomeCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IncomeCategoryRepository extends JpaRepository<IncomeCategory, UUID> {

    @Query("SELECT c "
            + "FROM IncomeCategory c "
            + "WHERE c.id = ?1")
    Optional<IncomeCategory> getCategoryById(String id);

    @Query("SELECT c "
            + "FROM IncomeCategory c "
            + "WHERE c.name = ?1")
    Optional<IncomeCategory> getCategoryByName(String name);

    @Query("SELECT c "
            + "FROM IncomeCategory c")
    List<IncomeCategory> getAllCategories();

}
