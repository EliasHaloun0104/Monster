package com.mygdx.game;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ForgetPassword extends AppCompatActivity {


    private EditText editText ;
    private Button   sendButton;
    private FirebaseAuth firebaseAuth;
    private Toolbar toolbar;

    // timer and SQLite database
    DatabaseHelper myDb;
    private long startTime;
    private long endTime;
    private Date currentDate;

    SimpleDateFormat simpleTimeFormat;
    SimpleDateFormat simpleDateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgetpassword);

        myDb = new DatabaseHelper(this);
        simpleTimeFormat = new SimpleDateFormat("HH:mm:ss");

        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        currentDate = new Date(System.currentTimeMillis());

        editText = findViewById(R.id.email);
        sendButton = findViewById(R.id.button2);
        toolbar = findViewById(R.id.signIn_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Forget password");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        firebaseAuth = FirebaseAuth.getInstance();
            //send
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.sendPasswordResetEmail(editText.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(ForgetPassword.this, "Reset Password Link send to your email!",
                                    Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(ForgetPassword.this, task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

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
        myDb.insertData(theTimer);
    }
}
