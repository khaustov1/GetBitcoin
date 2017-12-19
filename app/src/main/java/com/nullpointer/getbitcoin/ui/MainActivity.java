package com.nullpointer.getbitcoin.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.nullpointer.getbitcoin.R;
import com.nullpointer.getbitcoin.presenter.MainViewPresenter;
import com.nullpointer.getbitcoin.presenter.interfaces.IMainViewPresenter;
import com.nullpointer.getbitcoin.ui.interfaces.IMainView;

/**
 * Created by Khaustov on 19.12.17.
 */

public class MainActivity extends AppCompatActivity implements IMainView {
    private final IMainViewPresenter mainPresenter = new MainViewPresenter(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainPresenter.beforeViewCreated();
    }

    @Override
    protected void onStart() {
        mainPresenter.onViewCreated();
        super.onStart();
    }

    @Override
    protected void onStop() {
        mainPresenter.onViewDestroyed();
        super.onStop();
    }

}
