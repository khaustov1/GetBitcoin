package com.nullpointer.getbitcoin;

import com.tapjoy.TJConnectListener;
import com.tapjoy.Tapjoy;

import java.lang.ref.WeakReference;
import java.util.Hashtable;

/**
 * Created by Khaustov on 19.12.17.
 */

public class TapJoyManager implements TJConnectListener {
    private final WeakReference<MainActivity> contextWeakReference;

    public TapJoyManager(MainActivity mainActivity) {
        contextWeakReference = new WeakReference<>(mainActivity);
    }

    public void initialize() {
        if (contextWeakReference.get() != null) {
            Tapjoy.connect(contextWeakReference.get(),
                    contextWeakReference.get().getString(R.string.api_key), new Hashtable(), this);
            Tapjoy.setDebugEnabled(true);
        } else {
            throw new RuntimeException("Main activity is dead");
        }
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

    }

    @Override
    public void onConnectFailure() {

    }
}
