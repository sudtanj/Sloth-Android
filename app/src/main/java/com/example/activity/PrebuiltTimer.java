package com.example.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.R;

public class PrebuiltTimer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prebuilt_timer);
        setupActionBar();
    }

    private void setupActionBar(){
        setTitle(getString(R.string.prebuilt_timers));
    }
}
