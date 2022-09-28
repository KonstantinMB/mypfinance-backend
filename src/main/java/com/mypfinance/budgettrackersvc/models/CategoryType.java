package com.mypfinance.budgettrackersvc.models;

public enum CategoryType {
    EXPENSE("expense"),
    INCOME("income");

    private final String name;

    CategoryType(String name) { this.name = name; }

    public java.lang.String getName() {
        return name;
    }
}
