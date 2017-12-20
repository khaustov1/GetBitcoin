package com.nullpointer.getbitcoin.logic.tapjoy;

/**
 * Created by Khaustov on 19.12.2017.
 */

public interface ITapJoyManager {
    void initialize();

    void start();

    void stop();

    void spendCurrency(int amount);

    void showOfferWall();

    int getCurrencyBalance();
}
