package com.mygdx.game.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.main.Assets;

public enum BlockType {
    Empty("B_empty"),
    HOVER("blockHover"),
    ENEMY("blockEnemy"),
    DEFAULT("block"),
    DOWN_LEFT("B_downLeft"),
    DOWN_RIGHT("B_downRight"),
    UP_LEFT("B_upLeft"),
    UP_RIGHT("B_upRight");

    private static BlockType[] a = values();
    private Sprite sprite;

    BlockType(String blockName) {
        this.sprite = new Sprite(Assets.getInstance().getRegion(blockName,-1));
    }


    public Sprite getSprite() {
        return sprite;
    }
    public BlockType getNext(BlockType type){
        for (int i = 3; i < a.length; i++) {
            if(type == a[i]) {
                try {
                    return a[i+1];
                }catch (ArrayIndexOutOfBoundsException ex){
                    return a[3];
                }
            }
        }
        return null;
    }
}
