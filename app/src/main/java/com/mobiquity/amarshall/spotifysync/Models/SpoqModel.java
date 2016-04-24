package com.mobiquity.amarshall.spotifysync.Models;

import java.io.Serializable;
import java.util.HashMap;

/**
 * The model that is stored on the Server
 */
public class SpoqModel implements Serializable {
    private int playlistId;
    /**
     * userMap uses the user's music service id (Spotify for now) as the key to retrieve the SpoqUser info
     */
    private HashMap<String, SpoqUser> userMap;
    private SpoqPlaylist playlist;

    public int getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }

    public HashMap<String, SpoqUser> getUserMap() {
        return userMap;
    }

    public void setUserMap(HashMap<String, SpoqUser> userMap) {
        this.userMap = userMap;
    }

    public SpoqPlaylist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(SpoqPlaylist playlist) {
        this.playlist = playlist;
    }

    public void addUser(SpoqUser spoqUser) {
        userMap.put(spoqUser.getMusicServiceId(), spoqUser);
    }

    public void removeUser(String musicServiceId) {
        userMap.remove(musicServiceId);
    }
}
