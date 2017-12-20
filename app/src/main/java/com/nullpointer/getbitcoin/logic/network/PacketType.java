package com.nullpointer.getbitcoin.logic.network;

/**
 * Created by Khaustov on 20.12.2017.
 */

public enum PacketType {
    GET_BALANCE(0), PUT_BALANCE(1), BTC_OUT_REQUEST(2);
    private int value;

    PacketType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
