package com.mygdx.game.screens;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.game.Ball;
import com.mygdx.game.game.BlockButton;
import com.mygdx.game.game.EnemyGenerator;

import static com.mygdx.game.main.Constant.BLOCK_SIZE;

public class LevelDetails {
    private int level;
    private int enemy;
    private int ballsNo;


    private EnemyGenerator enemyGenerator;
    private Array<Ball> balls;


    private long startTime;
    private long now;
    private long time;
    private boolean isRunning;
    private boolean isFinished;

    public LevelDetails(int level, int enemy, int ballsNo, int millisBetween) {
        this.level = level;
        this.enemy = enemy;
        this.ballsNo = ballsNo;

        balls = new Array<>();
        enemyGenerator = new EnemyGenerator(millisBetween);


        startTime = TimeUtils.millis();
        isRunning = false;
        isFinished = false;
    }

    public void update(Array<BlockButton> blockButtons){
        if(!isRunning){
            now = TimeUtils.millis();
            if(now> startTime + 3000) { // 3 seconds to start
                isRunning = true;
                startTime = now;
            }
        }
        //updateBall
        for (Ball b: balls) {
            b.ballMoving(blockButtons);
        }
        //Update enemy
        if(enemy>0) enemyGenerator.createEnemy(blockButtons);
    }

    public void generateBall(){
        if(isRunning && ballsNo >0){
            ballsNo--;
            balls.add(new Ball());
        }
    }

    public void draw(SpriteBatch batch, BitmapFont font){
        for (Ball b: balls) {
            b.draw(batch);
        }
        font.draw(batch, "Level " +  level, 40 , BLOCK_SIZE *6 -BLOCK_SIZE/2);
        if(!isRunning)
            font.draw(batch,"Start in: " + (TimeUtils.millis() - startTime), 40, BLOCK_SIZE*4.5f -BLOCK_SIZE/2);
        else{
            font.draw(batch,"Time: " + ((TimeUtils.millis() - startTime)), 40, BLOCK_SIZE*4.5f - BLOCK_SIZE/2);
        }
        font.draw(batch, "Ball/s " + ballsNo, 40 , BLOCK_SIZE *3 - BLOCK_SIZE/2);
        font.draw(batch, "Enemy/s " +  enemy, 40 , BLOCK_SIZE *1.5f - BLOCK_SIZE/2);


    }



}
