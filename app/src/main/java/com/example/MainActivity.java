package com.example;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    //private TextView mTextMessage;
    //Fragments
    private AllTimers allTimers;
    private ActiveTimers activeTimers;
    private Settings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        initializeViews();
        Log.d("tes","msuk");
        if (savedInstanceState == null) {
            if(allTimers==null){
                allTimers = new AllTimers();
                activeTimers = new ActiveTimers();
                settings = new Settings();
            }
            ft.replace(R.id.frameContainer,allTimers).commit();
        }
    }

    private void initializeViews(){
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        switch (item.getItemId()) {
            case R.id.navigation_all_timers:
                ft.replace(R.id.frameContainer,allTimers).commit();
                return true;
            case R.id.navigation_active_timers:
                ft.replace(R.id.frameContainer,activeTimers).commit();
                return true;
            case R.id.navigation_settings:
                ft.replace(R.id.frameContainer,settings).commit();
                return true;
        }
        return false;
    }
}
