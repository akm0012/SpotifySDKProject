package com.mobiquity.amarshall.spotifysync.UI.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mobiquity.amarshall.spotifysync.R;

/**
 * Used to join or create a playlist.
 */
public class PinFragment extends Fragment {
    private PinListener pinListener;

    private TextView joinButton;
    private TextView joinAndListenButton;

    private View pinIndicator1;
    private View pinIndicator2;
    private View pinIndicator3;
    private View pinIndicator4;
    private View pinIndicator5;
    private View pinIndicator6;

    private TextView pinSpot1;
    private TextView pinSpot2;
    private TextView pinSpot3;
    private TextView pinSpot4;
    private TextView pinSpot5;
    private TextView pinSpot6;

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

        initUI(view);

        return view;
    }

    EditText.OnEditorActionListener onEditorActionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                //Clear focus here from edittext
                getView().findViewById(R.id.user_name_edit_text).clearFocus();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
            }
            return false;
        }
    };

    private void initUI(View view) {

        TextView pinButton0;
        TextView pinButton1;
        TextView pinButton2;
        TextView pinButton3;
        TextView pinButton4;
        TextView pinButton5;
        TextView pinButton6;
        TextView pinButton7;
        TextView pinButton8;
        TextView pinButton9;
        TextView pinButtonDelete;

        // Get a Handle to all the UI elements
        pinButton0 = (TextView) view.findViewById(R.id.pin_button_0);
        pinButton1 = (TextView) view.findViewById(R.id.pin_button_1);
        pinButton2 = (TextView) view.findViewById(R.id.pin_button_2);
        pinButton3 = (TextView) view.findViewById(R.id.pin_button_3);
        pinButton4 = (TextView) view.findViewById(R.id.pin_button_4);
        pinButton5 = (TextView) view.findViewById(R.id.pin_button_5);
        pinButton6 = (TextView) view.findViewById(R.id.pin_button_6);
        pinButton7 = (TextView) view.findViewById(R.id.pin_button_7);
        pinButton8 = (TextView) view.findViewById(R.id.pin_button_8);
        pinButton9 = (TextView) view.findViewById(R.id.pin_button_9);
        pinButtonDelete = (TextView) view.findViewById(R.id.pin_button_delete);
        joinButton = (TextView) view.findViewById(R.id.join_button);
        joinAndListenButton = (TextView) view.findViewById(R.id.join_and_listen_button);

        pinIndicator1 = view.findViewById(R.id.pin_indicator_1);
        pinIndicator2 = view.findViewById(R.id.pin_indicator_2);
        pinIndicator3 = view.findViewById(R.id.pin_indicator_3);
        pinIndicator4 = view.findViewById(R.id.pin_indicator_4);
        pinIndicator5 = view.findViewById(R.id.pin_indicator_5);
        pinIndicator6 = view.findViewById(R.id.pin_indicator_6);

        pinSpot1 = (TextView) view.findViewById(R.id.pin_spot_1);
        pinSpot2 = (TextView) view.findViewById(R.id.pin_spot_2);
        pinSpot3 = (TextView) view.findViewById(R.id.pin_spot_3);
        pinSpot4 = (TextView) view.findViewById(R.id.pin_spot_4);
        pinSpot5 = (TextView) view.findViewById(R.id.pin_spot_5);
        pinSpot6 = (TextView) view.findViewById(R.id.pin_spot_6);

        // Add onClickListeners
        pinButton0.setOnClickListener(onClickListener);
        pinButton1.setOnClickListener(onClickListener);
        pinButton2.setOnClickListener(onClickListener);
        pinButton3.setOnClickListener(onClickListener);
        pinButton4.setOnClickListener(onClickListener);
        pinButton5.setOnClickListener(onClickListener);
        pinButton6.setOnClickListener(onClickListener);
        pinButton7.setOnClickListener(onClickListener);
        pinButton8.setOnClickListener(onClickListener);
        pinButton9.setOnClickListener(onClickListener);
        pinButtonDelete.setOnClickListener(onClickListener);
        joinButton.setOnClickListener(onClickListener);
        joinAndListenButton.setOnClickListener(onClickListener);

        // Add onTouchListeners
        pinButton0.setOnTouchListener(onTouchListener);
        pinButton1.setOnTouchListener(onTouchListener);
        pinButton2.setOnTouchListener(onTouchListener);
        pinButton3.setOnTouchListener(onTouchListener);
        pinButton4.setOnTouchListener(onTouchListener);
        pinButton5.setOnTouchListener(onTouchListener);
        pinButton6.setOnTouchListener(onTouchListener);
        pinButton7.setOnTouchListener(onTouchListener);
        pinButton8.setOnTouchListener(onTouchListener);
        pinButton9.setOnTouchListener(onTouchListener);
        pinButtonDelete.setOnTouchListener(onTouchListener);


        ((EditText) view.findViewById(R.id.user_name_edit_text)).setOnEditorActionListener(onEditorActionListener);

        view.findViewById(R.id.user_button).setOnClickListener(onClickListener);
        view.findViewById(R.id.create_new_playlist_button).setOnClickListener(onClickListener);

    }

    /**
     * When a button is pressed we make it look bigger.
     */
    View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent event) {

            if (view instanceof TextView) {
                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        ((TextView) view).setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.pin_number_size_zoomed));
                        ((TextView) view).setTextColor(ContextCompat.getColor(getActivity(), R.color.colorAccent));
                        break;

                    case MotionEvent.ACTION_UP:
                        ((TextView) view).setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.pin_number_size_normal));
                        ((TextView) view).setTextColor(ContextCompat.getColor(getActivity(), R.color.text));
                        break;
                }
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

                case R.id.join_button:
                    joinPlaylist();
                    break;

                case R.id.join_and_listen_button:
                    joinAndListenToPlaylist();
                    break;

                case R.id.user_button:
                    pinListener.onUserImageTapped();
                    break;

                case R.id.create_new_playlist_button:
                    pinListener.onCreateNewPlaylistClicked();
                    break;
            }
        }
    };

    /**
     * Joins the playlist without enabling live listening.
     */
    private void joinPlaylist() {
        pinListener.onJoinClicked(Integer.parseInt(pin));
    }

    /**
     * Joins the playlist and starts playing whatever song the queue is listening to.
     */
    private void joinAndListenToPlaylist() {
        pinListener.onJoinAndListenClicked(Integer.parseInt(pin));
    }

    /**
     * Adds a number to the end of the PIN.
     *
     * @param numberToAdd The number you are adding to the PIN.
     */
    private void addNumberToPin(int numberToAdd) {

        switch (pin.length()) {

            case 0:
                pinIndicator1.setVisibility(View.GONE);
                pinSpot1.setVisibility(View.VISIBLE);
                pinSpot1.setText("" + numberToAdd);
                pin += numberToAdd;
                break;

            case 1:
                pinIndicator2.setVisibility(View.GONE);
                pinSpot2.setVisibility(View.VISIBLE);
                pinSpot2.setText("" + numberToAdd);
                pin += numberToAdd;
                break;

            case 2:
                pinIndicator3.setVisibility(View.GONE);
                pinSpot3.setVisibility(View.VISIBLE);
                pinSpot3.setText("" + numberToAdd);
                pin += numberToAdd;
                break;

            case 3:
                pinIndicator4.setVisibility(View.GONE);
                pinSpot4.setVisibility(View.VISIBLE);
                pinSpot4.setText("" + numberToAdd);
                pin += numberToAdd;
                break;

            case 4:
                pinIndicator5.setVisibility(View.GONE);
                pinSpot5.setVisibility(View.VISIBLE);
                pinSpot5.setText("" + numberToAdd);
                pin += numberToAdd;
                break;

            case 5:
                pinIndicator6.setVisibility(View.GONE);
                pinSpot6.setVisibility(View.VISIBLE);
                pinSpot6.setText("" + numberToAdd);
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
                pinIndicator1.setVisibility(View.VISIBLE);
                pinSpot1.setVisibility(View.GONE);
                pin = "";
                break;

            case 2:
                pinIndicator2.setVisibility(View.VISIBLE);
                pinSpot2.setVisibility(View.GONE);
                pin = pin.substring(0, pin.length() - 1);
                break;

            case 3:
                pinIndicator3.setVisibility(View.VISIBLE);
                pinSpot3.setVisibility(View.GONE);
                pin = pin.substring(0, pin.length() - 1);
                break;

            case 4:
                pinIndicator4.setVisibility(View.VISIBLE);
                pinSpot4.setVisibility(View.GONE);
                pin = pin.substring(0, pin.length() - 1);
                break;

            case 5:
                pinIndicator5.setVisibility(View.VISIBLE);
                pinSpot5.setVisibility(View.GONE);
                pin = pin.substring(0, pin.length() - 1);
                break;

            case 6:
                pinIndicator6.setVisibility(View.VISIBLE);
                pinSpot6.setVisibility(View.GONE);
                pin = pin.substring(0, pin.length() - 1);
                break;
        }

        lockJoinButtons();
    }

    /**
     * Enables the Join buttons to allow the user to move to the next screen.
     */
    private void unlockJoinButtons() {
        joinButton.setEnabled(true);
        joinAndListenButton.setEnabled(true);
    }

    /**
     * Disabled the Join buttons to block the user from moving to the next screen.
     */
    private void lockJoinButtons() {
        joinButton.setEnabled(false);
        joinAndListenButton.setEnabled(false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof PinListener) {
            pinListener = (PinListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        pinListener = null;
    }

    public interface PinListener {
        void onJoinClicked(int pin);

        void onJoinAndListenClicked(int pin);

        void onCreateNewPlaylistClicked();

        void onUserImageTapped();
    }
}
