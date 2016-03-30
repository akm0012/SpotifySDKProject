package com.mobiquity.amarshall.spotifysync.UI.Activites;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.mobiquity.amarshall.spotifysync.Models.User;
import com.mobiquity.amarshall.spotifysync.Utils.DAO;
import com.mobiquity.amarshall.spotifysync.R;

import com.mobiquity.amarshall.spotifysync.Utils.SpotifyUserValidator;

import com.mobiquity.amarshall.spotifysync.UI.BaseActivity;

import kaaes.spotify.webapi.android.models.Track;

public class MainActivity extends BaseActivity {

    private SpotifyUserValidator validator;
    private User userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userData = new User();
        userData.setUserId("jfowler");
        userData.setQueueId(0L);

        // Lock the UI until we are logged in
        lockUI();
        validator = new SpotifyUserValidator();
        validator.launchLogin(this);

        findViewById(R.id.button_addSong).setOnClickListener(onClickListener);
        findViewById(R.id.button_createPlaylist).setOnClickListener(onClickListener);
        findViewById(R.id.button_joinPlaylist).setOnClickListener(onClickListener);
        findViewById(R.id.loadingOverlay).setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {

                case R.id.button_addSong:
                    //TODO: lots of things
                    startActivity(new Intent(MainActivity.this, AddSongActivity.class));
                    break;

                case R.id.button_createPlaylist:
                    //TODO: lots of things
                    new Thread(client.startQueue(userData)).start();
                    startActivity(new Intent(MainActivity.this, PlaylistActivity.class));
                    break;

                case R.id.button_joinPlaylist:
                    //TODO: lots of things
                    new Thread(client.joinQueue(userData)).start();
                    startActivity(new Intent(MainActivity.this, PlaylistActivity.class));
                    break;

                case R.id.loadingOverlay:
                    // Prevent taps from going through
                    break;
            }

        }
    };

    public void unlockUI() {
        findViewById(R.id.loadingOverlay).setVisibility(View.GONE);
    }

    public void lockUI() {
        findViewById(R.id.loadingOverlay).setVisibility(View.VISIBLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String token = validator.getToken(requestCode, resultCode, data);

        if (token != null) {
            unlockUI();
            DAO.getInstance(this).saveSpotifyToken(token);
        } else {
            Toast.makeText(MainActivity.this, "Error Logging In. Restart App", Toast.LENGTH_SHORT).show();
        }
    }

}
