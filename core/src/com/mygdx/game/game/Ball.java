package com.mygdx.game.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.main.Assets;

import static com.mygdx.game.main.Constant.BLOCK_HEIGHT;
import static com.mygdx.game.main.Constant.BLOCK_WIDTH;


public class Ball {
    private Pair mapIndex; // to get which block in map the ball in
    private Pair position; // to get the real ball position
    private Direction direction;
    private FadeTimer timer; //To give the user impression of ball disappearing
    private boolean isDie;

    public Ball(Pair position, Direction direction) {
        this.mapIndex = new Pair(position.getX(), position.getY());
        this.position = new Pair(mapIndex.getX() * BLOCK_WIDTH, mapIndex.getY() * BLOCK_HEIGHT);
        this.direction = direction;
        timer = new FadeTimer();
    }

    public void draw(SpriteBatch batch) {
        if(timer.isStartDeath()){
            batch.draw(Assets.getInstance().ball_die(), position.getX() , position.getY());
        }else{
            batch.draw(Assets.getInstance().ball(), position.getX() , position.getY());
        }

    }

    public boolean isDie() {
        return isDie;
    }

    public void update(Array<Block> blocks, Array<Pair> pairs) {
        //ball decide teh next direction
        ballMoving(blocks, pairs);
        //update position
        position.add(direction.getPair());
        mapIndex.set(position.getX() / BLOCK_WIDTH, position.getY() / BLOCK_HEIGHT);
        //update is the death start
        isDie = timer.update();
    }

    public void ballMoving(Array<Block> blocks, Array<Pair> pairs) {
        //The block where the ball in
        Block blockNow = blocks.get(pairs.indexOf(mapIndex, false));
        //The movement is divided for two part for centet of block to edge and from edge to center so this method fires twice
        //in center & in Edge
        if (position.inCenter()) { // Reach the center
            switch (blockNow.getType()){
                case ENEMY:
                    blockNow.counterStrike();
                    break;
                case Empty:
                    direction = Direction.STOP;
                    timer.startDeath();
                    break;
                case UP_RIGHT:
                    switch (direction){
                        case RIGHT:
                            direction = Direction.DOWN;
                            break;
                        case UP:
                            direction = Direction.LEFT;
                            break;
                    }
                    break;
                case UP_LEFT:
                    switch (direction){
                        case LEFT:
                            direction = Direction.DOWN;
                            break;
                        case UP:
                            direction = Direction.RIGHT;
                            break;
                    }
                    break;
                case DOWN_RIGHT:
                    switch (direction){
                        case RIGHT:
                            direction = Direction.UP;
                            break;
                        case DOWN:
                            direction = Direction.LEFT;
                            break;
                    }
                    break;
                case DOWN_LEFT:
                    switch (direction){
                        case LEFT:
                            direction = Direction.UP;
                            break;
                        case DOWN:
                            direction = Direction.RIGHT;
                            break;
                    }
            }
        } else if (position.inEdge()) {
            Block nextBlock;
                switch (direction) {
                    case LEFT://LEFT CELL is the same
                        if (blockNow.getType() == BlockType.DOWN_RIGHT || blockNow.getType() == BlockType.UP_RIGHT ) {
                            direction = Direction.RIGHT;
                        }
                        break;
                    case RIGHT:
                        nextBlock = blocks.get(pairs.indexOf(mapIndex.rightCell(), false));
                        if (nextBlock.getType() == BlockType.DOWN_LEFT || nextBlock.getType() == BlockType.UP_LEFT ) {
                            direction = Direction.LEFT;
                        }
                        break;
                    case UP:
                        nextBlock = blocks.get(pairs.indexOf(mapIndex.upCell(), false));
                        if (nextBlock.getType() == BlockType.DOWN_LEFT || nextBlock.getType() == BlockType.DOWN_RIGHT) {
                            direction = Direction.DOWN;
                        }
                        break;
                    case DOWN://LEFT CELL is the same
                        if (blockNow.getType() == BlockType.UP_RIGHT || blockNow.getType() == BlockType.UP_LEFT ) {
                            direction = Direction.UP;
                        }
                        break;
                }
            }

    }
}




