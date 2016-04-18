package com.mobiquity.amarshall.spotifysync.UI.Activites;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;

import com.mobiquity.amarshall.spotifysync.Models.SpoqModel;
import com.mobiquity.amarshall.spotifysync.Models.SpoqUser;
import com.mobiquity.amarshall.spotifysync.R;
import com.mobiquity.amarshall.spotifysync.UI.Fragments.PinFragment;
import com.mobiquity.amarshall.spotifysync.UI.Fragments.ServerDebugFragment;
import com.mobiquity.amarshall.spotifysync.Utils.DAO;
import com.mobiquity.amarshall.spotifysync.Utils.SpotifyInteractor;
import com.mobiquity.amarshall.spotifysync.Utils.SpotifyUserValidator;

import kaaes.spotify.webapi.android.models.UserPrivate;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends CommandActivity implements PinFragment.PinListener {

    private SpotifyUserValidator validator;
    private SpoqUser userData;
    private SpoqModel spoqModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userData = new SpoqUser();
        userData.setUserName("jfowler");

        // Lock the UI until we are logged in
        //lockUI();
        //validator = new SpotifyUserValidator();
        //validator.launchLogin(this);

        loadFragmentSlideRight(ServerDebugFragment.newInstance());
    }

    private void loadFragmentSlideRight(Fragment nextFragment) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        int id = R.id.fragmentContainer;

        Fragment current = manager.findFragmentById(R.id.fragmentContainer);
        if (current == null) {
            transaction.add(id, nextFragment);
        } else {
            transaction.setCustomAnimations(R.animator.slide_in_from_right, R.animator.slide_out_to_left, R.animator.slide_in_from_left, R.animator.slide_out_to_right);
            transaction.replace(id, nextFragment);
            transaction.addToBackStack(null);
        }
        if (!this.isDestroyed()) {
            transaction.commit();
        }
    }


    public void unlockUI() {
        findViewById(R.id.loadingOverlay).setVisibility(View.GONE);
    }

    public void lockUI() {
        findViewById(R.id.loadingOverlay).setVisibility(View.VISIBLE);
    }

    @Override
    protected void onActivityResult(final int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String token = validator.getToken(requestCode, resultCode, data);

        if (token != null) {
            DAO.getInstance(this).saveSpotifyToken(token);
            SpotifyInteractor interactor = new SpotifyInteractor(token);
            interactor.getUserInfo(new Callback<UserPrivate>() {
                @Override
                public void success(UserPrivate userPrivate, Response response) {
                    DAO.getInstance(MainActivity.this).saveSpotifyId(userPrivate.id);
                    DAO.getInstance(MainActivity.this).saveSpotifyId(userPrivate.display_name);
                    userData.setMusicServiceId(userPrivate.id);
                    userData.setUserName(userPrivate.display_name);
                    unlockUI();
                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(MainActivity.this, "Error retrieving user info", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(MainActivity.this, "Error Logging In. Restart App", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getFragmentManager();
        if (!fragmentManager.popBackStackImmediate()) {
            super.onBackPressed();
        }
    }


    @Override
    public void onJoinClicked(int pin) {
        //TODO: Refactor all the getActivity calls to use an interface
        Toast.makeText(this, "Join Playlist: " + pin, Toast.LENGTH_SHORT).show();
        lockUI();
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                unlockUI();
            }
        }, 5000);
    }

    @Override
    public void onJoinAndListenClicked(int pin) {
        onJoinClicked(pin);
    }

    @Override
    public void onCreateNewPlaylistClicked() {
        Toast.makeText(MainActivity.this, "Join a new Playlist", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUserNameChanged() {
        Toast.makeText(MainActivity.this, "New user name clicked", Toast.LENGTH_SHORT).show();
    }
}
