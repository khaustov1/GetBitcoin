package com.nullpointer.getbitcoin.ui.uiTasks;

import android.view.TextureView;
import android.widget.TextView;

import com.nullpointer.getbitcoin.presenter.interfaces.IMainViewPresenter;

/**
 * Created by Khaustov on 20.12.2017.
 */

public class UpdateCurrencyBalanceTask implements Runnable {
    private final TextView balanceView;
    private final IMainViewPresenter mainViewPresenter;

    public UpdateCurrencyBalanceTask(TextView balanceView, IMainViewPresenter presenter) {
        this.balanceView = balanceView;
        this.mainViewPresenter = presenter;
    }

    @Override
    public void run() {
        balanceView.setText("Balance : " + mainViewPresenter.getCurrencyBalance());
    }
}
