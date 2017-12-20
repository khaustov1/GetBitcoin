package com.nullpointer.getbitcoin.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nullpointer.getbitcoin.R;
import com.nullpointer.getbitcoin.presenter.MainViewPresenter;
import com.nullpointer.getbitcoin.presenter.interfaces.IMainViewPresenter;
import com.nullpointer.getbitcoin.ui.interfaces.IMainView;
import com.nullpointer.getbitcoin.ui.uiTasks.UpdateBTCBalanceTask;
import com.nullpointer.getbitcoin.ui.uiTasks.UpdateCurrencyBalanceTask;

/**
 * Created by Khaustov on 19.12.17.
 */

public class MainActivity extends AppCompatActivity implements IMainView {
    private final IMainViewPresenter mainPresenter = new MainViewPresenter(this);
    private Button button;
    private TextView currencyBalance;
    private TextView btcBalance;
    private Handler uiHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.show_offer_wall);
        currencyBalance = findViewById(R.id.currency_balance);
        btcBalance = findViewById(R.id.btc_balance);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onShowOfferWallAction();
            }
        });
        mainPresenter.beforeViewCreated();
    }

    @Override
    public void onShowOfferWallAction() {
        mainPresenter.showOfferWall();
    }

    @Override
    public void onCurrencyBalanceUpdate() {
        uiHandler.post(new UpdateCurrencyBalanceTask(currencyBalance, mainPresenter));
    }

    @Override
    public void onBTCBalanceUpdate() {
        uiHandler.post(new UpdateBTCBalanceTask(btcBalance, mainPresenter.getBTCBalance()));
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainPresenter.onViewCreated();
    }

    @Override
    protected void onStop() {
        mainPresenter.onViewDestroyed();
        super.onStop();
    }

}
