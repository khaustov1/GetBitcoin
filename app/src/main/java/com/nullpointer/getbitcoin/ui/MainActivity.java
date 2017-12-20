package com.nullpointer.getbitcoin.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.nullpointer.getbitcoin.R;
import com.nullpointer.getbitcoin.presenter.MainViewPresenter;
import com.nullpointer.getbitcoin.presenter.interfaces.IMainViewPresenter;
import com.nullpointer.getbitcoin.ui.interfaces.IMainView;

/**
 * Created by Khaustov on 19.12.17.
 */

public class MainActivity extends AppCompatActivity implements IMainView {
    private final IMainViewPresenter mainPresenter = new MainViewPresenter(this);
    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.show_offer_wall);
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
