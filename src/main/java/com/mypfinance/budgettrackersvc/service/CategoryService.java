package com.mypfinance.budgettrackersvc.service;

import com.mypfinance.budgettrackersvc.models.domain.Category;

public interface CategoryService {

    Category getCategoryByName(String name);

    Category saveCategory(Category category);

}
