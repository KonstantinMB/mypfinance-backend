package com.mypfinance.budgettrackersvc.service;

import com.mypfinance.budgettrackersvc.exception.ResourceNotFoundException;
import com.mypfinance.budgettrackersvc.models.domain.ExpenseCategory;

import java.util.List;

public interface ExpenseCategoryService {

    ExpenseCategory getCategoryByName(String name) throws ResourceNotFoundException;

    List<ExpenseCategory> getAllCategories() throws ResourceNotFoundException;

    ExpenseCategory saveCategory(ExpenseCategory category) throws ResourceNotFoundException;

}
