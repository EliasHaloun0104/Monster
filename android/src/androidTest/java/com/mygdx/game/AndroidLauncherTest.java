package com.mygdx.game;


import org.junit.Rule;
import org.junit.Test;
import androidx.test.espresso.intent.rule.IntentsTestRule;

import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;

import static androidx.test.espresso.matcher.ViewMatchers.withId;


public class AndroidLauncherTest {

    @Rule
    public IntentsTestRule<AndroidLauncher> activity =
            new IntentsTestRule<>(AndroidLauncher.class);

    @Test
    public void buttonChangeActivity() {
        onView(withId(R.id.screenTimeButton)).perform(click());
        intended(hasComponent(ScreenTimeActivity.class.getName()));
    }

}