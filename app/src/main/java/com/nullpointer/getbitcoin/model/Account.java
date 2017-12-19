package com.nullpointer.getbitcoin.model;

import java.math.BigInteger;

/**
 * Created by Khaustov on 19.12.17.
 */

public class Account {
    private final String accountUUID;
    private final BigInteger currencyBalance;

    public Account(String accountUUID, BigInteger currentBalance) {
        this.accountUUID = accountUUID;
        this.currencyBalance = currentBalance;
    }

    public BigInteger getCurrencyBalance() {
        return currencyBalance;
    }

    public String getAccountUUID() {
        return accountUUID;
    }
}
