package com.mobiquity.amarshall.spotifysync.BroadcastReceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.mobiquity.amarshall.spotifysync.UI.Activites.MainActivity;

public class WebSocketReceiver extends BroadcastReceiver {

    private final MainActivity context;

    public WebSocketReceiver(MainActivity context) {
        this.context = context;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context.showToast("Hey there sexy world");

    }
}
