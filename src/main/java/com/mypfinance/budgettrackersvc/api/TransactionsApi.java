package com.mypfinance.budgettrackersvc.api;

import com.mypfinance.budgettrackersvc.exception.RequestNotValidException;
import com.mypfinance.budgettrackersvc.service.TransactionService;
import com.mypfinance.budgettrackersvc.validation.TypeCheckup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1/budget-tracker/transaction")
public class TransactionsApi {

    private final TransactionService service;

    private final TypeCheckup typeCheckup;

    @Autowired
    public TransactionsApi(TransactionService service, TypeCheckup typeCheckup) {
        this.service = service;
        this.typeCheckup = typeCheckup;
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE , consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionDto> createTransaction(@RequestBody TransactionDto request) throws RequestNotValidException {

        typeCheckup.checkTypeCase(request.getType());

        TransactionDto response = transactionMapper.mapTransactionToDto(
                service.saveTransaction(transactionMapper.mapTransactionDtoToDomain(request)));

        return ResponseEntity.ok(response);
    }
}
