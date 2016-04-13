package com.mobiquity.amarshall.spotifysync.UI.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobiquity.amarshall.spotifysync.R;

/**
 * Activities that contain this fragment must implement the
 * {@link PinFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PinFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PinFragment extends Fragment {
    private PinListener mListener;

    public PinFragment() {
        // Required empty public constructor
    }

    public static PinFragment newInstance() {
        PinFragment fragment = new PinFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pin, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof PinListener) {
            mListener = (PinListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface PinListener {
        void onPinEntered(int pin);
    }
}
