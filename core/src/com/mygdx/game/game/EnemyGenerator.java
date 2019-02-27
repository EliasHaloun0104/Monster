package com.mygdx.game.game;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.main.Assets;

public class EnemyGenerator {
    private long startTime;
    private int millisBetween; // the time between every two generator

    public EnemyGenerator(int millisBetween) {
        startTime = TimeUtils.millis();
        this.millisBetween = millisBetween;
    }

    public void createEnemy(Array<BlockButton> blockButtons, Assets assets) {
        long timeNow = TimeUtils.millis();
        if (timeNow> startTime + millisBetween) {
            startTime = timeNow;
            int random;
            do
                random = MathUtils.random(1,48-1);
            while (random == 5 || random==42);

            blockButtons.get(random).setEnemy(1, assets);
            }

    }
}



