package com.mypfinance.budgettrackersvc.api;

import com.mypfinance.budgettrackersvc.exception.ResourceNotFoundException;
import com.mypfinance.budgettrackersvc.models.dto.CategoryDto;
import com.mypfinance.budgettrackersvc.models.dto.TransactionDto;
import com.mypfinance.budgettrackersvc.models.mapper.TransactionMapper;
import com.mypfinance.budgettrackersvc.service.ExpenseTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1/budget-tracker/expense/transactions")
public class ExpenseTransactionsApi {

    private final ExpenseTransactionService service;

    private final TransactionMapper mapper;

    @Autowired
    public ExpenseTransactionsApi(ExpenseTransactionService service, TransactionMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping(value = "/{transactionId}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionDto> getTransactionById(@PathVariable String transactionId)
            throws ResourceNotFoundException {

        return ResponseEntity.ok(mapper.mapExpenseTransactionToDto(
                service.getTransactionById(transactionId)));
    }

    @GetMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TransactionDto>> getTransactions(@AuthenticationPrincipal Jwt jwt)
            throws ResourceNotFoundException {

        String accountId = jwt.getClaimAsString("accountId");

        return ResponseEntity.ok(mapper.mapListOfExpenseTransactionsToDtos(
                service.getTransactions(accountId)));
    }

    @GetMapping(value = "/category", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TransactionDto>> getAllTransactionsByCategory(@RequestBody CategoryDto request)
            throws ResourceNotFoundException {

        return ResponseEntity.ok(mapper.mapListOfExpenseTransactionsToDtos(
                service.getTransactionsByCategoryName(request.getName())));
    }

    @GetMapping(value = "/date",consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TransactionDto>> getAllTransactionsByDate(@RequestBody TransactionDto request)
            throws ResourceNotFoundException {

        return ResponseEntity.ok(mapper.mapListOfExpenseTransactionsToDtos(
                service.getTransactionsByDate(request.getDate())));
    }

    @PutMapping(value = "/{transactionId}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionDto> modifyTransaction(
            @AuthenticationPrincipal Jwt jwt, @PathVariable String transactionId, @RequestBody TransactionDto request)
            throws ResourceNotFoundException {

        String accountId = jwt.getClaimAsString("accountId");

        return ResponseEntity.ok(mapper.mapExpenseTransactionToDto(
                service.modifyTransaction(transactionId, mapper.mapTransactionDtoToExpenseTransaction(accountId, request))));
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE , consumes = APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<TransactionDto> createTransaction(@AuthenticationPrincipal Jwt jwt, @RequestBody TransactionDto request)
            throws ResourceNotFoundException {

        String accountId = jwt.getClaimAsString("accountId");

        return ResponseEntity.ok(mapper.mapExpenseTransactionToDto(
                service.saveTransaction(mapper.mapTransactionDtoToExpenseTransaction(accountId, request))));
    }

    @DeleteMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteTransaction(@PathVariable String transactionId) throws ResourceNotFoundException {

        service.deleteTransactionById(transactionId);

        return ResponseEntity.ok().body(String.format("Transaction with ID: %s", transactionId));
    }
}
