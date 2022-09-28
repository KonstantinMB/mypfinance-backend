package com.mypfinance.budgettrackersvc.models.mapper;

import com.mypfinance.budgettrackersvc.models.domain.Category;
import com.mypfinance.budgettrackersvc.models.dto.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CategoryMapper {

    public Category mapCategoryDtoToDomain(CategoryDto dto) {

        return Category.builder()
                .id(UUID.randomUUID())
                .color(dto.getColor())
                .name(dto.getName())
                .type(dto.getType())
                .build();
    }

    public CategoryDto mapCategoryToDto(Category domain) {

        return CategoryDto.builder()
                .color(domain.getColor())
                .name(domain.getName())
                .type(domain.getType())
                .build();
    }

}
