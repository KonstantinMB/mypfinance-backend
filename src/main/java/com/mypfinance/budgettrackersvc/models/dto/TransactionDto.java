package com.mypfinance.budgettrackersvc.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TransactionDto {

    private LocalDate date;

    private String categoryName;

    private String amount;

    private String currency;

    private String description;
}
