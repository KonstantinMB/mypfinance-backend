package com.mypfinance.budgettrackersvc.models.domain;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "transaction")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    private UUID id;

    /*
    * The type of the transaction. Could have only two values: EXPENSE/INCOME
    */
    @Column(name = "type")
    private String type;

    @NotNull
    @Column(name = "date")
    private LocalDate date;

    @Column(name = "expense_amount")
    private Double expenseAmount;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "description")
    private String description;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(type, that.type)) return false;
        if (!Objects.equals(date, that.date)) return false;
        if (!Objects.equals(expenseAmount, that.expenseAmount))
            return false;
        if (!Objects.equals(categoryName, that.categoryName)) return false;
        return Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (expenseAmount != null ? expenseAmount.hashCode() : 0);
        result = 31 * result + (categoryName != null ? categoryName.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
