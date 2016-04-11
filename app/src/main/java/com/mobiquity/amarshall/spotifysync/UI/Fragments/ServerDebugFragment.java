package com.mobiquity.amarshall.spotifysync.UI.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mobiquity.amarshall.spotifysync.Models.SpoqTrack;
import com.mobiquity.amarshall.spotifysync.Models.SpoqUser;
import com.mobiquity.amarshall.spotifysync.R;
import com.mobiquity.amarshall.spotifysync.UI.Activites.AddSongActivity;
import com.mobiquity.amarshall.spotifysync.UI.Activites.CommandActivity;
import com.mobiquity.amarshall.spotifysync.UI.CustomViews.AnimatableLinearLayout;

/**
 * Used so J-Rod can test the server.
 */
public class ServerDebugFragment extends Fragment {

    static SpoqUser testUser = new SpoqUser();
    private CommandActivity commandActivity;
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.debugButton_createPlaylist:
                    Toast.makeText(getActivity(), "Create a playlist", Toast.LENGTH_SHORT).show();
                    commandActivity.createPlaylist(testUser);
                    break;

                case R.id.debugButton_joinPlaylist:
                    String playListIDString = ((EditText) getView().findViewById(R.id.debugEditText_joinPlaylist)).getText().toString();
                    int playListID = 0;
                    if (!TextUtils.isEmpty(playListIDString)) {
                        playListID = Integer.parseInt(playListIDString);
                    }
                    testUser.setConnectedPlaylistId(playListID);
                    commandActivity.joinPlaylist(testUser);
                    Toast.makeText(getActivity(), "Join playlist : " + playListID, Toast.LENGTH_SHORT).show();
                    break;

                case R.id.debugButton_leavePlaylist:
                    commandActivity.leavePlaylist();
                    Toast.makeText(getActivity(), "Leave a playlist", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.debugButton_addSong:
                    startActivityForResult(new Intent(getActivity(), AddSongActivity.class), 1000);
                    Toast.makeText(getActivity(), "Add a song to a playlist", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.debugButton_downVote:
                    Toast.makeText(getActivity(), "Down vote a song", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.debugButton_removeDownVote:
                    Toast.makeText(getActivity(), "Remove a down vote", Toast.LENGTH_SHORT).show();
                    break;
            }

        }
    };

    public ServerDebugFragment() {
        // Required empty public constructor
        testUser.setUserName("jfowler" + Math.random());
        testUser.setMusicServiceId("musicServiceId" + Math.random());
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment.
     *
     * @return A new instance of fragment TestRedFragment.
     */
    public static ServerDebugFragment newInstance() {
        return new ServerDebugFragment();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof CommandActivity) {
            commandActivity = (CommandActivity) activity;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CommandActivity) {
            commandActivity = (CommandActivity) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_debug_menu, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Add an onClickListener to all the Buttons in this linear layout
        AnimatableLinearLayout linearLayout = (AnimatableLinearLayout) view.findViewById(R.id.debugLinearLayout);
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            if (linearLayout.getChildAt(i) instanceof Button) {
                linearLayout.getChildAt(i).setOnClickListener(onClickListener);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1000) {
            SpoqTrack spoqTrack = new SpoqTrack();
            spoqTrack.setTrackId(data.getStringExtra("trackId"));
            commandActivity.addTrack(spoqTrack);
        }
    }
}
