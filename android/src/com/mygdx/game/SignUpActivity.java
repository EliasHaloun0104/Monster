package com.mygdx.game;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {

    private Button createAccountBtn;
    private TextInputLayout regEmail;
    private TextInputLayout regPassword;
    private TextInputLayout regUserName;

    private Toolbar toolbar;

    //Database
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    //Firebase
    private FirebaseAuth mAuth;

    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Firebase
        mAuth = FirebaseAuth.getInstance();

        createAccountBtn = findViewById(R.id.reg_singUp);
        regEmail = findViewById(R.id.reg_email);
        regUserName = findViewById(R.id.reg_userName);
        regPassword = findViewById(R.id.reg_password);
        toolbar = findViewById(R.id.reg_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Register");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressDialog = new ProgressDialog(this);


        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String display_string = regUserName.getEditText().getText().toString();
                String email_string = regEmail.getEditText().getText().toString();
                String password_string = regPassword.getEditText().getText().toString();

                if (!TextUtils.isEmpty(display_string) || !TextUtils.isEmpty(email_string)
                        || !TextUtils.isEmpty(password_string)) {
                    progressDialog.setTitle("Registering User");
                    progressDialog.setMessage("Please Wait, Account creation in progress!");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();
                    registerUser(display_string, email_string, password_string);

                }
            }
        });
    }

    private void registerUser(final String display_string, String email_string, String password_string) {

        mAuth.createUserWithEmailAndPassword(email_string, password_string).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                    String uid = currentUser.getUid();

                    database = FirebaseDatabase.getInstance();
                    databaseReference = database.getReference().child("Users").child(uid);

                    HashMap<String, String> userMap = new HashMap<>();
                    userMap.put("name", display_string);
                    userMap.put("status", "Hi there, I'm using HKR Monsters App.");
                    userMap.put("role", "user");

                    databaseReference.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                progressDialog.dismiss();
                                Intent mainIntent = new Intent(SignUpActivity.this, AndroidLauncher.class);
                                mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(mainIntent);
                                finish();
                            }
                        }
                    });


                } else {
                    progressDialog.hide();
                    Toast.makeText(SignUpActivity.this, "Cannot create! Please check the form and try again",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
