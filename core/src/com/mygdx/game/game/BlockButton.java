package com.mygdx.game.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.main.MainGame;

public class BlockButton extends Button{
    private static String[] images = {"block", "B_downLeft", "B_downRight", "B_upLeft", "B_upRight", "blockEnemy"};
    private int ref;
    private int numberOfStrike; // in case the block type is enemy
    private Pair position;

    public BlockButton(int ref, int x, int y, int width, int height) {
        super(images[ref], -1);
        this.ref = ref;
        btn.getImage().setFillParent(true);
        btn.setPosition(x,y);
        position = new Pair(x,y);
        btn.setWidth(width);
        btn.setHeight(height);
        btn.addListener(new InputListener(){
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                updateTexture();
                Gdx.app.log("TAG", event.toString());
                return true;
            }
        });
    }

    public int getRef() {
        return ref;
    }

    public Pair getPosition() {
        return position;
    }

    private void updateTexture(){
        ref++;
        if(ref == images.length-1){
            ref = 0;
        }
        super.setImage(images[ref]);
    }

    public void counterStrike(){
        numberOfStrike--;
        if(numberOfStrike == 0){
            ref = 0;
            super.setImage(images[0]);
        }
        MainGame.getInstance().addToScore();
    }

    public int getX(){
        return (int) btn.getX();
    }
    public int getY(){
        return (int) btn.getY();
    }
}
