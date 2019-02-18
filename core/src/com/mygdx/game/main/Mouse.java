package com.mygdx.game.main;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.game.Pair;

import static com.mygdx.game.main.Constant.BLOCK_HEIGHT;
import static com.mygdx.game.main.Constant.BLOCK_WIDTH;

public class Mouse {
    private static Mouse instance = new Mouse();
    private Pair position;


    public static Mouse getInstance() {
        return instance;
    }

    private Mouse() {
        position = new Pair(-1,-1);
    }

    public void update(){
        position.set(Gdx.input.getX()/BLOCK_WIDTH, (Gdx.graphics.getHeight()-Gdx.input.getY())/BLOCK_HEIGHT);
    }

    public boolean mouseEnter(Pair position){
        return this.position.equals(position);
    }
}
