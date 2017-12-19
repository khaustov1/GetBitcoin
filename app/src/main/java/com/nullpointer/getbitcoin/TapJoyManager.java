package com.nullpointer.getbitcoin;

import android.util.Log;

import com.tapjoy.TJConnectListener;
import com.tapjoy.TJGetCurrencyBalanceListener;
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

    private final WeakReference<MainActivity> contextWeakReference;
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

    public TapJoyManager(MainActivity mainActivity) {
        contextWeakReference = new WeakReference<>(mainActivity);
    }

    public void initialize() {
        if (contextWeakReference.get() != null) {
            Tapjoy.connect(contextWeakReference.get(),
                    contextWeakReference.get().getString(R.string.api_key),
                    new Hashtable(), this);
            Tapjoy.setDebugEnabled(true);
        } else {
            throw new RuntimeException("Main activity is dead");
        }
        executorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                Tapjoy.getCurrencyBalance(balanceListener);
            }
        }, GET_BALANCE_START_DELAY, GET_BALANCE_INTERVAL, TimeUnit.SECONDS);
    }

    public void start() {
        if (contextWeakReference.get() != null) {
            Tapjoy.onActivityStart(contextWeakReference.get());
        }
    }

    public void stop() {
        if (contextWeakReference.get() != null) {
            Tapjoy.onActivityStop(contextWeakReference.get());
        }
    }

    @Override
    public void onConnectSuccess() {
        Log.d("Tapjoy", "Connection established successfully");
    }

    @Override
    public void onConnectFailure() {
        Log.e("Tapjoy", "Connection failed");
    }
}
