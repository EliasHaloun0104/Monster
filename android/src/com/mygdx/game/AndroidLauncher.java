package com.mygdx.game;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AndroidLauncher extends Activity {

	private Button playButton;
	private Button secondActivityButton;

	@Override
	public void onCreate (Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout);
		playButton = findViewById(R.id.startGame);
		secondActivityButton = findViewById(R.id.secondButton);

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
	}
}
