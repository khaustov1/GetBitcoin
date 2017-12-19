package com.nullpointer.getbitcoin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Khaustov on 19.12.17.
 */

public class MainActivity extends AppCompatActivity {
    private final TapJoyManager tapJoyManager = new TapJoyManager(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tapJoyManager.initialize();
    }

    @Override
    protected void onStart() {
        tapJoyManager.start();
        super.onStart();
    }

    @Override
    protected void onStop() {
        tapJoyManager.stop();
        super.onStop();
    }
}
