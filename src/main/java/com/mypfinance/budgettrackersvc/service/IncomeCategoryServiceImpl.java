package com.mypfinance.budgettrackersvc.service;

import com.mypfinance.exception.ResourceNotFoundException;
import com.mypfinance.budgettrackersvc.models.domain.IncomeCategory;
import com.mypfinance.budgettrackersvc.repository.IncomeCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
@AllArgsConstructor
public class IncomeCategoryServiceImpl implements IncomeCategoryService {

    private final IncomeCategoryRepository repository;

    @Override
    public IncomeCategory getCategoryByName(String name) throws ResourceNotFoundException {

        Optional<IncomeCategory> response = repository.getCategoryByName(name.toLowerCase(Locale.ROOT));

        if(response.isEmpty()){
            throw new ResourceNotFoundException(String.format("Failed to find category of type: %s", name), BAD_REQUEST);
        }

        return response.get();
    }

    @Override
    public List<IncomeCategory> getAllCategories() throws ResourceNotFoundException {

        List<IncomeCategory> response = repository.getAllCategories();

        if(response.isEmpty()) {
            throw new ResourceNotFoundException("No categories were found.", BAD_REQUEST);
        }
        return response;
    }

    @Override
    @Transactional
    public IncomeCategory saveCategory(IncomeCategory category) throws ResourceNotFoundException {

        repository.save(category);

        Optional<IncomeCategory> response = repository.getCategoryById(category.getId());

        if(response.isEmpty()){
            throw new ResourceNotFoundException(String.format("Failed to find category with name: %s", category.getName()), BAD_REQUEST);
        }
        return response.get();
    }
}
