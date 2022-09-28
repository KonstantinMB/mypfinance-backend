package com.mypfinance.budgettrackersvc.service;

import com.mypfinance.budgettrackersvc.models.domain.Category;
import com.mypfinance.budgettrackersvc.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    @Override
    public Category getCategoryByName(String name) {

        return repository.getCategoryByName(name);
    }

    @Override
    @Transactional
    public Category saveCategory(Category category) {

        repository.save(category);

        return repository.getCategoryById(category.getId());
    }
}
