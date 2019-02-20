package com.mygdx.game.game;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class EnemyGenerator {
    private long startTime;
    private int strikeNumber; // the number of strike for each enemy to die
    private int numberOfEnemy; // the number of the generator called
    private int millisBetween; // the time between every two generator

    public EnemyGenerator(int strikeNumber, int numberOfEnemy, int millisBetween) {
        startTime = TimeUtils.millis();
        this.strikeNumber = strikeNumber;
        this.numberOfEnemy = numberOfEnemy;
        this.millisBetween = millisBetween;
    }

    public void createEnemy(Array<BlockButton> blockButtons) {
        long timeNow = TimeUtils.millis();
        if (numberOfEnemy>0 && timeNow> startTime + millisBetween) {
            startTime = timeNow;
            int random;
            do
                random = MathUtils.random(1,48-1);
            while (random == 5 || random==42);
            blockButtons.get(random).setEnemy(1);
            numberOfEnemy--;
            }

    }
}



