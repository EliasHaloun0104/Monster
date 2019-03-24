package com.mygdx.game;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class ChangeScoreActivity extends AppCompatActivity {

    private Toolbar toolbar;
    String userName;
    String userId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_score);

        userId = getIntent().getStringExtra("user_id");
         userName = getIntent().getStringExtra("user_name");

        toolbar = findViewById(R.id.changescore_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Change "+userName+"'s Score");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }
}
