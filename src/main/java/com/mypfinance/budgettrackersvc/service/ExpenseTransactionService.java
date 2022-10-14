package com.mypfinance.budgettrackersvc.service;

import com.mypfinance.budgettrackersvc.exception.ResourceNotFoundException;
import com.mypfinance.budgettrackersvc.models.domain.ExpenseTransaction;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseTransactionService {

    ExpenseTransaction getTransactionById(String id) throws ResourceNotFoundException;

    List<ExpenseTransaction> getTransactionsByCategoryName(String categoryName) throws ResourceNotFoundException;

    List<ExpenseTransaction> getTransactions(String accountId) throws ResourceNotFoundException;

    List<ExpenseTransaction> getTransactionsByDate(LocalDate date) throws ResourceNotFoundException;

    ExpenseTransaction saveTransaction(ExpenseTransaction transaction) throws ResourceNotFoundException;

    ExpenseTransaction modifyTransaction(String transactionId, ExpenseTransaction request)
            throws RuntimeException, ResourceNotFoundException;

    void deleteTransactionById(String transactionId) throws ResourceNotFoundException;
}
