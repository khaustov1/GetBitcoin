package com.nullpointer.getbitcoin.ui.uiTasks;

import android.widget.TextView;

/**
 * Created by Khaustov on 20.12.2017.
 */

public class UpdateBTCBalanceTask implements Runnable {
    private final TextView btcBalanceView;
    private final String balance;

    public UpdateBTCBalanceTask(TextView btcBalanceView, String balance) {
        this.btcBalanceView = btcBalanceView;
        this.balance = balance;
    }

    @Override
    public void run() {
        btcBalanceView.setText("Balance in BTC: " + balance);
    }
}
