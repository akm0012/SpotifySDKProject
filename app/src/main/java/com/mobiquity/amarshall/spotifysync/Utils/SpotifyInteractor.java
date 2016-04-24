package com.mobiquity.amarshall.spotifysync.Utils;

import android.os.AsyncTask;

import com.mobiquity.amarshall.spotifysync.Models.SpoqTrack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.models.Pager;
import kaaes.spotify.webapi.android.models.SavedTrack;
import kaaes.spotify.webapi.android.models.Track;
import kaaes.spotify.webapi.android.models.UserPrivate;
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

    public void getTracksById(List<SpoqTrack> trackIds, SpotifyInteractor.TrackListener listener) {
        AsyncTrackRetriever asyncTrackRetriever = new AsyncTrackRetriever(listener);
        asyncTrackRetriever.execute(trackIds.toArray(new SpoqTrack[trackIds.size()]));
    }

    public void getUserInfo(Callback<UserPrivate> callback) {
        spotifyApi.getService().getMe(callback);
    }

    public class AsyncTrackRetriever extends AsyncTask<SpoqTrack, Void, HashMap<String, Track>> {
        TrackListener listener;

        public AsyncTrackRetriever(TrackListener trackListener) {
            listener = trackListener;
        }

        @Override
        protected HashMap<String, Track> doInBackground(SpoqTrack... params) {
            HashMap<String, Track> trackList = new HashMap<>();
            for (SpoqTrack track : params) {
                Track spotifyTrack = spotifyApi.getService().getTrack(track.getTrackId());
                trackList.put(spotifyTrack.id, spotifyTrack);
            }
            return trackList;
        }

        @Override
        protected void onPostExecute(HashMap<String, Track> tracks) {
            super.onPostExecute(tracks);
            listener.onTracksRetrieved(tracks);
            listener = null;
        }
    }

    public interface TrackListener{
        void onTracksRetrieved(HashMap<String, Track> tracks);
    }
}
