package com.example;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
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
        setupActionBar();
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

    // create an action bar button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_timer,menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (item.getItemId()) {
            case 0:
                Intent intent = new Intent(this, AddTimer.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initializeViews(){
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
    }

    private void setupActionBar(){
        setTitle(getString(R.string.title_all_timers));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        switch (item.getItemId()) {
            case R.id.navigation_all_timers:
                setTitle(getString(R.string.title_all_timers));
                ft.replace(R.id.frameContainer,allTimers).commit();
                return true;
            case R.id.navigation_active_timers:
                setTitle(getString(R.string.title_active_timers));
                ft.replace(R.id.frameContainer,activeTimers).commit();
                return true;
            case R.id.navigation_settings:
                setTitle(getString(R.string.title_settings));
                ft.replace(R.id.frameContainer,settings).commit();
                return true;
        }
        return false;
    }
}
