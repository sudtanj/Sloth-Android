package com.example.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.R;
import com.example.database.dao.TimerDAO;
import com.example.database.dao.TimerTypesDAO;
import com.example.database.model.TimerModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link AllTimers#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AllTimers extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private AllTimersListener mListener;

    ListView allTimers;
    List<TimerModel> allTimerList = new ArrayList<>();

    List<TimerModel> tempTimer = new ArrayList<>();

    public AllTimers() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AllTimers.
     */
    // TODO: Rename and change types and number of parameters
    public static AllTimers newInstance(String param1, String param2) {
        AllTimers fragment = new AllTimers();
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
        return inflater.inflate(R.layout.fragment_all_timers, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        allTimers = (ListView) view.findViewById(R.id.allTimers);
        setupAllTimers();
    }

    @Override
    public void onResume() {
        super.onResume();
        setupAllTimers();
    }

    private void setupAllTimers(){
        loadAllTimerList();
        ArrayList<String> list = new ArrayList<>();
        for(int i=0;i<allTimerList.size();++i){
            //if(allTimerList.get(i).getTimerType().getId()==3){
            list.add(allTimerList.get(i).getName());
            tempTimer.add(allTimerList.get(i));
            //}
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,list);
        allTimers.setAdapter(arrayAdapter);

        allTimers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                long id = 0;
                for(TimerModel temp : tempTimer){
                    if(temp.getName().equals((String)allTimers.getItemAtPosition(i))){
                        id = temp.getId();
                    }
                }
                mListener.allTimersInteraction(id);
            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(long uri) {
        if (mListener != null) {
            mListener.allTimersInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AllTimersListener) {
            mListener = (AllTimersListener) context;
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
    public interface AllTimersListener {
        // TODO: Update argument type and name
        void allTimersInteraction(long id);
    }

    private void loadAllTimerList()
    {
        try
        {
            allTimerList = TimerDAO.readAll(-11, -11);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}
