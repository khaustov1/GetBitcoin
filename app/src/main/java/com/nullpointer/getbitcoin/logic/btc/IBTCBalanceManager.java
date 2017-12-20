package com.nullpointer.getbitcoin.logic.btc;

/**
 * Created by Khaustov on 20.12.2017.
 */

public interface IBTCBalanceManager {
    void onCurrencyBalanceChanged(int currencyAmount);
    String getBTCBalance();
}
