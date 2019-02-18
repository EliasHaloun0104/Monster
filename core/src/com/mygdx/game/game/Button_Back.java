package com.mygdx.game.game;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.main.Assets;
import com.mygdx.game.main.MainGame;
import com.mygdx.game.screens.MainMenu;

public class Button_Back extends Button {
    //This is button which used in several screen and always in the same position and do the same action

    private Sprite courser;
    private boolean drawCourser;

    public Button_Back() {
        super("backButton",-1);
        courser = new Sprite(Assets.getInstance().getRegion("backButtonCourser",-1));
        drawCourser = false;
        TextureRegionDrawable image_1 = new TextureRegionDrawable(Assets.getInstance().getRegion("backButton",-1));
        super.getBtn().setPosition(0,0);
        super.getBtn().addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                MainGame.getInstance().setScreen(new MainMenu());
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                drawCourser = true;
                super.enter(event, x, y, pointer, fromActor);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                drawCourser = false;
                super.exit(event, x, y, pointer, toActor);
            }
        });


    }

    public void draw(SpriteBatch batch){
        if(drawCourser){
            batch.draw(courser,0,0);
        }
    }
}
