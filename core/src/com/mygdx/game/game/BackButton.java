package com.mygdx.game.game;


import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.main.Assets;

public class BackButton extends Button {
    //This is button which used in several screen and always in the same position and do the same action


    public BackButton() {
        super("backButton",-1);
        TextureRegionDrawable image_1 = new TextureRegionDrawable(Assets.getInstance().getRegion("backButton",-1));
        btn.setPosition(0,0);
        /*btn.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                MainGame.getInstance().setScreen(new MainMenu());
                return super.touchDown(event, x, y, pointer, button);
            }

        });*/
    }


}
