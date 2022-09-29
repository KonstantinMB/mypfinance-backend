package com.mypfinance.budgettrackersvc.service;

import com.mypfinance.budgettrackersvc.exception.ResourceNotFoundException;
import com.mypfinance.budgettrackersvc.models.domain.ExpenseCategory;

import java.util.List;
import java.util.Optional;

public interface ExpenseCategoryService {

    ExpenseCategory getCategoryByName(String name) throws ResourceNotFoundException;

    List<ExpenseCategory> getAllCategories() throws ResourceNotFoundException;

    ExpenseCategory saveCategory(ExpenseCategory category) throws ResourceNotFoundException;

}
