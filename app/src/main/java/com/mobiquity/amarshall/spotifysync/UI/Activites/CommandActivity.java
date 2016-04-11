package com.mobiquity.amarshall.spotifysync.UI.Activites;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.mobiquity.amarshall.spotifysync.BroadcastReceivers.WebSocketReceiver;
import com.mobiquity.amarshall.spotifysync.Interfaces.SpoqUpdateListener;
import com.mobiquity.amarshall.spotifysync.Models.SpoqModel;
import com.mobiquity.amarshall.spotifysync.Models.SpoqTrack;
import com.mobiquity.amarshall.spotifysync.Models.SpoqUser;
import com.mobiquity.amarshall.spotifysync.Services.WebSocketService;
import com.mobiquity.amarshall.spotifysync.UI.BaseActivity;

/**
 * Created by jfowler on 4/11/16.
 */
public class CommandActivity extends BaseActivity implements ServiceConnection, SpoqUpdateListener {

    protected SpoqModel spoqModel;
    private boolean isBound = false;
    private WebSocketService.WebSocketBinder binder = null;

    @Override
    protected void onPause() {
        super.onPause();
        if (isBound) {
            unbindService(this);
            isBound = false;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        bindToWebSocketService();
        startConnection();
        WebSocketReceiver webSocketReceiver = new WebSocketReceiver(this);
        IntentFilter webSocketIntentFilter = new IntentFilter(WebSocketService.WEB_SOCKET_SERVICE_BROADCAST);
        this.registerReceiver(webSocketReceiver, webSocketIntentFilter);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        isBound = true;
        binder = (WebSocketService.WebSocketBinder) service;
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        isBound = true;
        binder = null;
    }

    private void bindToWebSocketService() {
        if (!isBound) {
            Intent startService = new Intent(this, WebSocketService.class);
            //TODO: May not want to startTheServiceHere
            startService(startService);
            bindService(startService, this, BIND_AUTO_CREATE);
        }
    }

    @Override
    public void onUpdate(SpoqModel model) {
        spoqModel = model;
        Log.i("doc", "Broadcast update received");
    }

    @Override
    public void onError(String message) {
        showToast(message);
        Log.i("doc", "Broadcast error received");
    }

    public void startConnection() {
        if (binder == null) {
            return;
        }
        binder.getWebSocketService().getWebSocketManager().startConnection();
    }

    public void addTrack(SpoqTrack track) {
        if (binder == null) {
            return;
        }
        binder.getWebSocketService().getWebSocketManager().addTrack(track);
    }

    public void joinPlaylist(SpoqUser user) {
        if (binder == null) {
            return;
        }
        binder.getWebSocketService().getWebSocketManager().joinPlaylist(user);
    }

    public void createPlaylist(SpoqUser user) {
        if (binder == null) {
            return;
        }
        binder.getWebSocketService().getWebSocketManager().createPlaylist(user);
    }

    public void leavePlaylist() {
        if (binder == null) {
            return;
        }
        binder.getWebSocketService().getWebSocketManager().leavePlaylist();
    }

    public void downVoteTrack(SpoqTrack track) {
        if (binder == null) {
            return;
        }
        binder.getWebSocketService().getWebSocketManager().downVoteTrack(track);
    }

    public void removeDownVote(SpoqTrack track) {
        if (binder == null) {
            return;
        }
        binder.getWebSocketService().getWebSocketManager().removeDownVote(track);
    }
}
