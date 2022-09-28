package com.mypfinance.budgettrackersvc.validation;

import com.mypfinance.budgettrackersvc.exception.RequestNotValidException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Locale;

import static com.mypfinance.budgettrackersvc.models.CategoryType.EXPENSE;
import static com.mypfinance.budgettrackersvc.models.CategoryType.INCOME;

// Should make it as an annotation to add to the dto object
@Component
@RequiredArgsConstructor
public class TypeCheckup {

    public void checkTypeCase(String type) throws RequestNotValidException {

        if((!type.toLowerCase(Locale.ROOT).equals(EXPENSE.getName()))
                && (!type.toLowerCase(Locale.ROOT).equals(INCOME.getName()))) {

            throw new RequestNotValidException("$.type", "Invalid value.", HttpStatus.BAD_REQUEST);
        }
    }
}
