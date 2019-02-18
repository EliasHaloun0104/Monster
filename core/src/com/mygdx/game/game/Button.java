package com.mygdx.game.game;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.main.Assets;

public class Button {
    //all button in the game are get from this class
    ImageButton btn;
    TextureRegionDrawable textureRegionDrawable;

    public Button(String regionalTexture, int index) {
        textureRegionDrawable = new TextureRegionDrawable(Assets.getInstance().getRegion(regionalTexture, index));
        btn = new ImageButton(textureRegionDrawable);
    }

    public ImageButton getBtn() {
        return btn;
    }
}
