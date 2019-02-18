package com.mygdx.game.game;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.HashMap;

public class EnemyGenerator {
    private long time; // time to generate new enemy
    private int enemyPerX_Sec; // number of enemy that show every punch of second
    private int strikeNumber; // the number of strike for each enemy to die
    private int callCounter; // the number of the generator called
    private int millisBetween; // the time between every two generator
    private int level; // game level

    public EnemyGenerator(int enemyPerX_Sec, int strikeNumber) {
        time = TimeUtils.millis();
        this.enemyPerX_Sec = enemyPerX_Sec;
        this.strikeNumber = strikeNumber;
        callCounter = 0;
        millisBetween = 10000;
        level = 1;
    }

    public void createEnemy(Range range, HashMap<Pair, Block> blockHashMap) {
        if (TimeUtils.millis()> time + millisBetween) {
            int i = 0;
            while (i < enemyPerX_Sec) {
                int randomX = MathUtils.random(range.getFromX(), range.getToX() - 1);
                int randomY = MathUtils.random(range.getFromY(), range.getToY() - 1);
                boolean bol_1 = randomX == range.getFromX() || randomX == range.getToX() - 1;
                boolean bol_2 = randomY == range.getFromY() || randomY == range.getToY() - 1;
                if (!(bol_1 && bol_2)) {
                    Pair p = new Pair(randomX, randomY);
                    blockHashMap.get(p).setToEnemyMode(strikeNumber);
                    i++;
                }
            }
            time = TimeUtils.millis();
            callCounter++;
            if(callCounter%12 == 0){
                strikeNumber++;
            }
            if(callCounter%24== 0){
                enemyPerX_Sec ++;
                level++;
            }

        }
    }

    public String getLevel() {
        return "LEVEL: " + level;
    }
}



