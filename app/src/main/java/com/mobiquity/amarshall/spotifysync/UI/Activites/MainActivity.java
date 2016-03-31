package com.mobiquity.amarshall.spotifysync.UI.Activites;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.mobiquity.amarshall.spotifysync.Models.User;
import com.mobiquity.amarshall.spotifysync.Utils.DAO;
import com.mobiquity.amarshall.spotifysync.R;

import com.mobiquity.amarshall.spotifysync.Utils.SpotifyUserValidator;

import com.mobiquity.amarshall.spotifysync.UI.BaseActivity;

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
        if(!this.isDestroyed()) {
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

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getFragmentManager();
        if (!fragmentManager.popBackStackImmediate()) {
            super.onBackPressed();
        }
    }
}
