package com.mypfinance.budgettrackersvc.models.domain;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "expense_category")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseCategory {

    @Id
    private String id;

    @Column(name = "account_id")
    private String accountId;

    @Column(name = "name")
    private String name;

    @Column(name = "color")
    @Nullable
    private String color;

    @OneToMany(mappedBy = "expenseCategory")
    private List<ExpenseTransaction> expenseTransactions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExpenseCategory that = (ExpenseCategory) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(name, that.name)) return false;
        return Objects.equals(color, that.color);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        return result;
    }
}
