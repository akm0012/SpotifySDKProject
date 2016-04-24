package com.mobiquity.amarshall.spotifysync.UI;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import kaaes.spotify.webapi.android.models.Track;

/**
 * Created by jfowler on 3/23/16.
 */
public class PlaylistRecyclerViewAdapter extends RecyclerView.Adapter<PlaylistRecyclerViewAdapter.ViewHolder> {

    private List<Track> trackList;

    public PlaylistRecyclerViewAdapter(List<Track> trackList) {
        this.trackList = trackList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return trackList.size();
    }

    private void downVoteTrack(Track track) {

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
