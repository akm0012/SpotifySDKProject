package com.mobiquity.amarshall.spotifysync.UI.Activites;

import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mobiquity.amarshall.spotifysync.R;
import com.mobiquity.amarshall.spotifysync.UI.CustomViews.AnimatableLinearLayout;

/**
 * Used so J-Rod can test the server.
 */
public class ServerDebugFragment extends Fragment {

    public ServerDebugFragment() {
        // Required empty public constructor
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {

                case R.id.debugButton_createPlaylist:
                    Toast.makeText(getActivity(), "Create a playlist", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.debugButton_joinPlaylist:
                    String playListIDString = ((EditText) getView().findViewById(R.id.debugEditText_joinPlaylist)).getText().toString();
                    int playListID = 0;
                    if (!TextUtils.isEmpty(playListIDString)) {
                        playListID = Integer.parseInt(playListIDString);
                    }

                    Toast.makeText(getActivity(), "Join playlist : " + playListID, Toast.LENGTH_SHORT).show();
                    break;

                case R.id.debugButton_leavePlaylist:
                    Toast.makeText(getActivity(), "Leave a playlist", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.debugButton_addSong:
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
}
