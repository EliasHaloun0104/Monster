package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
import com.mygdx.game.game.BlockButton;
import com.mygdx.game.game.Button;
import com.mygdx.game.main.LevelDetails;
import com.mygdx.game.main.MainGame;
import com.mygdx.game.main.ViewManager;

import static com.mygdx.game.main.Constant.BLOCK_SIZE;
import static com.mygdx.game.main.Constant.MAX_COLUMN;
import static com.mygdx.game.main.Constant.MAX_RAW;


public class Play implements Screen {
    private SpriteBatch batch;

    //Fonts
    private BitmapFont font;
    private BitmapFont greenFont;
    private BitmapFont redFont;

    private ViewManager viewManager;
    private Stage stage;

    private Texture background;


    private LevelDetails levelDetails;

    //public Play(LevelDetails levelDetails) {
        //this.levelDetails = levelDetails;
    //}




    //Buttons
    private Array<BlockButton> blockButtons;
    private Button ballButton;

    @Override
    public void show() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.WHITE);

        viewManager = new ViewManager();
        stage = new Stage(viewManager.getViewport());

        levelDetails = new LevelDetails(1,3, 2, 5000);


        background = new Texture("background2.png");


        ballButton = new Button("block",-1);
        ballButton.getBtn().setPosition(13*BLOCK_SIZE, BLOCK_SIZE);
        ballButton.getBtn().addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                levelDetails.generateBall();
            return true;

            }
        });
        ballButton.addToStage(stage);
        blockButtons = new Array<>();
        BlockButton temp;
        for (int i = 0; i < MAX_COLUMN; i++) {
            for (int j = 0; j < MAX_RAW; j++) {
                temp =  new BlockButton(0, (i+5)*BLOCK_SIZE, (j+1)*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                blockButtons.add(temp);
                temp.addToStage(stage);
            }
        }


        InputMultiplexer multiplexer = new InputMultiplexer();
        Gdx.input.setInputProcessor(multiplexer);
        multiplexer.addProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.90f, 0.90f, 0.90f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        viewManager.apply(batch, stage);

        batch.begin();
        batch.draw(background,0,0);
        batch.end();

        stage.draw();
        stage.act();

        //Draw over stage
        batch.begin();
        levelDetails.draw(batch, font);
        batch.end();

        levelDetails.update(blockButtons);
        if(Gdx.input.isKeyJustPressed(Input.Keys.BACK)){
            MainGame.getInstance().setScreen(new LevelMap());
        }
    }

    @Override
    public void resize(int width, int height) {
        //viewport.update(width, height, true);
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
        //batch.dispose();
       // background.dispose();
        //font.dispose();

    }



}
