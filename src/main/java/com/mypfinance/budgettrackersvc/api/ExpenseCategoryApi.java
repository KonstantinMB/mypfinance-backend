package com.mypfinance.budgettrackersvc.api;

import com.mypfinance.budgettrackersvc.exception.ResourceNotFoundException;
import com.mypfinance.budgettrackersvc.models.domain.ExpenseCategory;
import com.mypfinance.budgettrackersvc.models.dto.CategoryDto;
import com.mypfinance.budgettrackersvc.models.mapper.CategoryMapper;
import com.mypfinance.budgettrackersvc.service.ExpenseCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1/budget-tracker/expense/categories")
public class ExpenseCategoryApi {

    private final ExpenseCategoryService service;

    private final CategoryMapper mapper;

    @Autowired
    public ExpenseCategoryApi(ExpenseCategoryService service, CategoryMapper categoryMapper) {
        this.service = service;
        this.mapper = categoryMapper;
    }

    @GetMapping(consumes = APPLICATION_JSON_VALUE)
    @RolesAllowed("USER")
    public ResponseEntity<List<CategoryDto>> getCategories() throws ResourceNotFoundException {

        List<ExpenseCategory> categories = service.getAllCategories();

        List<CategoryDto> response = new ArrayList<>();
        for(ExpenseCategory category : categories){
            response.add(mapper.mapExpenseCategoryToDto(category));
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto request) throws ResourceNotFoundException {

        return ResponseEntity.ok(mapper.mapExpenseCategoryToDto(
                service.saveCategory(mapper.mapCategoryDtoToExpenseCategory(request))));
    }
}
