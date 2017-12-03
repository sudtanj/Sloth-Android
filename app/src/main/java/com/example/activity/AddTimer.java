package com.example.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.R;
import com.example.database.dao.StepsDAO;
import com.example.database.dao.TimerDAO;
import com.example.database.model.StepsModel;
import com.example.database.model.TimerModel;

import org.w3c.dom.Text;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddTimer extends AppCompatActivity {

    public static final String MANUAL = "manual";
    public static final String KEY = "key";
    public static final int FIRST = 0;
    private List<TimerModel> timer;
    private List<StepsModel> stepsModelList;

    private EditText timerName;
    private ListView steps;
    private Button done;
    private Button addButton;
    private TextView edit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_timer);
        setTheme(getIntent().getExtras().getInt(MainActivity.THEME));
        setupActionBar();
        setupTimer();
    }

    private void setupViews(){
        timerName = (EditText) findViewById(R.id.timerName);
        steps = (ListView) findViewById(R.id.stepsList);
        done = (Button) findViewById(R.id.doneButton);
        addButton = (Button) findViewById(R.id.addStepButton);
        edit = (TextView) findViewById(R.id.editText);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void setupTimer(){
        if(!getIntent().getExtras().getString(KEY).equals(MANUAL)){
           loadSelectedTimer();
           timerName.setText(timer.get(FIRST).getName());
           loadStepsTimer();
           ArrayList<String> stepsName = new ArrayList<>();
           for(StepsModel model: stepsModelList){
               stepsName.add(model.getName()+" "+model.getTime()/1000+" "+"s");
           }
           ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,stepsName);
        }
    }

    private void setupActionBar(){
        setTitle(getString(R.string.add_timer));
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
