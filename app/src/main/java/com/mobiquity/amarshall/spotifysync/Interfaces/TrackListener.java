package com.mobiquity.amarshall.spotifysync.Interfaces;

import com.mobiquity.amarshall.spotifysync.Models.TrackQueue;

import java.util.List;

import kaaes.spotify.webapi.android.models.Track;

public interface TrackListener {
    void onTracksRetrieved(List<Track> trackList);
    void onQueueReceived(TrackQueue trackQueue);
}