package com.mygdx.game;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenTimeActivity extends AppCompatActivity {

    private TextView todayText;
    private TextView monthText;
    private TextView yearText;


    DatabaseHelper myDb;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_time);

        todayText = findViewById(R.id.screentime_todayTime);
        monthText = findViewById(R.id.screentime_month);
        yearText = findViewById(R.id.screentime_year);


        Intent intent = getIntent();
        long today =  intent.getLongExtra("Today",0);
        long thisMonth =  intent.getLongExtra("This Month",0);
        long thisYear =  intent.getLongExtra("This Year",0);



        todayText.setText(theDiff(today));


        monthText.setText(theDiff(thisMonth));

        yearText.setText(theDiff(thisYear));
    }

    public String theDiff(long ms) {

        long totalSecs = ms / 1000;

        long diffHours = (totalSecs / 3600);
        long diffMins = (totalSecs / 60) % 60;
        long diffSecs = totalSecs % 60;


        return diffHours + "h " + diffMins + "m " + diffSecs + "s";


    }
}
