package com.mypfinance.accountsvc.models.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name="accounts")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Account {

    @Id
    private String id;

    @Column(name = "username")
    @NotBlank(message = "Please provide a value for username.")
    private String username;

    @Column(name = "password")
    @NotBlank
    private String password;

    @Column(name = "first_name")
    @NotBlank(message = "Please provide at least the account user's first name.")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    @NotBlank(message = "Please provide an email.")
    @Email
    private String email;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "registration_date")
    private LocalDateTime registrationDate;

    @Column(name="roles")
    private String roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (!Objects.equals(id, account.id)) return false;
        if (!Objects.equals(username, account.username)) return false;
        if (!Objects.equals(password, account.password)) return false;
        if (!Objects.equals(firstName, account.firstName)) return false;
        if (!Objects.equals(lastName, account.lastName)) return false;
        if (!Objects.equals(email, account.email)) return false;
        if (!Objects.equals(balance, account.balance)) return false;
        return Objects.equals(registrationDate, account.registrationDate);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        result = 31 * result + (registrationDate != null ? registrationDate.hashCode() : 0);
        return result;
    }
}
