package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.game.BlockButton;
import com.mygdx.game.game.Button;
import com.mygdx.game.main.LevelDetails;
import com.mygdx.game.main.MainGame;

import static com.mygdx.game.main.Constant.BLOCK_SIZE;
import static com.mygdx.game.main.Constant.MAX_COLUMN;
import static com.mygdx.game.main.Constant.MAX_RAW;


public class Play implements Screen {
    Basic screen;


    private Texture background;
    private LevelDetails levelDetails;

    private Sound ping;
    private Sound bongo;
    private TextureRegion ball;




    //Buttons
    private Array<BlockButton> blockButtons;
    private Button ballButton;

    @Override
    public void show() {

        screen = new Basic();
        ping =  Gdx.audio.newSound(Gdx.files.internal("ping.wav"));
        bongo = Gdx.audio.newSound(Gdx.files.internal("bongo.wav"));
        ball = screen.assets.ball();


        levelDetails = new LevelDetails(1,3, 2, 5000);


        background = new Texture("background2.png");


        ballButton = new Button("block",-1, screen.assets);
        ballButton.getBtn().setPosition(13*BLOCK_SIZE, BLOCK_SIZE);
        ballButton.getBtn().addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                levelDetails.generateBall();
            return true;

            }
        });
        ballButton.addToStage(screen.stage);
        blockButtons = new Array<>();
        BlockButton temp;
        for (int i = 0; i < MAX_COLUMN; i++) {
            for (int j = 0; j < MAX_RAW; j++) {
                temp =  new BlockButton(0, (i+5)*BLOCK_SIZE, (j+1)*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE, screen.assets);
                blockButtons.add(temp);
                temp.addToStage(screen.stage);
            }
        }


        InputMultiplexer multiplexer = new InputMultiplexer();
        Gdx.input.setInputProcessor(multiplexer);
        multiplexer.addProcessor(screen.stage);
    }

    @Override
    public void render(float delta) {
        screen.render();

        screen.batch.begin();
        screen.batch.draw(background,0,0);
        screen.batch.end();

        screen.stage.draw();
        screen.stage.act();

        //Draw over stage
        screen.batch.begin();
        levelDetails.draw(screen.batch, screen.font, ball);
        screen.batch.end();

        levelDetails.update(blockButtons, screen.assets, ping, bongo);
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
        screen.dispose();
        background.dispose();
    }



}
