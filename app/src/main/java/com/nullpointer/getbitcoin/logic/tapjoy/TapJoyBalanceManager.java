package com.nullpointer.getbitcoin.logic.tapjoy;

import android.util.Log;

import com.tapjoy.TJGetCurrencyBalanceListener;
import com.tapjoy.TJSpendCurrencyListener;

/**
 * Created by Khaustov on 19.12.2017.
 */

public class TapJoyBalanceManager {
    private int currentBalance;

    private final TJGetCurrencyBalanceListener balanceListener = new TJGetCurrencyBalanceListener() {
        @Override
        public void onGetCurrencyBalanceResponse(String currencyName, int balance) {
            Log.i("Tapjoy", "getCurrencyBalance returned " + currencyName + ":" + balance);
            currentBalance = balance;
        }

        @Override
        public void onGetCurrencyBalanceResponseFailure(String error) {
            Log.i("Tapjoy", "getCurrencyBalance error: " + error);
        }
    };
    private final TJSpendCurrencyListener spendCurrencyListener = new TJSpendCurrencyListener() {
        @Override
        public void onSpendCurrencyResponse(String currencyName, int balance) {
            Log.i("Tapjoy", currencyName + ": " + balance);
        }

        @Override
        public void onSpendCurrencyResponseFailure(String error) {
            Log.i("Tapjoy", "spendCurrency error: " + error);
        }
    };

    public TJGetCurrencyBalanceListener getBalanceListener() {
        return balanceListener;
    }

    public TJSpendCurrencyListener getSpendCurrencyListener() {
        return spendCurrencyListener;
    }
}
