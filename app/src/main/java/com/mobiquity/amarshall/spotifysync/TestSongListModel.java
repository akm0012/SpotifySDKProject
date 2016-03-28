package com.mobiquity.amarshall.spotifysync;

import java.util.List;

import kaaes.spotify.webapi.android.models.Track;

public class TestSongListModel {
    private List<Track> trackList;

    public List<Track> getTrackList() {
        return trackList;
    }

    public void setTrackList(List<Track> trackList) {
        this.trackList = trackList;
    }
}