package com.mobiquity.amarshall.spotifysync.Utils;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.models.Pager;
import kaaes.spotify.webapi.android.models.SavedTrack;
import retrofit.Callback;

/**
 * Created by jfowler on 3/23/16.
 */
public class SpotifyInteractor {

    private SpotifyApi spotifyApi;

    public SpotifyInteractor(String accessToken) {
        spotifyApi = new SpotifyApi();
        spotifyApi.setAccessToken(accessToken);
    }

    public void getUsersSavedTracks(Callback<Pager<SavedTrack>> callback) {
        spotifyApi.getService().getMySavedTracks(callback);
    }
}
