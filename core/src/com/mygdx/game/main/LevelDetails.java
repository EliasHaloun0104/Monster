package com.mygdx.game.main;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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

    private int timer;
    private boolean isRunning;

    public LevelDetails(int level, int enemy, int ballsNo, int millisBetween) {
        timer = 3; // 3seconds
        this.level = level;
        this.enemy = enemy;
        this.ballsNo = ballsNo;

        balls = new Array<>();
        enemyGenerator = new EnemyGenerator(millisBetween);

        isRunning = false;
    }

    public void update(Array<BlockButton> blockButtons, Assets assets, Sound ping, Sound bongo, float timer){
        if(!isRunning){
            if(timer>1f){
                this.timer--;
                if(this.timer == 0){
                    isRunning = true;
                }
            }
        }else{
            if(timer>1f){
                this.timer++;
            }
        }
        //updateBall
        for (Ball b: balls) {
            b.ballMoving(blockButtons, ping, bongo, assets);
        }
        for (int i = 0; i < balls.size ; i++) {
            if(balls.get(i).isKillBall()) balls.removeIndex(i);
        }
        //Update enemy
        if(enemy>0) enemyGenerator.createEnemy(blockButtons, assets);
    }

    public void generateBall(){
        if(isRunning && ballsNo >0){
            ballsNo--;
            balls.add(new Ball());
        }
    }

    public void draw(SpriteBatch batch, BitmapFont font, TextureRegion ball){
        for (Ball b: balls) {
            b.draw(batch, ball);
        }
        font.draw(batch, "Level " +  level, 40 , BLOCK_SIZE *6 -BLOCK_SIZE/2);



        if(!isRunning)
            font.draw(batch,"Start in: 0" + timer, 40, BLOCK_SIZE*4.5f -BLOCK_SIZE/2);
        else{
            font.draw(batch,"Time: " + timer, 40, BLOCK_SIZE*4.5f - BLOCK_SIZE/2);
        }

        font.draw(batch, "Ball/s " + ballsNo, 40 , BLOCK_SIZE *3 - BLOCK_SIZE/2);

        font.draw(batch, "Enemy/s " +  enemy, 40 , BLOCK_SIZE *1.5f - BLOCK_SIZE/2);


    }



}
