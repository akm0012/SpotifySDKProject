package com.mobiquity.amarshall.spotifysync.Services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.google.gson.Gson;
import com.mobiquity.amarshall.spotifysync.Interfaces.WebSocketListener;
import com.mobiquity.amarshall.spotifysync.Models.BroadcastContainer;
import com.mobiquity.amarshall.spotifysync.Models.SpoqModel;
import com.mobiquity.amarshall.spotifysync.Models.SpoqTrack;
import com.mobiquity.amarshall.spotifysync.Utils.DAO;
import com.mobiquity.amarshall.spotifysync.Utils.SpotifyInteractor;
import com.mobiquity.amarshall.spotifysync.Utils.WebSocketManager;

import java.util.HashMap;
import java.util.List;

import kaaes.spotify.webapi.android.models.Track;

public class WebSocketService extends Service implements WebSocketListener {

    public static final String WEB_SOCKET_SERVICE_BROADCAST = "SPOQ_WEB_SOCKET_SERVICE_BROADCAST";
    private WebSocketBinder webSocketBinder;
    private WebSocketManager webSocketManager;
    private SpoqModel spoqModel;
    private HashMap<String, Track> downloadedTracks;
    SpotifyInteractor interactor;

    public WebSocketService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        webSocketBinder = new WebSocketBinder(this);
        webSocketManager = new WebSocketManager(this);
        webSocketManager.startConnection();
        interactor = new SpotifyInteractor(DAO.getInstance(this).getPrefSpotifyToken());
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

    @Override
    public void onConnectionClosed() {
        //TODO - Differentiate between user/system closure of the connection
    }

    @Override
    public void onConnectionOpen() {

    }

    @Override
    public void onSuccess(final SpoqModel model) {
        List<SpoqTrack> trackListToDownload = WebSocketManager.getPlaylistDiff(spoqModel.getPlaylist().getTrackList(), downloadedTracks);
        interactor.getTracksById(trackListToDownload, new SpotifyInteractor.TrackListener() {
            @Override
            public void onTracksRetrieved(HashMap<String, Track> tracks) {
                downloadedTracks.putAll(tracks);
                BroadcastContainer container = new BroadcastContainer();
                container.setSpoqModel(model);
                container.setSpotifyTracks(tracks);
                Gson gson = new Gson();
                sendBroadcast(gson.toJson(container));
            }
        });
        spoqModel = model;
    }

    @Override
    public void onFailure(String error) {
        sendBroadcast(error);
    }

    public WebSocketManager getWebSocketManager() {
        return webSocketManager;
    }

    public class WebSocketBinder extends Binder {
        private final WebSocketService webSocketService;

        public WebSocketBinder(WebSocketService service) {
            webSocketService = service;
        }

        public WebSocketService getWebSocketService() {
            return webSocketService;
        }
    }
}
