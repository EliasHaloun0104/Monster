package com.mygdx.game;

import org.junit.Test;
import static org.junit.Assert.*;

public class ScreenTimeActivityTest {

    @Test
    public void theDiff() {
        ScreenTimeActivity sta = new ScreenTimeActivity();
        assertEquals("0h 0m 13s", sta.theDiff(13000));
        assertEquals("0h 0m 13s", sta.theDiff(13999));
        assertEquals("0h 1m 1s", sta.theDiff(61000));
        assertEquals("2h 2m 5s", sta.theDiff(7325001));
    }
}