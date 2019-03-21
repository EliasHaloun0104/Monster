package com.mygdx.game;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class MonsterOffline extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
