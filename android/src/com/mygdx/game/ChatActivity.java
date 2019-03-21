package com.mygdx.game;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


public class ChatActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private ViewPager viewPager;

    private SectionPagerAdapter sectionPagerAdapter;

    private TabLayout tabLayout;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);


        viewPager = findViewById(R.id.chat_viewpager);
        toolbar = findViewById(R.id.chat_toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Messenger");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sectionPagerAdapter = new SectionPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(sectionPagerAdapter);

        tabLayout = findViewById(R.id.chat_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.chat_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);


        if (item.getItemId() == R.id.menu_profileBtn) {
            Intent profileSettings = new Intent(ChatActivity.this, ProfileSettingsActivity.class);
            startActivity(profileSettings);
        }
        if (item.getItemId() == R.id.menu_usersBtn) {
            Intent allUsersIntent = new Intent(ChatActivity.this, UsersActivity.class);
            startActivity(allUsersIntent);
        }

        return true;
    }
}
