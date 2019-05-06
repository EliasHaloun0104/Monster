package com.mygdx.game;


public class TheTimer {

    private String date;
    private long duration;

    public TheTimer(String date, long duration) {
        this.date = date;
        this.duration = duration;
    }

    public String getDate() {
        return date;
    }


    public long getDuration() {
        return duration;
    }

}
