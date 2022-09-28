package com.mypfinance.budgettrackersvc.repository;

import com.mypfinance.budgettrackersvc.models.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {

    @Query("SELECT c "
            + "FROM Category c "
            + "WHERE c.id = ?1")
    Category getCategoryById(UUID id);

    @Query("SELECT c "
            + "FROM Category c "
            + "WHERE c.name = ?1")
    Category getCategoryByName(String name);

}
