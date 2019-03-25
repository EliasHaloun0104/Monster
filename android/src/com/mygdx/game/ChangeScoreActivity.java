package com.mygdx.game;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class ChangeScoreActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextInputLayout scoreTxt;
    private Button changeScroeBtn;
    private String userName;
    private String userId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_score);

        userId = getIntent().getStringExtra("user_id");
        userName = getIntent().getStringExtra("user_name");

        scoreTxt = findViewById(R.id.changescore_score);
        changeScroeBtn = findViewById(R.id.changeScore_change);


        toolbar = findViewById(R.id.changescore_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Change " + userName + "'s Score");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        changeScroeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}
