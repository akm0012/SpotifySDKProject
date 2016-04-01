package com.mobiquity.amarshall.spotifysync.UI.Activites;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mobiquity.amarshall.spotifysync.Models.SpoqTrack;
import com.mobiquity.amarshall.spotifysync.R;
import com.mobiquity.amarshall.spotifysync.UI.BaseActivity;
import com.mobiquity.amarshall.spotifysync.UI.SongListAdapter;
import com.mobiquity.amarshall.spotifysync.Utils.DAO;
import com.mobiquity.amarshall.spotifysync.Utils.SpotifyInteractor;
import com.mobiquity.amarshall.spotifysync.Utils.SpotifyUserValidator;
import com.spotify.sdk.android.player.Config;
import com.spotify.sdk.android.player.ConnectionStateCallback;
import com.spotify.sdk.android.player.Player;
import com.spotify.sdk.android.player.PlayerNotificationCallback;
import com.spotify.sdk.android.player.PlayerState;
import com.spotify.sdk.android.player.Spotify;

import java.util.ArrayList;
import java.util.List;

import kaaes.spotify.webapi.android.models.Pager;
import kaaes.spotify.webapi.android.models.SavedTrack;
import kaaes.spotify.webapi.android.models.Track;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class AddSongActivity extends BaseActivity implements PlayerNotificationCallback, ConnectionStateCallback {

    private Player player;
    private String accessToken;
    private SpotifyInteractor interactor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Check if result comes from the correct activity
        accessToken = DAO.getInstance(this).getPrefSpotifyToken();
        interactor = new SpotifyInteractor(accessToken);
        final Config playerConfig = new Config(this, accessToken, SpotifyUserValidator.CLIENT_ID);
        interactor.getUsersSavedTracks(new Callback<Pager<SavedTrack>>() {
            @Override
            public void success(Pager<SavedTrack> savedTrackPager, Response response) {
                ListView listView = (ListView) findViewById(R.id.songs);
                final SongListAdapter adapter = new SongListAdapter(AddSongActivity.this, convertSavedTracksToTracks(savedTrackPager.items));
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                /*Client myClient = new Client(ADDRESS, PORT);
                                myClient.execute((Track)adapter.getItem(position));*/
                        Track track = (Track)adapter.getItem(position);
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("trackId", track.id);
                        AddSongActivity.this.setResult(1000, resultIntent);
                        AddSongActivity.this.finish();
                    }
                });
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(AddSongActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
        Spotify.getPlayer(playerConfig, this, new Player.InitializationObserver() {
            @Override
            public void onInitialized(Player player) {
                AddSongActivity.this.player = player;
                AddSongActivity.this.player.addConnectionStateCallback(AddSongActivity.this);
                AddSongActivity.this.player.addPlayerNotificationCallback(AddSongActivity.this);
            }

            @Override
            public void onError(Throwable throwable) {
                Log.e("MainActivity", "Could not initialize player: " + throwable.getMessage());
            }
        });
    }

    @Override
    public void onPlaybackEvent(EventType eventType, PlayerState playerState) {

    }

    @Override
    public void onPlaybackError(ErrorType errorType, String s) {

    }

    @Override
    public void onLoggedIn() {

    }

    @Override
    public void onLoggedOut() {

    }

    @Override
    public void onLoginFailed(Throwable throwable) {

    }

    @Override
    public void onTemporaryError() {

    }

    @Override
    public void onConnectionMessage(String s) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Spotify.destroyPlayer(this);
    }

    private List<Track> convertSavedTracksToTracks(List<SavedTrack> savedTracks){
        List<Track> tracks = new ArrayList<>();
        for(SavedTrack savedTrack : savedTracks){
            tracks.add(savedTrack.track);
        }
        return tracks;
    }
}
