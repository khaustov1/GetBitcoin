package com.nullpointer.getbitcoin.logic.tapjoy;

import com.tapjoy.TJGetCurrencyBalanceListener;
import com.tapjoy.Tapjoy;

/**
 * Created by Khaustov on 20.12.2017.
 */

public class GetCurrencyBalanceTask implements Runnable {

    private final TJGetCurrencyBalanceListener listener;

    public GetCurrencyBalanceTask(TJGetCurrencyBalanceListener listener) {
        this.listener = listener;
    }

    @Override
    public void run() {
        Tapjoy.getCurrencyBalance(listener);
    }
}
