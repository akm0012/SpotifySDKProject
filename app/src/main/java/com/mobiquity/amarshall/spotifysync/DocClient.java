package com.mobiquity.amarshall.spotifysync;

import android.util.Log;

import com.google.gson.Gson;

import org.glassfish.tyrus.client.ClientManager;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.websocket.ClientEndpointConfig;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.Session;

import kaaes.spotify.webapi.android.models.Track;

public class DocClient implements Runnable{
    private static CountDownLatch messageLatch;
    private Session currentSession;
    private TestSongListModel songListModel;

    public DocClient(){
        songListModel = new TestSongListModel();
    }

    @Override
    public void run() {
        try {
            messageLatch = new CountDownLatch(1);

            final ClientEndpointConfig cec = ClientEndpointConfig.Builder.create().build();
            URI uri = new URI(MainActivity.ADDRESS + ":" + MainActivity.PORT + MainActivity.END_ADDRESS);

            ClientManager manager = ClientManager.createClient();
            currentSession = manager.connectToServer(new Endpoint() {
                @Override
                public void onOpen(Session session, EndpointConfig config) {
                    session.addMessageHandler(new MessageHandler.Whole<String>() {
                        @Override
                        public void onMessage(String message) {
                            Log.i("Doc", "Receiving: " + message);
                            Gson gson = new Gson();
                            songListModel = gson.fromJson(message, TestSongListModel.class);
                            messageLatch.countDown();
                        }
                    });
                }
            }, cec, uri);
            messageLatch.await(100, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Runnable sendTrack(Track track){
        if(currentSession != null) {
            Gson gson = new Gson();
            final String json = gson.toJson(track);
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
}