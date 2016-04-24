package com.mobiquity.amarshall.spotifysync.Models;

import java.util.HashMap;

import kaaes.spotify.webapi.android.models.Track;

/**
 * This is what is broadcasted to the Activity (UI) from the Service whenever the server sends
 * a new SpoqModel Update.
 */
public class BroadcastContainer {

    private SpoqModel spoqModel;

    /** The key is the Spotify ID we store in the spoqModel. */
    private HashMap<String, Track> spotifyTracks;

    public SpoqModel getSpoqModel() {
        return spoqModel;
    }

    public void setSpoqModel(SpoqModel spoqModel) {
        this.spoqModel = spoqModel;
    }

    public HashMap<String, Track> getSpotifyTracks() {
        return spotifyTracks;
    }

    public void setSpotifyTracks(HashMap<String, Track> spotifyTracks) {
        this.spotifyTracks = spotifyTracks;
    }

}
