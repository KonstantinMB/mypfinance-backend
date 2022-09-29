package com.mypfinance.budgettrackersvc.models.mapper;

import com.mypfinance.budgettrackersvc.models.domain.ExpenseCategory;
import com.mypfinance.budgettrackersvc.models.domain.IncomeCategory;
import com.mypfinance.budgettrackersvc.models.dto.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CategoryMapper {

    public IncomeCategory mapCategoryDtoToIncomeCategory(CategoryDto dto) {

        return IncomeCategory.builder()
                .id(UUID.randomUUID())
                .name(dto.getName())
                .color(dto.getColor())
                .build();
    }

    public ExpenseCategory mapCategoryDtoToExpenseCategory(CategoryDto dto) {

        return ExpenseCategory.builder()
                .id(UUID.randomUUID())
                .name(dto.getName())
                .color(dto.getColor())
                .build();
    }

    public CategoryDto mapIncomeCategoryToDto(IncomeCategory domain) {

        return CategoryDto.builder()
                .name(domain.getName())
                .color(domain.getColor())
                .build();
    }

    public CategoryDto mapExpenseCategoryToDto(ExpenseCategory domain) {

        return CategoryDto.builder()
                .name(domain.getName())
                .color(domain.getColor())
                .build();
    }
}
