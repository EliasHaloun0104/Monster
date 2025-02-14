package com.mygdx.game;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AdminActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private ViewPager viewPager;

    private SectionPagerAdapter sectionPagerAdapter;

    private TabLayout tabLayout;
    private DatabaseReference userRef;


    //Firebase
    private FirebaseAuth mAuth;



    // timer and SQLite database
    DatabaseHelper myDb;
    private long startTime;
    private long endTime;
    private Date currentDate;

    SimpleDateFormat simpleTimeFormat;
    SimpleDateFormat simpleDateFormat;



    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        myDb = new DatabaseHelper(this);
        simpleTimeFormat = new SimpleDateFormat("HH:mm:ss");

        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        currentDate = new Date(System.currentTimeMillis());

        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null) {
            userRef = FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getCurrentUser().getUid());
        }

        viewPager = findViewById(R.id.admin_viewpager);
        toolbar = findViewById(R.id.admin_toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Messenger");


        sectionPagerAdapter = new SectionPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(sectionPagerAdapter);

        tabLayout = findViewById(R.id.admin_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.admin_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);


        if (item.getItemId() == R.id.adminmenu_signout) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(AdminActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();

        startTime = System.currentTimeMillis();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            userRef.child("online").setValue(true);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        endTime = System.currentTimeMillis();
        long diff = endTime - startTime;
        String theDate = simpleDateFormat.format(currentDate);
        TheTimer theTimer = new TheTimer(theDate, diff);
        myDb.insertData(theTimer);

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            userRef.child("online").setValue(false);
        }

    }

}
