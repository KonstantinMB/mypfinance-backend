package com.mypfinance.budgettrackersvc.service;

import com.mypfinance.budgettrackersvc.exception.ResourceNotFoundException;
import com.mypfinance.budgettrackersvc.models.domain.ExpenseCategory;
import com.mypfinance.budgettrackersvc.repository.ExpenseCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
@AllArgsConstructor
public class ExpenseCategoryServiceImpl implements ExpenseCategoryService {

    private final ExpenseCategoryRepository repository;

    @Override
    public ExpenseCategory getCategoryByName(String name) throws ResourceNotFoundException {

        Optional<ExpenseCategory> response = repository.getCategoryByName(name.toLowerCase(Locale.ROOT));

        if(response.isEmpty()){
            throw new ResourceNotFoundException(String.format("Failed to find category of type: %s", name), BAD_REQUEST);
        }

        return response.get();
    }

    @Override
    public List<ExpenseCategory> getAllCategories() throws ResourceNotFoundException {

        List<ExpenseCategory> response = repository.getAllCategories();

        if(response.isEmpty()) {
            throw new ResourceNotFoundException("No categories were found.", BAD_REQUEST);
        }
        return response;
    }

    @Override
    @Transactional
    public ExpenseCategory saveCategory(ExpenseCategory category) throws ResourceNotFoundException {

        repository.save(category);

        Optional<ExpenseCategory> response = repository.getCategoryById(category.getId());

        if(response.isEmpty()){
            throw new ResourceNotFoundException(String.format("Failed to find category with name: %s", category.getName()), BAD_REQUEST);
        }
        return response.get();
    }
}
