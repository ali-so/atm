package com.kiandigital.atm.model;

import lombok.Getter;

@Getter
public enum TransactionType {
    deposit(0, "deposit", "deposit"),
    withdraw(1, "withdraw", "withdraw");

    private final Integer index;
    private final String title;
    private final String alias;

    TransactionType(Integer index, String title, String alias) {
        this.index = index;
        this.title = title;
        this.alias = alias;
    }

    public static TransactionType valueOfIndex(Integer index) {
        for (TransactionType transactionType : values()) {
            if (transactionType.getIndex().equals(index)) {
                return transactionType;
            }
        }
        throw new IllegalArgumentException("TransactionType cannot be resolved for code " + index);
    }
}
