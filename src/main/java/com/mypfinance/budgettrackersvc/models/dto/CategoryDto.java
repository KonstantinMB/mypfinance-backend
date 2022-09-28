package com.mypfinance.budgettrackersvc.models.dto;


import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CategoryDto {

    private String type;

    private String name;

    private String color;
}
