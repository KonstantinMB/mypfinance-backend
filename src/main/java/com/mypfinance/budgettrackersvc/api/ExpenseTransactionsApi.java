package com.mypfinance.budgettrackersvc.api;

import com.mypfinance.budgettrackersvc.exception.ResourceNotFoundException;
import com.mypfinance.budgettrackersvc.models.dto.TransactionDto;
import com.mypfinance.budgettrackersvc.models.mapper.TransactionMapper;
import com.mypfinance.budgettrackersvc.service.ExpenseTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1/budget-tracker/expense/transaction")
public class ExpenseTransactionsApi {

    private final ExpenseTransactionService service;

    private final TransactionMapper mapper;

    @Autowired
    public ExpenseTransactionsApi(ExpenseTransactionService service, TransactionMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE , consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionDto> createTransaction(@RequestBody TransactionDto request) throws ResourceNotFoundException {

        TransactionDto response = mapper.mapExpenseTransactionToDto(
                service.saveTransaction(mapper.mapTransactionDtoToExpenseTransaction(request)));

        return ResponseEntity.ok(response);
    }
}
