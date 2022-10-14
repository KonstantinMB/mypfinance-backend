package com.mypfinance.budgettrackersvc.models.domain;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "expense_transaction")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseTransaction {

    @Id
    @NotNull
    private String id;

    @Column(name = "account_id")
    private String accountId;

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
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ExpenseTransaction that = (ExpenseTransaction) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
