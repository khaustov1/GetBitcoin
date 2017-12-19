package com.nullpointer.getbitcoin;

import android.util.Log;

import com.nullpointer.getbitcoin.ui.MainActivity;
import com.tapjoy.TJConnectListener;
import com.tapjoy.TJGetCurrencyBalanceListener;
import com.tapjoy.TJSpendCurrencyListener;
import com.tapjoy.Tapjoy;

import java.lang.ref.WeakReference;
import java.util.Hashtable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Khaustov on 19.12.17.
 */

public class TapJoyManager implements TJConnectListener {
    private static final int GET_BALANCE_INTERVAL = 30;
    private static final int GET_BALANCE_START_DELAY = 10;

    private final WeakReference<MainActivity> activityWeakReference;
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private final TJGetCurrencyBalanceListener balanceListener = new TJGetCurrencyBalanceListener() {
        @Override
        public void onGetCurrencyBalanceResponse(String currencyName, int balance) {
            Log.i("Tapjoy", "getCurrencyBalance returned " + currencyName + ":" + balance);
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

    public TapJoyManager(MainActivity mainActivity) {
        activityWeakReference = new WeakReference<>(mainActivity);
    }

    public void initialize() {
        if (activityWeakReference.get() != null) {
            Tapjoy.connect(activityWeakReference.get(),
                    activityWeakReference.get().getString(R.string.api_key),
                    new Hashtable(), this);
            Tapjoy.setDebugEnabled(true);
        } else {
            throw new RuntimeException("Main activity is dead");
        }
    }

    public void spendCurrency(int amount) {
        Tapjoy.spendCurrency(amount, spendCurrencyListener);
    }

    public void start() {
        if (activityWeakReference.get() != null) {
            Tapjoy.onActivityStart(activityWeakReference.get());
        }
    }

    public void stop() {
        if (activityWeakReference.get() != null) {
            Tapjoy.onActivityStop(activityWeakReference.get());
            activityWeakReference.clear();
        }
    }

    @Override
    public void onConnectSuccess() {
        Log.d("Tapjoy", "Connection established successfully");
        executorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                Tapjoy.getCurrencyBalance(balanceListener);
            }
        }, GET_BALANCE_START_DELAY, GET_BALANCE_INTERVAL, TimeUnit.SECONDS);
    }

    @Override
    public void onConnectFailure() {
        Log.e("Tapjoy", "Connection failed");
    }
}
