package com.nullpointer.getbitcoin.logic.tapjoy;

import android.util.Log;

import com.nullpointer.getbitcoin.R;
import com.nullpointer.getbitcoin.presenter.interfaces.IMainViewPresenter;
import com.nullpointer.getbitcoin.ui.MainActivity;
import com.tapjoy.TJConnectListener;
import com.tapjoy.Tapjoy;

import java.lang.ref.WeakReference;
import java.util.Hashtable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Khaustov on 19.12.17.
 */

public class TapJoyManager implements TJConnectListener, ITapJoyManager {
    private static final int GET_BALANCE_INTERVAL = 60;
    private static final int GET_BALANCE_START_DELAY = 0;

    private final WeakReference<MainActivity> activityWeakReference;
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private final TapJoyBalanceManager balanceManager;
    private final TapJoyPlaceManager placeManager;
    private final IMainViewPresenter mainViewPresenter;

    public TapJoyManager(MainActivity mainActivity, IMainViewPresenter mainViewPresenter) {
        this.activityWeakReference = new WeakReference<>(mainActivity);
        this.placeManager = new TapJoyPlaceManager();
        this.balanceManager = new TapJoyBalanceManager(mainViewPresenter);
        this.mainViewPresenter = mainViewPresenter;
    }

    @Override
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

    @Override
    public void spendCurrency(int amount) {
        Tapjoy.spendCurrency(amount, balanceManager.getSpendCurrencyListener());
    }

    @Override
    public void start() {
        if (activityWeakReference.get() != null) {
            Tapjoy.onActivityStart(activityWeakReference.get());
        }
    }

    @Override
    public void stop() {
        if (activityWeakReference.get() != null) {
            Tapjoy.onActivityStop(activityWeakReference.get());
        }
    }

    @Override
    public void showOfferWall() {
        placeManager.showOfferWall();
    }

    @Override
    public int getCurrencyBalance() {
        return balanceManager.getCurrentBalance();
    }

    @Override
    public void onConnectSuccess() {
        Log.d("Tapjoy", "Connection established successfully");
        placeManager.initialize();
        executorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                Tapjoy.getCurrencyBalance(balanceManager.getBalanceListener());
            }
        }, GET_BALANCE_START_DELAY, GET_BALANCE_INTERVAL, TimeUnit.SECONDS);
    }

    @Override
    public void onConnectFailure() {
        Log.e("Tapjoy", "Connection failed");
    }
}
