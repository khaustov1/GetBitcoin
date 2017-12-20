package com.nullpointer.getbitcoin.presenter;

import com.nullpointer.getbitcoin.logic.tapjoy.ITapJoyManager;
import com.nullpointer.getbitcoin.logic.tapjoy.TapJoyManager;
import com.nullpointer.getbitcoin.presenter.interfaces.IMainViewPresenter;
import com.nullpointer.getbitcoin.ui.MainActivity;
import com.nullpointer.getbitcoin.ui.interfaces.IMainView;

/**
 * Created by Khaustov on 19.12.2017.
 */

public class MainViewPresenter implements IMainViewPresenter {
    private final ITapJoyManager tapJoyManager;

    public MainViewPresenter(IMainView mainView) {
        this.tapJoyManager = new TapJoyManager((MainActivity) mainView);
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
