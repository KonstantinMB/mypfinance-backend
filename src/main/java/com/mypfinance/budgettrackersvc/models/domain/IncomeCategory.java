package com.mypfinance.budgettrackersvc.models.domain;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "income_category")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IncomeCategory {

    @Id
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "color")
    @Nullable
    private String color;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IncomeCategory category = (IncomeCategory) o;

        if (!Objects.equals(id, category.id)) return false;
        if (!Objects.equals(name, category.name))
            return false;
        return Objects.equals(color, category.color);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        return result;
    }
}
