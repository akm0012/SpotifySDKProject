package com.mobiquity.amarshall.spotifysync.UI.Activites;

import android.os.Bundle;
import android.widget.ListView;

import com.mobiquity.amarshall.spotifysync.R;
import com.mobiquity.amarshall.spotifysync.UI.BaseActivity;
import com.mobiquity.amarshall.spotifysync.UI.SongListAdapter;

import java.util.List;

import kaaes.spotify.webapi.android.models.Track;

/**
 * Created by jfowler on 3/30/16.
 */
public class PlaylistActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);
    }

    @Override
    public void onTracksRetrieved(List<Track> trackList) {
        super.onTracksRetrieved(trackList);
        ListView trackListView = (ListView) findViewById(R.id.songs);
        SongListAdapter adapter = new SongListAdapter(this, this.trackList);
        trackListView.setAdapter(adapter);
    }
}
