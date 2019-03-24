package com.mygdx.game;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AndroidLauncher extends Activity {

    private Button playButton;
    private Button chatButton;
    private Button howToPlayButton;
    private Button aboutUsButton;
    private Button secondActivityButton;
    private Button admintButton;
    private Button signOutButton;
    private Button shareButton;
    private Button highscoresButton;
    private LoginManager loginManager;


    //Firebase
    private FirebaseAuth mAuth;

    private InternetConnection connection = new InternetConnection();

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        /*
        if (connection.isOnline(getApplicationContext())) {
            System.out.println("ONLINE");
        } else {
            System.out.println("OFFLINE");
        }


        SharedPreferences pref = getApplicationContext().getSharedPreferences("prefs", MODE_PRIVATE);
        String username = pref.getString("username", null);

        if (username != null) {
        */
        setContentView(R.layout.layout);

        //Firebase
        mAuth = FirebaseAuth.getInstance();


        playButton = findViewById(R.id.startGame);
        secondActivityButton = findViewById(R.id.secondButton);
        chatButton = findViewById(R.id.chat);
        howToPlayButton = findViewById(R.id.howToPlay);
        aboutUsButton = findViewById(R.id.aboutUs);
        signOutButton = findViewById(R.id.signOutBtn);
        shareButton = findViewById(R.id.sharebtn);
        highscoresButton = findViewById(R.id.highscoresButton);

        admintButton = findViewById(R.id.adminButton);


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
                Intent intent = new Intent(v.getContext(), AboutUsActivity.class);
                startActivity(intent);
            }
        });
        howToPlayButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), HowToPlayActivity.class);
                startActivity(intent);
            }
        });

        admintButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AdminActivity.class);
                startActivity(intent);
            }
        });
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ShareFB.class);
                startActivity(intent);
            }
        });

        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                loginManager.getInstance().logOut();
                Intent intent = new Intent(AndroidLauncher.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        highscoresButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (connection.isOnline(getApplicationContext())) {
                    Intent intent = new Intent(v.getContext(), HighScoresActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(AndroidLauncher.this,
                            "No connection",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

       /* }

        else {
            Intent intent = new Intent(this.getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        }
        */
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        // to check if it's logged in or not
        if (currentUser == null) {
            Intent intent = new Intent(AndroidLauncher.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

}
