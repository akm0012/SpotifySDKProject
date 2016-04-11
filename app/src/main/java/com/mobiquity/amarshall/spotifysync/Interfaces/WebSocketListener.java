package com.mobiquity.amarshall.spotifysync.Interfaces;

import com.mobiquity.amarshall.spotifysync.Models.SpoqModel;

/**
 * Created by jfowler on 4/8/16.
 */
public interface WebSocketListener {
    void onConnectionClosed();

    void onConnectionOpen();

    void onSuccess(SpoqModel model);

    void onFailure(String error);
}
