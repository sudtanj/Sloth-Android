package com.example.activity;

import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.R;
import com.example.database.dao.StepsDAO;
import com.example.database.dao.TimerDAO;
import com.example.database.dao.TimerTypesDAO;
import com.example.database.model.StepsModel;
import com.example.database.model.TimerModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddTimer extends AppCompatActivity {

    public static final int MANUAL = 1;
    public static final String MENU_KEY = "menu_key";
    public static final String KEY = "key";
    public static final int FIRST = 0;
    private List<TimerModel> timer = new ArrayList<>();
    private List<StepsModel> stepsModelList = new ArrayList<>();
    private List<StepsModel> temp = new ArrayList<>();

    private EditText timerName;
    private EditText timerInformation;
    private ListView steps;
    private Button done;
    private Button addButton;
    private TextView edit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_timer);

        setupViews();
        setTheme(getIntent().getExtras().getInt(MainActivity.THEME));
        setupActionBar();
        setupTimer();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    private void setupViews(){

        timerName = (EditText) findViewById(R.id.timerName);
        timerInformation = (EditText) findViewById(R.id.timerInformation);
        steps = (ListView) findViewById(R.id.stepsList);
        done = (Button) findViewById(R.id.doneButton);
        addButton = (Button) findViewById(R.id.addStepButton);
        edit = (TextView) findViewById(R.id.editText);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTimerToDatabase();
                finish();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater li = LayoutInflater.from(getApplicationContext());
                View promptsView = li.inflate(R.layout.prompt_add_steps, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddTimer.this);
                alertDialogBuilder.setView(promptsView);

                final EditText stepInputName = (EditText) promptsView.findViewById(R.id.editTextDialogUserInputName);
                final EditText stepInputSeconds = (EditText) promptsView.findViewById(R.id.editTextDialogUserInputSeconds);

                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // get user input and set it to result
                                        //result.setText(userInput.getText());
                                        StepsModel stepsModel = new StepsModel();
                                        stepsModel.setName(stepInputName.getText().toString());
                                        try{
                                            stepsModel.setTime(Integer.valueOf(stepInputSeconds.getText().toString()));
                                        }catch (Exception e){}
                                        temp.add(stepsModel);
                                        stepsModelList.add(stepsModel);
                                        refreshListView();
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater li = LayoutInflater.from(getApplicationContext());
                View promptsView = li.inflate(R.layout.prompt_edit_steps, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddTimer.this);
                alertDialogBuilder.setView(promptsView);

                final EditText stepInputEdit = (EditText) promptsView.findViewById(R.id.editTextDialogUserInputEdit);

                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // get user input and set it to result
                                        //result.setText(userInput.getText());
                                        try{
                                            stepsModelList.remove(Integer.valueOf(stepInputEdit.getText().toString())+1);
                                        }catch (Exception e){}
                                        refreshListView();
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }

    private void setupTimer(){
        if(getIntent().getExtras().getInt(MENU_KEY)==FIRST){
           loadSelectedTimer();
           timerName.setText(timer.get(FIRST).getName());
           timerInformation.setText(timer.get(FIRST).getInformation());
           loadStepsTimer();
           refreshListView();
        }
    }

    private void refreshListView(){
        ArrayList<String> stepsName = new ArrayList<>();
        for(StepsModel model: stepsModelList){
            if(model.getTime()>60){
                if(model.getTime()%60==0){
                    stepsName.add(model.getName()+" : "+model.getTime()/60+" "+"minutes");
                }else{
                    stepsName.add(model.getName()+" : "+model.getTime()/60+" "+"minutes and "+model.getTime()%60+"seconds");
                }
            }else{
                stepsName.add(model.getName()+" : "+model.getTime()+" "+"seconds");
            }

            Log.d("Time Value :" , Float.toString(model.getTime()));
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,stepsName);
        steps.setAdapter(arrayAdapter);
    }

    private void setupActionBar(){
        setTitle(getString(R.string.add_timer));
    }

    private void addTimerToDatabase(){
        int timerId = 0;
        try {
            timerId = new ArrayList<>(TimerDAO.readAll(-11,-11)).size();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int stepsIndex = 0;
        try {
            stepsIndex = new ArrayList<>(StepsDAO.readAll(-11,-11)).size();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        TimerModel tempTimerModel = new TimerModel();
        if(timerId!=0){
            tempTimerModel.setId(timerId);
            try {
                tempTimerModel.setTimerType(TimerTypesDAO.read(3));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            tempTimerModel.setName(timerName.getText().toString());
            tempTimerModel.setInformation(timerInformation.getText().toString());
            try {
                TimerDAO.update(tempTimerModel);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(stepsIndex!=0){
            for(int i = stepsIndex; i < stepsModelList.size();++i){
                StepsModel tempModel = stepsModelList.get(i);
                tempModel.setId(i);
                tempModel.setTimer(tempTimerModel);
                try {
                    StepsDAO.update(tempModel);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private void loadSelectedTimer(){
        try
        {
            timer = TimerDAO.search(getIntent().getExtras().getString(KEY),-11,-11);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    private void loadStepsTimer(){
        try
        {
            stepsModelList = StepsDAO.readByTimer(timer.get(FIRST).getId(),-11,-11);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

}
