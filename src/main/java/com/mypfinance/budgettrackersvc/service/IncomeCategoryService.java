package com.mypfinance.budgettrackersvc.service;

import com.mypfinance.exception.ResourceNotFoundException;
import com.mypfinance.budgettrackersvc.models.domain.IncomeCategory;

import java.util.List;

public interface IncomeCategoryService {

    IncomeCategory getCategoryByName(String name) throws ResourceNotFoundException;

    List<IncomeCategory> getAllCategories() throws ResourceNotFoundException;

    IncomeCategory saveCategory(IncomeCategory category) throws ResourceNotFoundException;

}
