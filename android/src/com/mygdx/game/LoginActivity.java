package com.mygdx.game;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class LoginActivity extends Activity {

    private Button login;
    private LoginButton faceLog;
    private TextView staus;
    private CallbackManager callbackManager;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        login = findViewById(R.id.loginButton);
        faceLog = findViewById(R.id.facelog);
        staus = findViewById(R.id.status);
        FacebookSdk.sdkInitialize(getApplicationContext());
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

        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
            System.out.println("passed");
                staus.setText("Good\n"+loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
               // System.out.print("awdd");
                staus.setText("Cancelled");
            }

            @Override
            public void onError(FacebookException error) {

            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
