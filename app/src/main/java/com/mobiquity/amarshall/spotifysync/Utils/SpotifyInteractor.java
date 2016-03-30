package com.mobiquity.amarshall.spotifysync.Utils;

import android.os.AsyncTask;

import com.mobiquity.amarshall.spotifysync.Interfaces.TrackListener;

import java.util.ArrayList;
import java.util.List;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.models.Pager;
import kaaes.spotify.webapi.android.models.SavedTrack;
import kaaes.spotify.webapi.android.models.Track;
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

    public void getTracksById(List<String> trackIds, TrackListener listener){
        AsyncTrackRetriever asyncTrackRetriever = new AsyncTrackRetriever(listener);
        asyncTrackRetriever.execute(trackIds.toArray(new String[trackIds.size()]));
    }

    public class AsyncTrackRetriever extends AsyncTask<String, Void, List<Track>>{
        TrackListener listener;

        public AsyncTrackRetriever(TrackListener trackListener){
            listener = trackListener;
        }

        @Override
        protected List<Track> doInBackground(String... params) {
            List<Track> trackList = new ArrayList<>();
            for(String trackId : params){
                Track track = spotifyApi.getService().getTrack(trackId);
                trackList.add(track);
            }
            return trackList;
        }

        @Override
        protected void onPostExecute(List<Track> tracks) {
            super.onPostExecute(tracks);
            listener.onTracksRetrieved(tracks);
            listener = null;
        }
    }
}
