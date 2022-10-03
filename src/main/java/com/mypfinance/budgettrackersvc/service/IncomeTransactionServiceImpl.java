package com.mypfinance.budgettrackersvc.service;

import com.mypfinance.exception.ResourceNotFoundException;
import com.mypfinance.budgettrackersvc.models.domain.IncomeTransaction;
import com.mypfinance.budgettrackersvc.repository.IncomeTransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
@AllArgsConstructor
public class IncomeTransactionServiceImpl implements IncomeTransactionService {

    private final IncomeTransactionRepository repository;

    @Override
    public IncomeTransaction getTransactionById(String id) throws ResourceNotFoundException {

        Optional<IncomeTransaction> response = repository.getTransactionById(id);

        if(response.isEmpty()){
            throw new ResourceNotFoundException(String.format("Failed to find transaction with ID: %s", id), BAD_REQUEST);
        }

        return response.get();
    }

    @Override
    public List<IncomeTransaction> getTransactionsByCategoryName(String categoryName) throws ResourceNotFoundException {

        List<IncomeTransaction> response = repository.getTransactionsByCategoryName(categoryName);

        if(response.isEmpty()) {
            throw new ResourceNotFoundException(String.format("No transactions were found with this category name: %s", categoryName), BAD_REQUEST);
        }
        return response;
    }

    @Override
    public IncomeTransaction saveTransaction(IncomeTransaction transaction) throws ResourceNotFoundException {

        repository.save(transaction);

        Optional<IncomeTransaction> response = repository.getTransactionById(transaction.getId());

        if(response.isEmpty()){
            throw new ResourceNotFoundException(String.format("Failed to find transaction with ID: %s", transaction.getId()), BAD_REQUEST);
        }
        return response.get();
    }
}
