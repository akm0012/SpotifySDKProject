package com.mobiquity.amarshall.spotifysync.Utils;


import android.os.AsyncTask;

import com.mobiquity.amarshall.spotifysync.Interfaces.WebSocketListener;
import com.mobiquity.amarshall.spotifysync.Models.SpoqTrack;
import com.mobiquity.amarshall.spotifysync.Models.SpoqUser;


/**
 * Created by jfowler on 4/11/16.
 */
public class WebSocketManager {
    private WebsocketClient websocketClient;
    private WebSocketListener webSocketListener;
    private WebsocketClient.SocketAsyncTask asyncTask;

    public WebSocketManager(WebSocketListener listener) {
        webSocketListener = listener;
        websocketClient = WebsocketClient.getInstance();
    }

    public boolean startConnection() {
        if (!websocketClient.isOpen()) {
            asyncTask = websocketClient.startConnection(webSocketListener);
            return true;
        }
        return false;
    }

    public void addTrack(SpoqTrack track) {
        AsyncTask.execute(websocketClient.addTrack(track));
    }

    public void joinPlaylist(SpoqUser user) {
        AsyncTask.execute(websocketClient.joinPlaylist(user));
    }

    public void createPlaylist(SpoqUser user) {
        AsyncTask.execute(websocketClient.createPlaylist(user));
    }

    public void leavePlaylist() {
        AsyncTask.execute(websocketClient.leavePlaylist());
    }

    public void downVoteTrack(SpoqTrack track) {
        AsyncTask.execute(websocketClient.downVoteTrack(track));
    }

    public void removeDownVote(SpoqTrack track) {
        AsyncTask.execute(websocketClient.removeDownVote(track));
    }

    public void interruptConnectionThread() {
        if (asyncTask != null) {
            asyncTask.cancel(true);
        }
    }
}
