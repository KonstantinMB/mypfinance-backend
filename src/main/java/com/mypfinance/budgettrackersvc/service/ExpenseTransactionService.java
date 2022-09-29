package com.mypfinance.budgettrackersvc.service;

import com.mypfinance.budgettrackersvc.exception.ResourceNotFoundException;
import com.mypfinance.budgettrackersvc.models.domain.ExpenseTransaction;

import java.util.List;
import java.util.UUID;

public interface ExpenseTransactionService {

    ExpenseTransaction getTransactionById(UUID id) throws ResourceNotFoundException;

    List<ExpenseTransaction> getTransactionsByCategoryName(String categoryName) throws ResourceNotFoundException;

    ExpenseTransaction saveTransaction(ExpenseTransaction transaction) throws ResourceNotFoundException;
}
