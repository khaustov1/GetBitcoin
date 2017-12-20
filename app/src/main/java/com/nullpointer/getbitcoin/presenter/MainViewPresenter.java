package com.nullpointer.getbitcoin.presenter;

import com.nullpointer.getbitcoin.logic.btc.BTCBalanceManager;
import com.nullpointer.getbitcoin.logic.btc.IBTCBalanceManager;
import com.nullpointer.getbitcoin.logic.tapjoy.ITapJoyManager;
import com.nullpointer.getbitcoin.logic.tapjoy.TapJoyManager;
import com.nullpointer.getbitcoin.presenter.interfaces.IMainViewPresenter;
import com.nullpointer.getbitcoin.ui.MainActivity;
import com.nullpointer.getbitcoin.ui.interfaces.IMainView;

import java.lang.ref.WeakReference;

/**
 * Created by Khaustov on 19.12.2017.
 */

public class MainViewPresenter implements IMainViewPresenter {
    private final ITapJoyManager tapJoyManager;
    private final WeakReference<IMainView> mainViewWeakReference;
    private final IBTCBalanceManager btcBalanceManager;

    public MainViewPresenter(IMainView mainView) {
        this.mainViewWeakReference = new WeakReference<>(mainView);
        this.tapJoyManager = new TapJoyManager((MainActivity) mainView, this);
        this.btcBalanceManager = new BTCBalanceManager();
    }

    @Override
    public void onCurrencyBalanceChanged() {
        if (mainViewWeakReference.get() != null) {
            mainViewWeakReference.get().onCurrencyBalanceUpdate();
            btcBalanceManager.onCurrencyBalanceChanged(tapJoyManager.getCurrencyBalance());
            mainViewWeakReference.get().onBTCBalanceUpdate();
        }
    }

    @Override
    public int getCurrencyBalance() {
        return tapJoyManager.getCurrencyBalance();
    }

    @Override
    public String getBTCBalance() {
        return btcBalanceManager.getBTCBalance();
    }

    @Override
    public void showOfferWall() {
        tapJoyManager.showOfferWall();
    }

    @Override
    public void beforeViewCreated() {
        tapJoyManager.initialize();
    }

    @Override
    public void onViewCreated() {
        tapJoyManager.start();
    }

    @Override
    public void onViewDestroyed() {
        tapJoyManager.stop();
    }
}
