package com.mygdx.game;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends Activity {

    private Button login;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        login = findViewById(R.id.loginButton);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("prefs", MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();

        //for now if the users presses login just login and save login.....
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

//                System.out.println("login pressed");
                editor.putString("username", "nuno");
                editor.apply();

                Intent intent = new Intent(v.getContext(), AndroidLauncher.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
