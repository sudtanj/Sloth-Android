package com.example;

import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //private TextView mTextMessage;
    //Fragments
    private AllTimers allTimers;
    private ActiveTimers activeTimers;
    private Settings settings;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_all_timers:
                    if(allTimers==null){
                        allTimers = new AllTimers();
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer,allTimers).commit();
                    return true;
                case R.id.navigation_active_timers:
                    if(activeTimers==null){
                        activeTimers = new ActiveTimers();
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer,activeTimers).commit();
                    return true;
                case R.id.navigation_settings:
                    if(settings==null){
                        settings = new Settings();
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer,settings).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            if(allTimers==null){
                allTimers = new AllTimers();
            }
            getSupportFragmentManager().beginTransaction().add(R.id.container,allTimers).commit();
        }


    }

    private void initializeViews(){
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
