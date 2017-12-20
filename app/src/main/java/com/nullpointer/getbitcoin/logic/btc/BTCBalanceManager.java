package com.nullpointer.getbitcoin.logic.btc;

import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * Created by Khaustov on 20.12.2017.
 */

public class BTCBalanceManager implements IBTCBalanceManager {
    private static final String CONVERTER_URL = "https://blockchain.info/tobtc";
    private OkHttpClient client = new OkHttpClient();
    private String currentBalance;

    @Override
    public void onCurrencyBalanceChanged(int currencyAmount) {
        BigDecimal currency = new BigDecimal(currencyAmount);
        BigDecimal hundred = new BigDecimal(100);
        BigDecimal currencyInUSD = currency.divide(hundred,2, BigDecimal.ROUND_HALF_UP);
        HttpUrl.Builder urlBuilder = HttpUrl.parse(CONVERTER_URL).newBuilder();
        urlBuilder.addQueryParameter("currency", "USD");
        urlBuilder.addQueryParameter("value", currencyInUSD.toPlainString());
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (response != null) {
            try {
                currentBalance = response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBTCBalance() {
        return currentBalance;
    }
}
