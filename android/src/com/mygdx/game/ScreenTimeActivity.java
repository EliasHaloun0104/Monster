package com.mygdx.game;

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

        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        myDb = new DatabaseHelper(this);

        long today = myDb.convertDataToString(String.valueOf(simpleDateFormat.format(date)), 1);


        todayText.setText(theDiff(today));

        simpleDateFormat = new SimpleDateFormat("yyyy-MM");

        long thisMonth = myDb.convertDataToString(String.valueOf(simpleDateFormat.format(date)), 2);
        monthText.setText(theDiff(thisMonth));

        simpleDateFormat = new SimpleDateFormat("yyyy");
        long thisYear = myDb.convertDataToString(String.valueOf(simpleDateFormat.format(date)), 3);
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
