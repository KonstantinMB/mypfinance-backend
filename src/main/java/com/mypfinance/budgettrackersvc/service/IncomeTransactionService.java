package com.mypfinance.budgettrackersvc.service;

import com.mypfinance.budgettrackersvc.exception.ResourceNotFoundException;
import com.mypfinance.budgettrackersvc.models.domain.IncomeTransaction;

import java.util.List;

public interface IncomeTransactionService {

    IncomeTransaction getTransactionById(String id) throws ResourceNotFoundException;

    List<IncomeTransaction> getTransactionsByCategoryName(String categoryName) throws ResourceNotFoundException;

    IncomeTransaction saveTransaction(IncomeTransaction transaction) throws ResourceNotFoundException;
}
