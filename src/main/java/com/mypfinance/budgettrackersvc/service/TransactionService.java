package com.mypfinance.budgettrackersvc.service;

import com.mypfinance.budgettrackersvc.models.domain.Transaction;

import java.util.List;
import java.util.UUID;

public interface TransactionService {

    Transaction getTransactionById(UUID id);

    List<Transaction> getTransactionsByCategoryName(String categoryName);

    Transaction saveTransaction(Transaction transaction);
}
