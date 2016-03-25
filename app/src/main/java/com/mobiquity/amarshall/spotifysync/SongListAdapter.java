package com.mobiquity.amarshall.spotifysync;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import kaaes.spotify.webapi.android.models.SavedTrack;
import kaaes.spotify.webapi.android.models.Track;

/**
 * Created by jfowler on 3/23/16.
 */
public class SongListAdapter extends BaseAdapter {

    private Context context;
    private List<SavedTrack> userTracks;

    public SongListAdapter(Context context, List<SavedTrack> tracks){
        this.context = context;
        userTracks = tracks;
    }
    @Override
    public int getCount() {
        return userTracks.size();
    }

    @Override
    public Object getItem(int position) {
        return userTracks.get(position).track;
    }

    @Override
    public long getItemId(int position) {
        return userTracks.get(position).describeContents();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_layout, null);

            holder = new ViewHolder();
            holder.songTitle = (TextView) convertView.findViewById(R.id.song_text);
            holder.artist = (TextView) convertView.findViewById(R.id.artist_text);
            holder.albumArt = (ImageView) convertView.findViewById(R.id.album_cover);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // Bind the data efficiently with the holder.
        Track track = userTracks.get(position).track;
        holder.songTitle.setText(track.name);
        holder.artist.setText(track.artists.get(0).name);
        Glide.with(context).load(track.album.images.get(0).url).into(holder.albumArt);

        return convertView;
    }

    static class ViewHolder {
        TextView songTitle;
        TextView artist;
        ImageView albumArt;
    }
}
