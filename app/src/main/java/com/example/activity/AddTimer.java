package com.example.activity;

import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.R;
import com.example.database.dao.StepsDAO;
import com.example.database.dao.TimerDAO;
import com.example.database.dao.TimerTypesDAO;
import com.example.database.model.StepsModel;
import com.example.database.model.TimerModel;
import com.j256.ormlite.dao.ForeignCollection;

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
    ArrayList<String> stepsName;
    ArrayList<Integer> stepsTime;

    private EditText timerName;
    //private EditText timerInformation;
    private ListView steps;
    private Button done;
    private Button addButton;
    private TextView edit;
    public static final String THEME = "Theme";
    private int theme = 1;


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
        Intent intent = new Intent(getApplicationContext(), TimerTypes.class);
        intent.putExtra(THEME,theme);
        startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    private void setupViews(){

        timerName = (EditText) findViewById(R.id.timerName);
        //timerInformation = (EditText) findViewById(R.id.timerInformation);
        steps = (ListView) findViewById(R.id.stepsList);
        done = (Button) findViewById(R.id.doneButton);
        addButton = (Button) findViewById(R.id.addStepButton);
        edit = (TextView) findViewById(R.id.editText);
        edit.setEnabled(false);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    addTimerToDatabase();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
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
                                        if(stepInputSeconds.getText().toString().matches("[0-9]+")){
                                            stepsModel.setTime(Integer.valueOf(stepInputSeconds.getText().toString())*1000);
                                            temp.add(stepsModel);
                                            stepsModelList.add(stepsModel);
                                            refreshListView();
                                        }else{
                                            Toast.makeText(getApplicationContext(), "Please only input a number to remove step", Toast.LENGTH_SHORT).show();
                                        }

                                        if(stepsModelList.size()!=0){
                                            edit.setEnabled(true);
                                        }
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
                                        if(stepInputEdit.getText().toString().matches("[0-9]+")&&Integer.valueOf(stepInputEdit.getText().toString())<=stepsModelList.size()){
                                            stepsModelList.remove(Integer.valueOf(stepInputEdit.getText().toString())-1);
                                            refreshListView();
                                        }else if(!stepInputEdit.getText().toString().matches("[0-9]+")){
                                            Toast.makeText(getApplicationContext(), "Please only input a number to remove step", Toast.LENGTH_SHORT).show();
                                        }else{
                                            Toast.makeText(getApplicationContext(), "Input is bigger than the step list", Toast.LENGTH_SHORT).show();
                                        }

                                        if(stepsName.size()==0){
                                            edit.setEnabled(false);
                                        }
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
           //timerInformation.setText(timer.get(FIRST).getInformation());
           loadStepsTimer();
           refreshListView();
        }
    }

    private void refreshListView(){
        stepsName = new ArrayList<>();
        //s
        for(int i=0;i<stepsModelList.size();++i){
            if(stepsModelList.get(i).getTime()/1000>60){
                if(stepsModelList.get(i).getTime()/1000%60==0){
                    stepsName.add(stepsModelList.get(i).getName()+" : "+stepsTime.get(i)/60+" "+"minutes");
                }else{
                    stepsName.add(stepsModelList.get(i).getName()+" : "+stepsTime.get(i)/60+" "+"minutes and "+stepsModelList.get(i).getTime()%60+"seconds");
                }
            }else{
                stepsName.add(stepsModelList.get(i).getName()+" : "+stepsTime.get(i)+" "+"seconds");
            }

            Log.d("Time Value :" , Float.toString(stepsModelList.get(i).getTime()));
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,stepsName);
        steps.setAdapter(arrayAdapter);
    }

    private void setupActionBar(){
        setTitle(getString(R.string.add_timer));
    }

    private void addTimerToDatabase() throws SQLException {
        int timerId = 0;

        TimerModel tempTimerMModel = new TimerModel();
        tempTimerMModel.setId(TimerDAO.readAll(-11,-11).size()+1);
        tempTimerMModel.setTimerType(TimerTypesDAO.read(4));
        tempTimerMModel.setName(timerName.getText().toString());
        //tempTimerMModel.setInformation(timerInformation.getText().toString());

        int stespDBList = StepsDAO.readAll(-11,-11).size();

        for(int i = stespDBList+1, j = 0; i<stepsModelList.size()+stespDBList+1 && j<stepsModelList.size() ; ++i , ++j){
            StepsModel tempStepsModel = new StepsModel();
            tempStepsModel = stepsModelList.get(j);
            tempStepsModel.setId(i);
            tempStepsModel.setTimer(tempTimerMModel);
            StepsDAO.create(tempStepsModel);
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
            stepsTime = new ArrayList<>();
            for(StepsModel stepsModel:stepsModelList){
                stepsTime.add(stepsModel.getTime()/1000);
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

}
