package com.mygdx.game.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.main.Assets;

import static com.mygdx.game.main.Constant.BLOCK_SIZE;


public class Ball {
    private static TextureRegion ball = Assets.getInstance().ball();
    private static TextureRegion dyingBall = Assets.getInstance().ball_die();

    private Pair position; // to get the real ball position
    private Direction direction;
    private FadeTimer timer; //To give the user impression of ball disappearing
    private boolean isBorn;
    private boolean isAlive;
    private boolean isDie;

    //All balls born on square 13,1
    public Ball() {
        this.position = new Pair(14*BLOCK_SIZE, BLOCK_SIZE);
        this.direction = Direction.LEFT;
        timer = new FadeTimer();
        isBorn = false;
        isAlive = false;
        isDie = false;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(ball, position.getX(), position.getY());
        /*if (timer.isStartDeath()) {
            batch.draw(dyingBall, position.getX(), position.getY());
        } else {
            batch.draw(ball, position.getX(), position.getY());
        }*/
    }


    public boolean isDie() {
        return isDie;
    }

    private BlockButton findBlaock(Array<BlockButton> blockButtons, Pair position){
        for (BlockButton b: blockButtons)
            if(b.getPosition().equals(position)) return b;
        return null;
    }

    public void ballMoving(Array<BlockButton> blockButtons) {
        //Add the move depend on direction
        direction.move(position);

        //in center & in Edge
        if (position.inCenter()) { // Reach the center
            BlockButton blockButton = findBlaock(blockButtons, position);
            if(blockButton != null) {
                switch (blockButton.getRef()){
                    case 1: // DownLeft
                        switch (direction){
                            case LEFT:
                                direction = Direction.UP;
                                break;
                            case DOWN:
                                direction = Direction.RIGHT;
                                break;
                        }
                        break;
                    case 2: // DownRight
                        switch (direction){
                            case RIGHT:
                                direction = Direction.UP;
                                break;
                            case DOWN:
                                direction = Direction.LEFT;
                                break;
                        }
                        break;
                    case 3: //UP Left
                        switch (direction){
                            case LEFT:
                                direction = Direction.DOWN;
                                break;
                            case UP:
                                direction = Direction.RIGHT;
                                break;
                        }
                        break;
                    case 4: //UP RIGHT
                        switch (direction){
                            case RIGHT:
                                direction = Direction.DOWN;
                                break;
                            case UP:
                                direction = Direction.LEFT;
                                break;
                        }
                        break;
                    case 5: //Enemy
                        blockButton.counterStrike();
                        break;
                }
            }else{
                direction = Direction.STOP;
            }


        } else if (position.inEdgeHor()) {

            BlockButton blockNext = null;
            Pair positionNext = null;
            switch (direction) {
                case LEFT:
                    positionNext = new Pair(position.getX() - BLOCK_SIZE / 2, position.getY());
                    blockNext = findBlaock(blockButtons, positionNext);
                    if(blockNext != null && (blockNext.getRef() == 2 || blockNext.getRef() == 4)){
                       direction = Direction.RIGHT;
                    }
                    break;
                case RIGHT:
                    positionNext = new Pair(position.getX() + BLOCK_SIZE / 2, position.getY());
                    blockNext = findBlaock(blockButtons, positionNext);
                    if(blockNext != null && (blockNext.getRef() == 1 || blockNext.getRef() == 3)){
                        direction = Direction.LEFT;
                    }
                    break;
            }
        } else if(position.inEdgeVer()) {
            BlockButton blockNext = null;
            Pair positionNext = null;
            switch (direction) {
                case UP:
                    positionNext = new Pair(position.getX(), position.getY() + BLOCK_SIZE / 2);
                    blockNext = findBlaock(blockButtons, positionNext);
                    if (blockNext != null && (blockNext.getRef() == 1 || blockNext.getRef() == 2)) {
                        direction = Direction.DOWN;
                    }
                    break;
                case DOWN:
                    positionNext = new Pair(position.getX(), position.getY() - BLOCK_SIZE / 2);
                    blockNext = findBlaock(blockButtons, positionNext);
                    if (blockNext != null && (blockNext.getRef() == 3 || blockNext.getRef() == 4)) {
                        direction = Direction.UP;
                    }
                    break;
            }
        }

    }

}




