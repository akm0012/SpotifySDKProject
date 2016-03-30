package com.mobiquity.amarshall.spotifysync.Utils;

import android.app.Activity;
import android.content.Intent;

import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;

/**
 * Used to retrieve a Spotify Token
 */
public class SpotifyUserValidator  {
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

    public String getToken(int requestCode, int resultCode, Intent data) {

        // Check if result comes from the correct activity
        if (requestCode == REQUEST_CODE) {
            AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, data);

            switch (response.getType()) {
                // Response was successful and contains auth token
                case TOKEN:
                    return response.getAccessToken();

                // Auth flow returned an error
                case ERROR:
                    // TODO: Handle error response
                    return null;

                // Most likely auth flow was cancelled
                default:
                    // TODO: Handle other cases
                    return null;
            }
        }

        return null;
    }
}
