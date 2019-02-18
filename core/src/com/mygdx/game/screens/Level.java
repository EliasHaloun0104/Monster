package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.game.Ball;
import com.mygdx.game.game.Block;
import com.mygdx.game.game.BlockType;
import com.mygdx.game.game.Button_Back;
import com.mygdx.game.game.Direction;
import com.mygdx.game.game.EnemyGenerator;
import com.mygdx.game.game.Pair;
import com.mygdx.game.game.Range;
import com.mygdx.game.main.Assets;
import com.mygdx.game.main.MainGame;
import com.mygdx.game.main.Mouse;

import java.util.HashMap;
import java.util.Iterator;

import static com.mygdx.game.main.Constant.BLOCK_HEIGHT;
import static com.mygdx.game.main.Constant.BLOCK_WIDTH;
import static com.mygdx.game.main.Constant.MAX_COLUMN;
import static com.mygdx.game.main.Constant.MAX_RAW;


public class Level implements Screen {
    private SpriteBatch batch;
    private Texture background;
    private BitmapFont font;


    private Range range;
    private EnemyGenerator enemyGenerator;
    private Array<Pair> pairs;
    private Array<Block> blocks;
    private HashMap<Pair, Block> blockHashMap;
    private Array<Ball> balls;
    private Pair ballPosition;
    private int numberOfAvailableBall;
    private boolean render;



    private ImageButton runButton;
    private ImageButton ballButton;
    private Button_Back button_back;
    private Stage stage;



    @Override
    public void show() {
        MainGame.getInstance().resetScore();
        render= false;
        blockHashMap = new HashMap<Pair, Block>();
        font = new BitmapFont();
        range = new Range(6,14);
        pairs = new Array<Pair>();
        blocks = new Array<Block>();
        balls = new Array<Ball>();


        for (int i = 0; i < MAX_RAW; i++) {
            for (int j = 0; j < MAX_COLUMN; j++) {
                Pair p = new Pair(j,i);
                pairs.add(p);
                Block b;
                if(range.inRange(p)){
                    b = new Block(p, BlockType.DEFAULT);
                }else{
                    b = new Block(p, BlockType.Empty);
                }
                blocks.add(b);
                blockHashMap.put(p,b);
            }
        }



        ballPosition = new Pair(14*BLOCK_WIDTH, 2*BLOCK_HEIGHT);

        enemyGenerator = new EnemyGenerator(1, 50);

        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.RED);
        background = new Texture("background2.png");
        numberOfAvailableBall = 19;

        addBall(ballPosition.getX()/BLOCK_WIDTH,ballPosition.getY()/BLOCK_HEIGHT);



        stage = new Stage();
        Pair p = getBlockByPosition(1,0).getPosition();
        TextureRegionDrawable image_1 = new TextureRegionDrawable(Assets.getInstance().getRegion("PlayButton",-1));
        TextureRegionDrawable image_2 = new TextureRegionDrawable(Assets.getInstance().getRegion("restartButton",-1));
        button_back = new Button_Back();
        runButton = new ImageButton(image_1);
        runButton.setPosition(p.getX()*BLOCK_WIDTH, p.getY()*BLOCK_HEIGHT);
        p = getBlockByPosition(2,0).getPosition();
        ballButton = new ImageButton(image_2);
        ballButton.setPosition(p.getX()*BLOCK_WIDTH, p.getY()* BLOCK_HEIGHT);
        runButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                render = true;
                return true;

            }
        });
        ballButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(numberOfAvailableBall > 0){
                    addBall(14,2);
                    numberOfAvailableBall--;
                }
                return true;

            }
        });
        stage.addActor(button_back.getBtn());
        stage.addActor(runButton);
        stage.addActor(ballButton);
        InputMultiplexer multiplexer = new InputMultiplexer();
        Gdx.input.setInputProcessor(multiplexer);
        multiplexer.addProcessor(touchListener());
        multiplexer.addProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0, 0, 0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(render){


            for (Ball b: balls) {
                b.update(blocks,pairs);
            }
        }else{
            for (Block r : blocks) {
                r.update();
            }
        }
        enemyGenerator.createEnemy(range,blockHashMap);
        Mouse.getInstance().update();
        batch.begin();
        batch.draw(background,0,0);
        for (Block r : blocks) {
            r.draw(batch,font, r.isMouseOver());
        }

        Iterator<Ball> ballIterator = balls.iterator();
        while (ballIterator.hasNext()){
            Ball b = ballIterator.next();
            b.draw(batch);
            if(b.isDie()){
                ballIterator.remove();
            }

        }

        font.draw(batch, String.valueOf(numberOfAvailableBall), ballPosition.getX(), ballPosition.getY());
        font.draw(batch, enemyGenerator.getLevel(), 12*BLOCK_WIDTH, 9.5f*BLOCK_HEIGHT);
        font.draw(batch, MainGame.getInstance().getStringScore(), 12*BLOCK_WIDTH, 9*BLOCK_HEIGHT);

        batch.end();
        stage.draw();
        stage.act();


        if(numberOfAvailableBall == 0 && balls.size==0){
            MainGame.getInstance().setScreen(new HighScore());
        }


    }

    @Override
    public void resize(int width, int height) {
        //viewport.update(width, height, true);
        //Gdx.app.log("TAG", "Viewport world dimensions: (" + viewport.getWorldHeight() + ", " + viewport.getWorldWidth() + ")");
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
        font.dispose();

    }

    public Block getBlockByPosition(int x, int y){
        Pair p = new Pair(x,y);
        return blockHashMap.get(p);
    }

    public void addBall(int x, int y){
        balls.add(new Ball(getBlockByPosition(x,y).getPosition(), Direction.LEFT));
    }

    public InputAdapter touchListener(){
        InputAdapter inputAdapter = new InputAdapter(){
            @Override
            public boolean touchDown (int x, int y, int pointer, int button) {
                for (int i = 0; i < blocks.size; i++) {
                    if(blocks.get(i).isMouseOver()) {
                        blocks.get(i).updateTexture();
                        break;
                    }
                }
                return false;
            }
        };
        return inputAdapter;
    }

}
