package com.mypfinance.budgettrackersvc.models.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

import static javax.persistence.CascadeType.*;
import static javax.persistence.CascadeType.DETACH;

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

    @ManyToOne(cascade = {PERSIST, MERGE, REFRESH, DETACH})
    @JoinColumn(name = "account_category_id", referencedColumnName = "id")
    @JsonIgnore
    private Account account;

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
