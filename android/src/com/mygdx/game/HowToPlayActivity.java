package com.mygdx.game;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HowToPlayActivity extends AppCompatActivity {


    // timer and SQLite database
    DatabaseHelper myDb;
    private long startTime;
    private long endTime;
    private Date currentDate;

    SimpleDateFormat simpleTimeFormat;
    SimpleDateFormat simpleDateFormat;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_play);

        myDb = new DatabaseHelper(this);
        simpleTimeFormat = new SimpleDateFormat("HH:mm:ss");

        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        currentDate = new Date(System.currentTimeMillis());
    }

    @Override
    protected void onStart() {
        super.onStart();

        startTime = System.currentTimeMillis();

    }

    @Override
    protected void onStop() {
        super.onStop();

        endTime = System.currentTimeMillis();
        long diff = endTime - startTime;
        String theDate = simpleDateFormat.format(currentDate);
        TheTimer theTimer = new TheTimer(theDate, diff);
    }
}
