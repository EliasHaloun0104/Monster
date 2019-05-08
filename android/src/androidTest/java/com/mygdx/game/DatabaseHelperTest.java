package com.mygdx.game;

import android.content.Context;

import org.junit.Test;

import androidx.test.platform.app.InstrumentationRegistry;

import static org.junit.Assert.*;

public class DatabaseHelperTest {

    private final Context mContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

    @Test
    public void insertData() {
        DatabaseHelper db = new DatabaseHelper(mContext);
        TheTimer timer = new TheTimer("2019-05-08", 10000000);
        boolean result = db.insertData(timer);
        assertTrue(result);
    }
}