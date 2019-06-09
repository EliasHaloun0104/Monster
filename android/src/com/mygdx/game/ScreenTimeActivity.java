package com.mygdx.game;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenTimeActivity extends AppCompatActivity {

    private TextView todayText;
    private TextView monthText;
    private TextView yearText;

    private Button updateBtn;
    private Button parentBtn;


    DatabaseHelper myDb;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_time);

        todayText = findViewById(R.id.screentime_todayTime);
        monthText = findViewById(R.id.screentime_month);
        yearText = findViewById(R.id.screentime_year);
        updateBtn = findViewById(R.id.screentime_updateBtn);
        parentBtn = findViewById(R.id.screentime_parentBtn);

        myDb = new DatabaseHelper(this);


        displayData();

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayData();
            }
        });
        parentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataParentControl();
            }
        } );

    }

    public String theDiff(long ms) {

        long totalSecs = ms / 1000;

        long diffHours = (totalSecs / 3600);
        long diffMins = (totalSecs / 60) % 60;
        long diffSecs = totalSecs % 60;


        return diffHours + "h " + diffMins + "m " + diffSecs + "s";


    }
    public void displayData(){
        Date date = new Date(System.currentTimeMillis());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long today = myDb.convertDataToString(String.valueOf(simpleDateFormat.format(date)), 1);
        todayText.setText(theDiff(today));


        simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        long thisMonth = myDb.convertDataToString(String.valueOf(simpleDateFormat.format(date)), 2);
        monthText.setText(theDiff(thisMonth));


        simpleDateFormat = new SimpleDateFormat("yyyy");
        long thisYear = myDb.convertDataToString(String.valueOf(simpleDateFormat.format(date)), 3);
        yearText.setText(theDiff(thisYear));
    }


    public void dataParentControl(){
        String text = "";
        Date date = new Date(System.currentTimeMillis());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long today = myDb.convertDataToString(String.valueOf(simpleDateFormat.format(date)), 1);
        text += "Today: " + theDiff(today) +"\n";



        simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        long thisMonth = myDb.convertDataToString(String.valueOf(simpleDateFormat.format(date)), 2);
        text += "Month: " + theDiff(thisMonth) +"\n";


        simpleDateFormat = new SimpleDateFormat("yyyy");
        long thisYear = myDb.convertDataToString(String.valueOf(simpleDateFormat.format(date)), 3);
        text += "Year: " + theDiff(thisYear) +"\n";
        Log.println(Log.ASSERT,"HHH", text);


    }
}
