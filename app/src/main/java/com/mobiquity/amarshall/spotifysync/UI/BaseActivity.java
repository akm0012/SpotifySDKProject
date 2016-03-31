package com.mobiquity.amarshall.spotifysync.UI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mobiquity.amarshall.spotifysync.Interfaces.TrackListener;
import com.mobiquity.amarshall.spotifysync.Models.SpoqModel;
import com.mobiquity.amarshall.spotifysync.Utils.DAO;
import com.mobiquity.amarshall.spotifysync.Utils.DocClient;
import com.mobiquity.amarshall.spotifysync.Utils.SpotifyInteractor;

import java.util.List;

import kaaes.spotify.webapi.android.models.Track;

/**
 * Base Activity that can be used for common utils.
 */
public class BaseActivity extends AppCompatActivity implements TrackListener{

    // Nothing now #soon
    protected List<Track> trackList;
    protected SpoqModel spoqModel;
    protected DocClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        client = DocClient.getInstance();
        if(!client.isOpen()){
            new Thread(client.startConnection()).start();
        }
    }

    @Override
    public void onTracksRetrieved(List<Track> trackList) {
        this.trackList = trackList;
    }

    @Override
    public void onQueueReceived(SpoqModel spoqModel) {
        this.spoqModel = spoqModel;
        SpotifyInteractor interactor = new SpotifyInteractor(DAO.PREF_SPOTIFY_TOKEN);
        interactor.getTracksById(this.spoqModel.getPlaylist().getTrackList(), this);
    }
}
