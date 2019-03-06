package com.mygdx.game;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AndroidLauncher extends Activity {

    private Button playButton;
    private Button chatButton;
    private Button howToPlayButton;
    private Button aboutUsButton;
    private Button secondActivityButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("prefs", MODE_PRIVATE);
        String username = pref.getString("username", null);

        if (username != null) {

            setContentView(R.layout.layout);

            playButton = findViewById(R.id.startGame);
            secondActivityButton = findViewById(R.id.secondButton);
            chatButton = findViewById(R.id.chat);
            howToPlayButton = findViewById(R.id.howToPlay);
            aboutUsButton = findViewById(R.id.aboutUs);

            playButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), GameActivity.class);
                    startActivity(intent);

                }
            });

            secondActivityButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), Activity2.class);
                    startActivity(intent);
                }
            });
            chatButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ChatActivity.class);
                    startActivity(intent);
                }
            });
            aboutUsButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), AboutUs.class);
                    startActivity(intent);
                }
            });
            howToPlayButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), HowToPlay.class);
                    startActivity(intent);
                }
            });

        }

        else {
            Intent intent = new Intent(this.getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
