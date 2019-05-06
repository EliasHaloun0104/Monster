package com.mygdx.game;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.mygdx.game.main.MainGame;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GameActivity extends AndroidApplication {

    // timer and SQLite database
    DatabaseHelper myDb;
    private long startTime;
    private long endTime;
    private Date currentDate;

    SimpleDateFormat simpleTimeFormat;
    SimpleDateFormat simpleDateFormat;

        @Override
        protected void onCreate (Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
            initialize(new MainGame(), config);

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
        myDb.insetData(theTimer);
    }
}
