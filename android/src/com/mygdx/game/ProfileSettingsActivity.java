package com.mygdx.game;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileSettingsActivity extends AppCompatActivity {


    private TextView status;
    private TextView userName;
    private Button changeStatusBtn;
    private CircleImageView profilePic;

    // Database
    private FirebaseUser currentUser;
    private DatabaseReference userDatabase;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);

        userName = findViewById(R.id.profile_userName);
        status = findViewById(R.id.profile_status);
        changeStatusBtn = findViewById(R.id.profile_changeStatus);
        profilePic = findViewById(R.id.profile_image);


        //Database
        try {
            currentUser = FirebaseAuth.getInstance().getCurrentUser();

            String currint_uid = currentUser.getUid();
            userDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(currint_uid);

            userDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    String name = dataSnapshot.child("name").getValue().toString();
                    String statusString = dataSnapshot.child("status").getValue().toString();

                    userName.setText(name);
                    status.setText(statusString);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }catch (NullPointerException ex){
            ex.printStackTrace();
        }

        changeStatusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String status_value = status.getText().toString();

                Intent statusIntent = new Intent(ProfileSettingsActivity.this,StatusActivity.class);
                statusIntent.putExtra("status_value",status_value);
                startActivity(statusIntent);
            }
        });

    }
}
