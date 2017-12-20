package com.nullpointer.getbitcoin.logic.tapjoy;

import android.util.Log;

import com.nullpointer.getbitcoin.presenter.interfaces.IMainViewPresenter;
import com.tapjoy.TJGetCurrencyBalanceListener;
import com.tapjoy.TJSpendCurrencyListener;

/**
 * Created by Khaustov on 19.12.2017.
 */

public class TapJoyBalanceManager {
    private final IMainViewPresenter mainViewPresenter;
    private int currentBalance;

    public TapJoyBalanceManager(IMainViewPresenter mainViewPresenter) {
        this.mainViewPresenter = mainViewPresenter;
    }

    private final TJGetCurrencyBalanceListener balanceListener = new TJGetCurrencyBalanceListener() {
        @Override
        public void onGetCurrencyBalanceResponse(String currencyName, int balance) {
            Log.i("Tapjoy", "getCurrencyBalance returned " + currencyName + ":" + balance);
            mainViewPresenter.onCurrencyBalanceChanged();
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

    public int getCurrentBalance() {
        return currentBalance;
    }
}
