package com.mobiquity.amarshall.spotifysync.Utils;

import android.util.Log;

import com.google.gson.Gson;
import com.mobiquity.amarshall.spotifysync.Models.User;

import org.glassfish.tyrus.client.ClientManager;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.websocket.ClientEndpointConfig;
import javax.websocket.CloseReason;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.Session;

import kaaes.spotify.webapi.android.models.Track;

public class DocClient implements Runnable {
    private static CountDownLatch messageLatch;
    private Session currentSession;

    public DocClient() {
    }

    @Override
    public void run() {
        try {
            messageLatch = new CountDownLatch(1);

            final ClientEndpointConfig cec = ClientEndpointConfig.Builder.create().build();
            URI uri = new URI(Constants.ADDRESS + ":" + Constants.PORT + Constants.END_ADDRESS);

            ClientManager manager = ClientManager.createClient();
            currentSession = manager.connectToServer(new Endpoint() {
                @Override
                public void onOpen(Session session, EndpointConfig config) {
                    session.addMessageHandler(new MessageHandler.Whole<String>() {
                        @Override
                        public void onMessage(String message) {
                            Log.i("Doc", "Receiving: " + message);
                            Gson gson = new Gson();
                            //songListModel = gson.fromJson(message, TestSongListModel.class);
                            messageLatch.countDown();
                        }
                    });
                }

                @Override
                public void onClose(Session session, CloseReason closeReason) {
                    currentSession = null;
                    super.onClose(session, closeReason);
                }
            }, cec, uri);
            messageLatch.await(100, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Runnable sendTrack(Track track) {
        if (currentSession != null) {
            Gson gson = new Gson();
            final String json = TrackQueueCommands.ADD_SONG + gson.toJson(track);
            Log.i("Doc", "Sending: " + json);
            return new Runnable() {
                @Override
                public void run() {
                    try {
                        currentSession.getBasicRemote().sendText(json);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
        }
        return null;
    }

    public Runnable joinQueue(User user) {
        if (currentSession != null) {
            Gson gson = new Gson();
            final String json = TrackQueueCommands.JOIN_QUEUE + gson.toJson(user);
            Log.i("Doc", "Sending: " + json);
            return new Runnable() {
                @Override
                public void run() {
                    try {
                        currentSession.getBasicRemote().sendText(json);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
        }
        return null;
    }

    public Runnable startQueue(User user) {
        if (currentSession != null) {
            Gson gson = new Gson();
            final String json = TrackQueueCommands.START_QUEUE + gson.toJson(user);
            Log.i("Doc", "Sending: " + json);
            return new Runnable() {
                @Override
                public void run() {
                    try {
                        currentSession.getBasicRemote().sendText(json);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
        }
        return null;
    }

    static class TrackQueueCommands {
        public static final String START_QUEUE = "start_queue!";
        public static final String JOIN_QUEUE = "join_queue!";
        public static final String LEAVE_QUEUE = "leave_queue!";
        public static final String ADD_SONG = "add_song!";
        public static final String DOWN_VOTE = "down_vote!";
    }
}