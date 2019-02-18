/*Manage the range of the map*/

package com.mygdx.game.game;


import static com.mygdx.game.main.Constant.MAX_COLUMN;
import static com.mygdx.game.main.Constant.MAX_RAW;

public class Range {
    int fromX;
    int toX;

    int fromY;
    int toY;

    public Range(int raw, int column) {
        fromX = (MAX_COLUMN - column)/2;
        toX = fromX + column;

        fromY = (MAX_RAW -raw)/2;
        toY = fromY +raw;

    }

    public boolean inRange(Pair p){
        return p.getX() >= fromX && p.getX() < toX && p.getY() >= fromY && p.getY()< toY;
    }

    public int getFromX() {
        return fromX;
    }

    public int getToX() {
        return toX;
    }

    public int getFromY() {
        return fromY;
    }

    public int getToY() {
        return toY;
    }
}
