package com.example.activity;

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
import android.widget.RelativeLayout;

import com.example.SlothTimeApplication;
import com.example.fragments.ActiveTimers;
import com.example.fragments.AllTimers;
import com.example.R;
import com.example.fragments.Settings;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener,Settings.OnFragmentInteractionListener,AllTimers.AllTimersListener{

    //private TextView mTextMessage;
    //Fragments
    private AllTimers allTimers;
    private ActiveTimers activeTimers;
    private Settings settings;
    public static final String THEME = "Theme";
    private int theme = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        setTheme(R.style.AppTheme1);
        initializeViews();
        setupActionBar();
        //ContextThemeWrapper tes = getApplicationContext().getTheme().applyStyle();
        Log.d("tes","msuk");
        if (savedInstanceState == null) {
            if(allTimers==null){
                allTimers = new AllTimers();
                activeTimers = new ActiveTimers();
                settings = new Settings();
            }
            ft.replace(R.id.frameContainer,allTimers).commit();
        }
        ((SlothTimeApplication) getApplication()).getTracker();
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
        switch (item.getItemId()) {
            case R.id.add_timer:
                Intent intent = new Intent(MainActivity.this, TimerTypes.class);
                intent.putExtra(THEME,theme);
                startActivity(intent);
                return true;
        }
        return false;
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
                setTitle(getString(R.string.title_activity_select_theme));
                ft.replace(R.id.frameContainer,settings).commit();
                return true;
        }
        return false;
    }

    @Override
    public void onFragmentInteraction(int id) {
        this.theme = id;
        setTheme(id);
    }

    @Override
    public void allTimersInteraction(String id) {
        
    }
}
