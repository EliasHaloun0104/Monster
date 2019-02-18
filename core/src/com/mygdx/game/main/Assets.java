package com.mygdx.game.main;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Assets {
    private static Assets instance = new Assets();
    private AssetManager asset;
    private TextureAtlas atlas;

    public static Assets getInstance() {
        return instance;
    }

    private Assets() {
        asset = new AssetManager();
        asset.load("Basic.atlas", TextureAtlas.class);
        asset.finishLoading();
        atlas = asset.get("Basic.atlas");
    }

    public TextureAtlas.AtlasRegion getRegion(String name, int index) {
        return atlas.findRegion(name,index);
    }

    public TextureAtlas.AtlasRegion ball_die(){
        return Assets.getInstance().getRegion("ballDie",-1);
    }
    public TextureAtlas.AtlasRegion ball(){
        return Assets.getInstance().getRegion("ball",-1);
    }

}
