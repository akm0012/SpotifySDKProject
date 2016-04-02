package com.mobiquity.amarshall.spotifysync.Services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;

import java.io.Serializable;

public class WebSocketService extends Service {

    public static final String WEB_SOCKET_SERVICE_BROADCAST = "SPOQ_WEB_SOCKET_SERVICE_BROADCAST";
    private WebSocketBinder webSocketBinder;

    public WebSocketService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        webSocketBinder = new WebSocketBinder(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return webSocketBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (intent != null && intent.getAction() != null) {
            switch (intent.getAction()) {
//                case TOGGLE_STREAM_INTENT_CODE:
//
//                    break;
//                case OPEN_APP_INTENT_CODE:
//                    Intent openIntent = new Intent(getApplicationContext(), MainActivity.class);
//                    openIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(openIntent);
//                    break;
            }
        }

        return START_STICKY;
    }

    private void sendBroadcast(String msg) {
        Intent broadcastIntent = new Intent(WEB_SOCKET_SERVICE_BROADCAST);
        broadcastIntent.putExtra(WEB_SOCKET_SERVICE_BROADCAST, msg);
        sendBroadcast(broadcastIntent);
    }

    public class WebSocketBinder extends Binder {
        private final WebSocketService webSocketService;

        public WebSocketBinder(WebSocketService service) {
            webSocketService = service;
        }

        public WebSocketService getRadioStreamService() {
            return webSocketService;
        }
    }
}
