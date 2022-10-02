package com.mypfinance.budgettrackersvc.models.dto;

import lombok.*;

import java.time.LocalDate;

@Builder
@Data
public class TransactionDto {

    private LocalDate date;

    private String categoryName;

    private String amount;

    private String currency;

    private String description;
}
