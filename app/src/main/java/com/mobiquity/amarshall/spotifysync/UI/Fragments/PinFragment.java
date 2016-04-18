package com.mobiquity.amarshall.spotifysync.UI.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.mobiquity.amarshall.spotifysync.R;

import java.util.ArrayList;

/**
 * Used to join or create a playlist.
 */
public class PinFragment extends Fragment {
    private PinListener mListener;

    private String pin = "";

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
        View view = inflater.inflate(R.layout.fragment_pin, container, false);

        initKeypad(view);

        return view;
    }

    private void initKeypad(View view) {

        // Add the onClickListeners to handle clicks
        view.findViewById(R.id.pin_button_0).setOnClickListener(onClickListener);
        view.findViewById(R.id.pin_button_1).setOnClickListener(onClickListener);
        view.findViewById(R.id.pin_button_2).setOnClickListener(onClickListener);
        view.findViewById(R.id.pin_button_3).setOnClickListener(onClickListener);
        view.findViewById(R.id.pin_button_4).setOnClickListener(onClickListener);
        view.findViewById(R.id.pin_button_5).setOnClickListener(onClickListener);
        view.findViewById(R.id.pin_button_6).setOnClickListener(onClickListener);
        view.findViewById(R.id.pin_button_7).setOnClickListener(onClickListener);
        view.findViewById(R.id.pin_button_8).setOnClickListener(onClickListener);
        view.findViewById(R.id.pin_button_9).setOnClickListener(onClickListener);
        view.findViewById(R.id.pin_button_delete).setOnClickListener(onClickListener);


        view.findViewById(R.id.pin_button_0).setOnTouchListener(onTouchListener);
        view.findViewById(R.id.pin_button_1).setOnTouchListener(onTouchListener);
        view.findViewById(R.id.pin_button_2).setOnTouchListener(onTouchListener);
        view.findViewById(R.id.pin_button_3).setOnTouchListener(onTouchListener);
        view.findViewById(R.id.pin_button_4).setOnTouchListener(onTouchListener);
        view.findViewById(R.id.pin_button_5).setOnTouchListener(onTouchListener);
        view.findViewById(R.id.pin_button_6).setOnTouchListener(onTouchListener);
        view.findViewById(R.id.pin_button_7).setOnTouchListener(onTouchListener);
        view.findViewById(R.id.pin_button_8).setOnTouchListener(onTouchListener);
        view.findViewById(R.id.pin_button_9).setOnTouchListener(onTouchListener);
        view.findViewById(R.id.pin_button_delete).setOnTouchListener(onTouchListener);

    }

    /**
     * When a button is pressed we make it look bigger.
     */
    View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {

            switch (event.getAction()) {

                case MotionEvent.ACTION_DOWN:
                    ((TextView) v).setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.pin_number_size_zoomed));
                    break;

                case MotionEvent.ACTION_UP:
                    ((TextView) v).setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.pin_number_size_normal));
                    break;
            }
            return false;
        }
    };

    /**
     * Button onClickListener
     */
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.pin_button_0:
                case R.id.pin_button_1:
                case R.id.pin_button_2:
                case R.id.pin_button_3:
                case R.id.pin_button_4:
                case R.id.pin_button_5:
                case R.id.pin_button_6:
                case R.id.pin_button_7:
                case R.id.pin_button_8:
                case R.id.pin_button_9:
                    addNumberToPin(Integer.parseInt(((TextView) v).getText().toString()));
                    break;

                case R.id.pin_button_delete:
                    deleteNumberFromPin();
                    break;
            }
        }
    };

    /**
     * Adds a number to the end of the PIN.
     *
     * @param numberToAdd The number you are adding to the PIN.
     */
    private void addNumberToPin(int numberToAdd) {

        switch (pin.length()) {

            case 0:
                getView().findViewById(R.id.pin_indicator_1).setVisibility(View.GONE);
                getView().findViewById(R.id.pin_spot_1).setVisibility(View.VISIBLE);
                ((TextView) getView().findViewById(R.id.pin_spot_1)).setText("" + numberToAdd);
                pin += numberToAdd;
                break;

            case 1:
                getView().findViewById(R.id.pin_indicator_2).setVisibility(View.GONE);
                getView().findViewById(R.id.pin_spot_2).setVisibility(View.VISIBLE);
                ((TextView) getView().findViewById(R.id.pin_spot_2)).setText("" + numberToAdd);
                pin += numberToAdd;
                break;

            case 2:
                getView().findViewById(R.id.pin_indicator_3).setVisibility(View.GONE);
                getView().findViewById(R.id.pin_spot_3).setVisibility(View.VISIBLE);
                ((TextView) getView().findViewById(R.id.pin_spot_3)).setText("" + numberToAdd);
                pin += numberToAdd;
                break;

            case 3:
                getView().findViewById(R.id.pin_indicator_4).setVisibility(View.GONE);
                getView().findViewById(R.id.pin_spot_4).setVisibility(View.VISIBLE);
                ((TextView) getView().findViewById(R.id.pin_spot_4)).setText("" + numberToAdd);
                pin += numberToAdd;
                break;

            case 4:
                getView().findViewById(R.id.pin_indicator_5).setVisibility(View.GONE);
                getView().findViewById(R.id.pin_spot_5).setVisibility(View.VISIBLE);
                ((TextView) getView().findViewById(R.id.pin_spot_5)).setText("" + numberToAdd);
                pin += numberToAdd;
                break;

            case 5:
                getView().findViewById(R.id.pin_indicator_6).setVisibility(View.GONE);
                getView().findViewById(R.id.pin_spot_6).setVisibility(View.VISIBLE);
                ((TextView) getView().findViewById(R.id.pin_spot_6)).setText("" + numberToAdd);
                pin += numberToAdd;
                unlockJoinButtons();
                break;
        }
    }

    /**
     * Remove the last number from the PIN.
     */
    private void deleteNumberFromPin() {

        switch (pin.length()) {

            case 1:
                getView().findViewById(R.id.pin_indicator_1).setVisibility(View.VISIBLE);
                getView().findViewById(R.id.pin_spot_1).setVisibility(View.GONE);
                pin = "";
                break;

            case 2:
                getView().findViewById(R.id.pin_indicator_2).setVisibility(View.VISIBLE);
                getView().findViewById(R.id.pin_spot_2).setVisibility(View.GONE);
                pin = pin.substring(0, pin.length() - 1);
                break;

            case 3:
                getView().findViewById(R.id.pin_indicator_3).setVisibility(View.VISIBLE);
                getView().findViewById(R.id.pin_spot_3).setVisibility(View.GONE);
                pin = pin.substring(0, pin.length() - 1);
                break;

            case 4:
                getView().findViewById(R.id.pin_indicator_4).setVisibility(View.VISIBLE);
                getView().findViewById(R.id.pin_spot_4).setVisibility(View.GONE);
                pin = pin.substring(0, pin.length() - 1);
                break;

            case 5:
                getView().findViewById(R.id.pin_indicator_5).setVisibility(View.VISIBLE);
                getView().findViewById(R.id.pin_spot_5).setVisibility(View.GONE);
                pin = pin.substring(0, pin.length() - 1);
                break;

            case 6:
                getView().findViewById(R.id.pin_indicator_6).setVisibility(View.VISIBLE);
                getView().findViewById(R.id.pin_spot_6).setVisibility(View.GONE);
                pin = pin.substring(0, pin.length() - 1);
                break;
        }

        lockJoinButtons();
    }

    /**
     * Enables the Join buttons to allow the user to move to the next screen.
     */
    private void unlockJoinButtons() {
        getView().findViewById(R.id.join_button).setEnabled(true);
        getView().findViewById(R.id.join_and_listen_button).setEnabled(true);
    }

    /**
     * Disabled the Join buttons to block the user from moving to the next screen.
     */
    private void lockJoinButtons() {
        getView().findViewById(R.id.join_button).setEnabled(false);
        getView().findViewById(R.id.join_and_listen_button).setEnabled(false);
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

    public String getPin() {
        return pin;
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
