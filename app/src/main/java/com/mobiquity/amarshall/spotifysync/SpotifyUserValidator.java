package com.mobiquity.amarshall.spotifysync;

import android.app.Activity;

import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;

/**
 * Created by jfowler on 3/23/16.
 */
public class SpotifyUserValidator {
    public static final String CLIENT_ID = "03487ad3b60a4426b6010870a2d07f12";
    public static final String REDIRECT_URI = "spotifytest://callback";
    public static final int REQUEST_CODE = 1337;

    public SpotifyUserValidator() {

    }

    public void launchLogin(Activity activity) {
        AuthenticationRequest.Builder builder = new AuthenticationRequest.Builder(CLIENT_ID,
                AuthenticationResponse.Type.TOKEN,
                REDIRECT_URI);
        builder.setScopes(new String[]{"user-read-private", "streaming", "user-library-read"});
        AuthenticationRequest request = builder.build();

        AuthenticationClient.openLoginActivity(activity, REQUEST_CODE, request);
    }

}
