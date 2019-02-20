package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
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
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.game.Ball;
import com.mygdx.game.game.BlockButton;
import com.mygdx.game.game.BackButton;
import com.mygdx.game.game.Button;
import com.mygdx.game.game.EnemyGenerator;
import com.mygdx.game.game.Pair;
import com.mygdx.game.main.MainGame;
import com.mygdx.game.main.ViewManager;

import static com.mygdx.game.main.Constant.BLOCK_SIZE;
import static com.mygdx.game.main.Constant.MAX_COLUMN;
import static com.mygdx.game.main.Constant.MAX_RAW;


public class Level implements Screen {
    private ViewManager viewManager;
    private SpriteBatch batch;
    private Texture background;
    private BitmapFont font;


    private EnemyGenerator enemyGenerator;
    private Array<Ball> balls;
    private Pair ballPosition;
    private int numberOfAvailableBall;
    private boolean render;

    private Array<BlockButton> blockButtons;

    private Button ballButton;
    private BackButton backButton;
    private Stage stage;



    @Override
    public void show() {
        int x = Gdx.graphics.getWidth();
        int y = Gdx.graphics.getHeight();
        viewManager = new ViewManager(0,0,x,y);
        MainGame.getInstance().resetScore();
        render= false;
        font = new BitmapFont();
        balls = new Array<>();
        blockButtons = new Array<>();


        for (int i = 0; i < MAX_COLUMN; i++) {
            for (int j = 0; j < MAX_RAW; j++) {
                blockButtons.add(new BlockButton(0, (i+6)*BLOCK_SIZE, (j+1)*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE));
            }
        }

        balls.add(new Ball());
        balls.add(new Ball());
        balls.add(new Ball());
        balls.add(new Ball());
        balls.add(new Ball());



        ballPosition = new Pair(14*BLOCK_SIZE, 2*BLOCK_SIZE);

        enemyGenerator = new EnemyGenerator(1, 50);

        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.RED);
        background = new Texture("background2.png");
        numberOfAvailableBall = 19;



        stage = new Stage(viewManager.getViewport());

        backButton = new BackButton();
        backButton.addToStage(stage);


        ballButton = new Button("restartButton",-1);
        ballButton.getBtn().setPosition(14*BLOCK_SIZE, BLOCK_SIZE);
        ballButton.getBtn().addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            //TODO lunch new Ball
            return true;

            }
        });
        ballButton.addToStage(stage);

        for (BlockButton b: blockButtons) {
            b.addToStage(stage);
        }


        InputMultiplexer multiplexer = new InputMultiplexer();
        Gdx.input.setInputProcessor(multiplexer);
        multiplexer.addProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.90f, 0.90f, 0.90f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        viewManager.apply(batch);

        batch.begin();
        batch.draw(background,0,0);

        for (Ball b: balls) {
            b.draw(batch);
        }

        font.draw(batch, String.valueOf(numberOfAvailableBall), ballPosition.getX(), ballPosition.getY());
        font.draw(batch, enemyGenerator.getLevel(), 12*BLOCK_SIZE, 9.5f*BLOCK_SIZE);
        font.draw(batch, MainGame.getInstance().getStringScore(), 12*BLOCK_SIZE, 9*BLOCK_SIZE);

        batch.end();
        stage.draw();
        stage.act();


        batch.begin();
        for (Ball b: balls) {
            b.draw(batch);
        }
        batch.end();

        for (Ball b: balls
        ) {
            b.ballMoving(blockButtons);
        }


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



}
