package com.mobiquity.amarshall.spotifysync.Utils;

import android.os.AsyncTask;

import com.mobiquity.amarshall.spotifysync.Models.SpoqTrack;

import java.util.ArrayList;
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

   /* public void getTracksById(List<SpoqTrack> trackIds, TrackListener listener) {
        AsyncTrackRetriever asyncTrackRetriever = new AsyncTrackRetriever(listener);
        asyncTrackRetriever.execute(trackIds.toArray(new SpoqTrack[trackIds.size()]));
    }*/

    public void getUserInfo(Callback<UserPrivate> callback) {
        spotifyApi.getService().getMe(callback);
    }

    /*public class AsyncTrackRetriever extends AsyncTask<SpoqTrack, Void, List<Track>> {
        TrackListener listener;

        public AsyncTrackRetriever(TrackListener trackListener) {
            listener = trackListener;
        }

        @Override
        protected List<Track> doInBackground(SpoqTrack... params) {
            List<Track> trackList = new ArrayList<>();
            for (SpoqTrack track : params) {
                Track spotifyTrack = spotifyApi.getService().getTrack(track.getTrackId());
                trackList.add(spotifyTrack);
            }
            return trackList;
        }

        @Override
        protected void onPostExecute(List<Track> tracks) {
            super.onPostExecute(tracks);
            listener.onTracksRetrieved(tracks);
            listener = null;
        }
    }*/
}
