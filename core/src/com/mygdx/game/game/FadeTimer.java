package com.mygdx.game.game;

import com.badlogic.gdx.utils.TimeUtils;
//The ball death timer (to avoid the disappear of ball directly
public class FadeTimer {
    boolean startDeath;
    long startDeathTime;

    public FadeTimer() {
        this.startDeath = false;
        this.startDeathTime = 0;
    }

    public void startDeath() {
        if(!startDeath) {
            this.startDeath = true;
            this.startDeathTime = TimeUtils.millis();
        }
    }

    public boolean update(){
        return startDeath && TimeUtils.millis() > startDeathTime + 1000;
    }

    public boolean isStartDeath() {
        return startDeath;
    }
}
