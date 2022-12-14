package com.mypfinance.budgettrackersvc.models.mapper;

import com.mypfinance.budgettrackersvc.models.domain.ExpenseTransaction;
import com.mypfinance.budgettrackersvc.models.domain.IncomeTransaction;
import com.mypfinance.budgettrackersvc.models.dto.TransactionDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Component
public class TransactionMapper {

    public ExpenseTransaction mapTransactionDtoToExpenseTransaction(String accountId, TransactionDto dto) {

        return ExpenseTransaction.builder()
                .id(UUID.randomUUID().toString())
                .accountId(accountId)
                .date(dto.getDate())
                .categoryName(dto.getCategoryName().toLowerCase(Locale.ROOT))
                .amount(dto.getAmount())
                .currency(dto.getCurrency().toUpperCase(Locale.ROOT))
                .description(dto.getDescription())
                .build();
    }

    public IncomeTransaction mapTransactionDtoToIncomeTransaction(String accountId, TransactionDto dto) {

        return IncomeTransaction.builder()
                .id(UUID.randomUUID().toString())
                .accountId(accountId)
                .date(dto.getDate())
                .categoryName(dto.getCategoryName().toLowerCase(Locale.ROOT))
                .amount(dto.getAmount())
                .currency(dto.getCurrency().toUpperCase(Locale.ROOT))
                .description(dto.getDescription())
                .build();
    }

    public TransactionDto mapExpenseTransactionToDto(ExpenseTransaction domain) {

        return TransactionDto.builder()
                .date(domain.getDate())
                .categoryName(domain.getCategoryName())
                .amount(domain.getAmount())
                .currency(domain.getCurrency())
                .description(domain.getDescription())
                .build();
    }

    public TransactionDto mapIncomeTransactionToDto(IncomeTransaction domain) {

        return TransactionDto.builder()
                .date(domain.getDate())
                .categoryName(domain.getCategoryName())
                .amount(domain.getAmount())
                .currency(domain.getCurrency())
                .description(domain.getDescription())
                .build();
    }

    public List<TransactionDto> mapListOfExpenseTransactionsToDtos (List<ExpenseTransaction> transactions){

        List<TransactionDto> dtos = new ArrayList<>();
        for(ExpenseTransaction transaction : transactions){
            dtos.add(mapExpenseTransactionToDto(transaction));
        }
        return  dtos;
    }

    public List<TransactionDto> mapListOfIncomeTransactionsToDtos (List<IncomeTransaction> transactions){

        List<TransactionDto> dtos = new ArrayList<>();
        for(IncomeTransaction transaction : transactions){
            dtos.add(mapIncomeTransactionToDto(transaction));
        }
        return  dtos;
    }
}
