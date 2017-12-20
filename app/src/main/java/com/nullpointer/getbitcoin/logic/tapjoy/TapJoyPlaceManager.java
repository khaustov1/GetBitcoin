package com.nullpointer.getbitcoin.logic.tapjoy;

import android.util.Log;

import com.tapjoy.TJActionRequest;
import com.tapjoy.TJError;
import com.tapjoy.TJPlacement;
import com.tapjoy.TJPlacementListener;
import com.tapjoy.Tapjoy;

/**
 * Created by Khaustov on 19.12.2017.
 */

public class TapJoyPlaceManager implements TJPlacementListener, IPlaceManager {
    private TJPlacement offerWall;

    @Override
    public void initialize() {
        if (Tapjoy.isConnected()) {
            this.offerWall = Tapjoy.getPlacement("OfferwallPlacement", this);
            offerWall.requestContent();
        }
    }

    @Override
    public void showOfferWall() {
        //ToDo: it should be available too
        if (offerWall != null) {
            if (offerWall.isContentReady()) {
                offerWall.showContent();
            }
        }
    }

    @Override
    public void onRequestSuccess(TJPlacement tjPlacement) {

    }

    @Override
    public void onRequestFailure(TJPlacement tjPlacement, TJError tjError) {
        Log.e("PlaceManager", "Request error for " + tjPlacement.getName()
                + " problem is " + tjError.message);
    }

    @Override
    public void onContentReady(TJPlacement tjPlacement) {

    }

    @Override
    public void onContentShow(TJPlacement tjPlacement) {

    }

    @Override
    public void onContentDismiss(TJPlacement tjPlacement) {
        offerWall.requestContent();
    }

    @Override
    public void onPurchaseRequest(TJPlacement tjPlacement, TJActionRequest tjActionRequest, String s) {

    }

    @Override
    public void onRewardRequest(TJPlacement tjPlacement, TJActionRequest tjActionRequest, String s, int i) {

    }
}
