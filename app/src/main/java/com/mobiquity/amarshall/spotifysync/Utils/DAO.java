package com.mobiquity.amarshall.spotifysync.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * The Data Access Object. Uses Shared Preferences as the means for persistence.
 */
public class DAO {

    public static final String PREF_SPOTIFY_TOKEN = "PREF_SPOTIFY_TOKEN";

    private static DAO instance;

    private Context context;

    private SharedPreferences sharedPreferences;

    private DAO(Context context) {
        sharedPreferences = context.getApplicationContext().getSharedPreferences(Constants.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        this.context = context;
    }

    /**
     * Create or retrieve the singleton DAO.
     *
     * @param context Needed to use Shared Preferences.
     * @return The DAO.
     */
    public static DAO getInstance(Context context) {
        if (instance == null) {
            instance = new DAO(context);
        }
        return instance;
    }

    /**
     * Saves the token to the DAO.
     *
     * @param tokenToSave The Spotify Token.
     */
    public void saveSpotifyToken(String tokenToSave) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PREF_SPOTIFY_TOKEN, tokenToSave);
        editor.apply();
    }

    /**
     * Gets the Spotify Token.
     *
     * @return The token, or null if it doesn't exist.
     */
    public String getPrefSpotifyToken() {
        return sharedPreferences.getString(PREF_SPOTIFY_TOKEN, null);
    }

    /**
     * Clears the DAO by wiping SharedPreferences.
     */
    public void clearDAO() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
