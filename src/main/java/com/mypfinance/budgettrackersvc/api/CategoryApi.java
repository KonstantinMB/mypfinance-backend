package com.mypfinance.budgettrackersvc.api;


import com.mypfinance.budgettrackersvc.exception.RequestNotValidException;
import com.mypfinance.budgettrackersvc.models.dto.CategoryDto;
import com.mypfinance.budgettrackersvc.models.mapper.CategoryMapper;
import com.mypfinance.budgettrackersvc.service.CategoryService;
import com.mypfinance.budgettrackersvc.validation.TypeCheckup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1/budget-tracker/category")
public class CategoryApi {

    private final CategoryService service;

    private final CategoryMapper categoryMapper;

    private final TypeCheckup typeCheckup;


    @Autowired
    public CategoryApi(CategoryService service, CategoryMapper categoryMapper, TypeCheckup typeCheckup) {
        this.service = service;
        this.categoryMapper = categoryMapper;
        this.typeCheckup = typeCheckup;
    }

    @GetMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDto> getCategoryByName(@RequestBody String categoryName) {

        CategoryDto response = categoryMapper.mapCategoryToDto(
                service.getCategoryByName(categoryName.toLowerCase(Locale.ROOT)));

        return ResponseEntity.ok(response);
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE , consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto request) throws RequestNotValidException {

        typeCheckup.checkTypeCase(request.getType());

        CategoryDto response = categoryMapper.mapCategoryToDto(
                service.saveCategory(categoryMapper.mapCategoryDtoToDomain(request)));

        return ResponseEntity.ok(response);
    }
}
