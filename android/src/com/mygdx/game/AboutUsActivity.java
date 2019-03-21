package com.mygdx.game;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class AboutUsActivity extends AppCompatActivity {
    private TextView aboutUs;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        aboutUs = findViewById(R.id.infoAboutUs);
        String text = "Students\n\n" +
                "Abdelhalem, Mohamed Salaheldin Mohamed\n" +
                "AL Nasr, Mohamed Rami\n" +
                "Haloun, Elias\n" +
                "Patrao Da Cunha, Nuno Jorge\n\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\tBachelor Program In Software Development\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tTBESH - 2017";

        aboutUs.setText(text);
    }
}
