package com.mygdx.game;

import org.junit.Test;

import static org.junit.Assert.*;

public class TheTimerTest {

    @Test
    public void getDate() {
        TheTimer theTimer = new TheTimer("yyyy-MM-dd", 12345);
        assertEquals("yyyy-MM-dd", theTimer.getDate());
    }

    @Test
    public void getDuration() {
        TheTimer theTimer = new TheTimer("yyyy-MM-dd", 12345);
        assertEquals(12345, theTimer.getDuration());
    }

}