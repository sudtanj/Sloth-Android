package com.example;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ActiveTimers.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ActiveTimers#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ActiveTimers extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private Button start;
    private Button addAc;
    private CountDownTimer timer;
    private SimpleDateFormat input = new SimpleDateFormat("mm");
    private SimpleDateFormat output = new SimpleDateFormat("mm:ss");
    private EditText text;
    private TextView actNameT, timeT;
    private ProgressBar progress;
    private TextToSpeech t1;
    private Button plusFive;
    private Date timeInput;

    private Context context;

    public ActiveTimers() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ActiveTimers.
     */
    // TODO: Rename and change types and number of parameters
    public static ActiveTimers newInstance(String param1, String param2) {
        ActiveTimers fragment = new ActiveTimers();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_active_timers, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //actNameT = (TextView) view.findViewById(R.id.textView4);
        //timeT = (TextView) view.findViewById(R.id.textView5);
        start = (Button) view.findViewById(R.id.startButton);
        text = (EditText) view.findViewById(R.id.inputTime);
        progress= (ProgressBar) view.findViewById(R.id.circularProgressbar);
        plusFive = (Button) view.findViewById(R.id.addTimeButton);
        timeInput = new Date(0);

        text.setText(output.format(timeInput));
        text.setGravity(Gravity.CENTER);
        text.setFilters(new InputFilter[] {new InputFilter.LengthFilter(5)});
        text.setKeyListener(DigitsKeyListener.getInstance("0123456789:"));
        t1=new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.US);
                }
            }
        });
        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        plusFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeInput.setTime(timeInput.getTime()+5000);
                text.setText(output.format(timeInput));
            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                if(text.getText().length()>0) {
                    try {
                        timeInput.setTime(timeInput.getTime()+(output.parse(text.getText().toString()).getSeconds()*1000));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    progress.setSecondaryProgressTintList(ColorStateList.valueOf(Color.WHITE));
                    start.setEnabled(false);
                    progress.setMax((int)timeInput.getTime());
                    progress.setProgress((int)timeInput.getTime());
                    text.clearFocus();
                    text.setEnabled(false);
                    new CountDownTimer(timeInput.getTime(), 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            progress.setProgress(progress.getProgress() - 1000);
                            text.setText(output.format(new Date(millisUntilFinished)));
                        }

                        @Override
                        public void onFinish() {
                            String output1="Your main countdown is up!";
                            Toast.makeText(context,output1,Toast.LENGTH_SHORT).show();
                            t1.speak(output1, TextToSpeech.QUEUE_FLUSH, null);
                            timeInput.setTime(0);
                            text.setText(output.format(timeInput));
                            text.setEnabled(true);
                            start.setEnabled(true);
                            progress.setSecondaryProgressTintList(ColorStateList.valueOf(Color.BLACK));
                        }
                    }.start();
                }
                else {
                    Toast.makeText(context,"Timer is not yet filled! please filled in seconds format!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        /*addAc = (Button) findViewById(R.id.addActivity);
        addAc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, addActivity.class);
                startActivityForResult(intent, 1);
            }
        });*/
    }

    /*protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            String names = data.getStringExtra("NAME");
            actNameT.setText(names);
            String time = text.getText().toString();
            timeT.setText(time+"s");
        }

    }*/

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            /*throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");*/
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
