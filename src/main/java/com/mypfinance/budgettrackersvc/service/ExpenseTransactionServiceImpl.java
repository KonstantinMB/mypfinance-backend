package com.mypfinance.budgettrackersvc.service;

import com.mypfinance.exception.ResourceNotFoundException;
import com.mypfinance.budgettrackersvc.models.domain.ExpenseTransaction;
import com.mypfinance.budgettrackersvc.repository.ExpenseTransactionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
@AllArgsConstructor
@Slf4j
public class ExpenseTransactionServiceImpl implements ExpenseTransactionService {

    private final ExpenseTransactionRepository repository;

    @Override
    public ExpenseTransaction getTransactionById(String id) throws ResourceNotFoundException {

        Optional<ExpenseTransaction> response = repository.getTransactionById(id);

        if(response.isEmpty()){
            throw new ResourceNotFoundException(String.format("Failed to find transaction with ID: %s", id), BAD_REQUEST);
        }

        return response.get();
    }

    @Override
    public List<ExpenseTransaction> getTransactionsByCategoryName(String categoryName) throws ResourceNotFoundException {

        List<ExpenseTransaction> response = repository.getTransactionsByCategoryName(categoryName.toLowerCase(Locale.ROOT));

        if(response.isEmpty()) {
            throw new ResourceNotFoundException(String.format("No transactions were found with this category name: %s", categoryName), BAD_REQUEST);
        }
        return response;
    }

    @Override
    public List<ExpenseTransaction> getTransactionsByDate(LocalDate date) throws ResourceNotFoundException {

        String dateToString = date.toString();
        List<ExpenseTransaction> response = repository.getTransactionsByDate(dateToString);

        if(response.isEmpty()) {
            throw new ResourceNotFoundException(String.format("No transactions were found on this day: %s", dateToString), BAD_REQUEST);
        }
        return response;
    }

    @Override
    public ExpenseTransaction saveTransaction(ExpenseTransaction transaction) throws ResourceNotFoundException {

        repository.save(transaction);

        Optional<ExpenseTransaction> response = repository.getTransactionById(transaction.getId());

        if(response.isEmpty()){
            throw new ResourceNotFoundException(String.format("Failed to find transaction with ID: %s", transaction.getId()), BAD_REQUEST);
        }
        return response.get();
    }

    @Override
    public ExpenseTransaction modifyTransaction(String transactionId, ExpenseTransaction request)
            throws RuntimeException, ResourceNotFoundException {

        Optional<ExpenseTransaction> existingTransaction = repository.getTransactionById(transactionId);

        if(existingTransaction.isPresent()){
            try {
                return existingTransaction.map(transaction -> {

                    ExpenseTransaction modifiedTransaction = new ExpenseTransaction();
                    modifiedTransaction.setId(transactionId);
                    modifiedTransaction.setDate(request.getDate());
                    modifiedTransaction.setCategoryName(request.getCategoryName());
                    modifiedTransaction.setAmount(request.getAmount());
                    modifiedTransaction.setCurrency(request.getCurrency());
                    modifiedTransaction.setDescription(request.getDescription());

                    repository.delete(transaction);

                    repository.save(modifiedTransaction);

                    return modifiedTransaction;
                }).get();
            } catch (Exception e) {
                log.debug(String.format("Something went wrong while trying to update expense transaction with ID: %s",
                        transactionId), e.getMessage(), e.getStackTrace());
                throw new RuntimeException("Something went wrong while trying to update this transaction. Please try again!");
            }
        } else {
            throw new ResourceNotFoundException(String.format("Failed to find transaction with ID: %s", transactionId), BAD_REQUEST);
        }
    }

    @Override
    public void deleteTransactionById(String transactionId) throws ResourceNotFoundException {

        Optional<ExpenseTransaction> existingTransaction = repository.getTransactionById(transactionId);

        if(existingTransaction.isPresent()){
            repository.delete(existingTransaction.get());
        } else {
            throw new ResourceNotFoundException(String.format("Failed to find transaction with ID: %s", transactionId), BAD_REQUEST);
        }
    }
}
