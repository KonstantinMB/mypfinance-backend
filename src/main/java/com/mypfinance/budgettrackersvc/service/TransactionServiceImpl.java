package com.mypfinance.budgettrackersvc.service;

import com.mypfinance.budgettrackersvc.models.domain.Transaction;
import com.mypfinance.budgettrackersvc.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    public Transaction getTransactionById(UUID id) {

        return transactionRepository.getTransactionById(id);
    }

    @Override
    public List<Transaction> getTransactionsByCategoryName(String categoryName) {

        return transactionRepository.getTransactionsByCategoryName(categoryName);
    }

    @Override
    public Transaction saveTransaction(Transaction transaction) {

        transactionRepository.save(transaction);

        return transactionRepository.getTransactionById(transaction.getId());
    }
}
