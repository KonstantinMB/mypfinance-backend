package com.mypfinance.budgettrackersvc.models.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import static javax.persistence.CascadeType.*;
import static javax.persistence.CascadeType.DETACH;

@Entity
@Table(name = "income_transaction")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IncomeTransaction {

    @Id
    @NotNull
    private String id;

    @Column(name = "date")
    @NotNull
    private LocalDate date;

    @Column(name = "amount")
    @NotNull
    private String amount;

    @Column(name = "currency")
    @NotNull
    private String currency;

    @Column(name = "category_name")
    @NotNull
    private String categoryName;

    @Column(name = "description")
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IncomeTransaction that = (IncomeTransaction) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(date, that.date)) return false;
        if (!Objects.equals(amount, that.amount)) return false;
        if (!Objects.equals(currency, that.currency)) return false;
        if (!Objects.equals(categoryName, that.categoryName)) return false;
        return Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        result = 31 * result + (categoryName != null ? categoryName.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
