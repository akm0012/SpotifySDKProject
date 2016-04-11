package com.mobiquity.amarshall.spotifysync.BroadcastReceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.google.gson.Gson;
import com.mobiquity.amarshall.spotifysync.Interfaces.SpoqUpdateListener;
import com.mobiquity.amarshall.spotifysync.Models.SpoqModel;
import com.mobiquity.amarshall.spotifysync.Services.WebSocketService;
import com.mobiquity.amarshall.spotifysync.Utils.WebsocketClient;

public class WebSocketReceiver extends BroadcastReceiver {

    private final SpoqUpdateListener listener;

    public WebSocketReceiver(SpoqUpdateListener listener) {
        this.listener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String message = intent.getStringExtra(WebSocketService.WEB_SOCKET_SERVICE_BROADCAST);
        if (message.equalsIgnoreCase(WebsocketClient.TrackQueueCommands.ERROR)) {
            listener.onError(message);
        } else {
            Gson gson = new Gson();
            listener.onUpdate(gson.fromJson(message, SpoqModel.class));
        }
    }
}
