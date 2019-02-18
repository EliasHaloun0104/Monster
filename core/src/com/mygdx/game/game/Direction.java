/*This enum to manage move direction*/
package com.mygdx.game.game;


import static com.mygdx.game.main.Constant.MoveX;
import static com.mygdx.game.main.Constant.MoveY;

public enum Direction {

    LEFT(-MoveX, 0), RIGHT(MoveX, 0), UP(0, MoveY), DOWN(0, -MoveY), STOP(0,0);

    Direction(int x, int y) {
        pair = new Pair(x,y);
    }

    public Pair getPair() {
        return pair;
    }

    private Pair pair;
}

