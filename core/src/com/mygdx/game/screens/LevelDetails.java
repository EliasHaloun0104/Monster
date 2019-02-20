package com.mygdx.game.screens;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;

public class LevelDetails {
    int level;
    int enemy;
    int balls;
    long startTime;
    long now;
    long time;
    boolean isRunning;
    boolean isFinished;

    public LevelDetails(int level, int enemy, int balls) {
        this.level = level;
        this.enemy = enemy;
        this.balls = balls;
        startTime = TimeUtils.millis();
        isRunning = false;
        isFinished = false;
    }

    public void update(){
        if(!isRunning){
            now = TimeUtils.millis();
            if(now> startTime + 3000) // 3 seconds to start
            isRunning = true;
        }
    }


}
