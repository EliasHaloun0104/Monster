package com.mygdx.game.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.main.MainGame;
import com.mygdx.game.main.Mouse;

import static com.mygdx.game.main.Constant.BLOCK_HEIGHT;
import static com.mygdx.game.main.Constant.BLOCK_WIDTH;


public class Block{
    private BlockType type;
    private Pair position;
    private int numberOfStrike; // in case the block type is enemy


    public Block(Pair position, BlockType type) {
        this.type = type;
        this.position = position;
        numberOfStrike = 0;
    }

    public boolean isMouseOver() {
        return Mouse.getInstance().mouseEnter(position);
    }

    public void setToEnemyMode(int numberOfStrike){
        type = BlockType.ENEMY;
        this.numberOfStrike += numberOfStrike;
    }



    public void draw(SpriteBatch batch, BitmapFont font,  boolean isMouseOver){
        if(type != BlockType.Empty){
            if(type == BlockType.DEFAULT && isMouseOver){
                batch.draw(BlockType.HOVER.getSprite(), position.getX() * BLOCK_WIDTH, position.getY() *BLOCK_HEIGHT);
            }else{
                batch.draw(type.getSprite(), position.getX() * BLOCK_WIDTH, position.getY() *BLOCK_HEIGHT);
                if(type == BlockType.ENEMY){
                    font.draw(batch, String.valueOf(numberOfStrike), position.getX()*BLOCK_WIDTH,position.getY()*BLOCK_HEIGHT+BLOCK_HEIGHT/4);
                }
            }
        }



    }


    public void update(){
        if(isMouseOver() && Gdx.input.isKeyJustPressed(Input.Keys.CONTROL_RIGHT)){
            updateTexture();
        }
    }

    public void updateTexture(){
        if(type.ordinal()>=3) {
            type = type.getNext(type);
        }
    }

    public Pair getPosition() {
        return position;
    }

    public BlockType getType() {
        return type;
    }

    public void counterStrike(){
        numberOfStrike--;
        if(numberOfStrike == 0){
            type = BlockType.DEFAULT;
        }
        MainGame.getInstance().addToScore();
    }
}
