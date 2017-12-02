package com.example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TimerTypes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_types);
        setupActionBar();
    }

    private void setupActionBar(){
        setTitle(getString(R.string.timer_types));
        getActionBar().setHomeButtonEnabled(true);
    }
}
