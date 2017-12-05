package com.example.activity;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.R;
import com.example.database.dao.TimerDAO;
import com.example.database.dao.TimerTypesDAO;
import com.example.database.model.TimerModel;
import com.example.database.model.TimerTypesModel;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TimerTypes extends AppCompatActivity{

    public static final String SELECTED_ITEM_ID = "id";
    public static final String SELECTED_ITEM = "name";

    public static final int MENU_ADD_TIMER = 0;
    String timerTypesMenu[] = {""};
    ListView manualTimersList;
    private List<TimerTypesModel> timerTypesList = new ArrayList<>();
    ListView prebuiltList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_types);
        setTheme(getIntent().getExtras().getInt(MainActivity.THEME));
        setupViews();
        setupActionBar();
        setupPrebuiltTimers();
    }

    private void setupViews(){
        manualTimersList = (ListView) findViewById(R.id.manualTimerList);
        timerTypesMenu[MENU_ADD_TIMER] = getString(R.string.timer_types_menu);
        ArrayAdapter<String> menuAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,timerTypesMenu);
        manualTimersList.setAdapter(menuAdapter);
        manualTimersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == MENU_ADD_TIMER){
                    Intent intent = new Intent(TimerTypes.this,AddTimer.class);
                    intent.putExtra(MainActivity.THEME, getIntent().getExtras().getInt(MainActivity.THEME));
                    intent.putExtra(AddTimer.MENU_KEY,AddTimer.MANUAL);
                    startActivity(intent);
                    finish();
                }
            }
        });

        prebuiltList = (ListView) findViewById(R.id.prebuiltTimerTypesList);
    }

    private void setupPrebuiltTimers(){
        loadTimerTypesList();
        int size = 0;
        if(timerTypesList.size()-1>=0){
            size = timerTypesList.size()-1;
        }
        String timerTypeName[] = new String[size];
        for(int i=0;i<timerTypesList.size()-1;++i){
            timerTypeName[i]=timerTypesList.get(i).getName();
        }
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,timerTypeName);
        prebuiltList.setAdapter(adapter);
        prebuiltList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                bundle.putInt(SELECTED_ITEM_ID,i);
                bundle.putString(SELECTED_ITEM,adapter.getItem(i));
                startActivity(new Intent(TimerTypes.this,PrebuiltTimer.class).putExtras(bundle));
                finish();
            }
        });
    }

    private void setupActionBar(){
        setTitle(getString(R.string.timer_types));
    }

    private void loadTimerTypesList()
    {
        try
        {
            timerTypesList = TimerTypesDAO.readAll(0, 128);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

}
