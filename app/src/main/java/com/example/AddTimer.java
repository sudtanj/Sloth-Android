package com.example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AddTimer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_timer);

        setupActionBar();
    }

    private void setupActionBar(){
        setTitle(getString(R.string.add_timer));
        getActionBar().setHomeButtonEnabled(true);
    }
}
