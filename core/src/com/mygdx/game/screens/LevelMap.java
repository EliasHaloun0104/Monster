package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.game.Button;
import com.mygdx.game.main.MainGame;

import static com.mygdx.game.main.Constant.BLOCK_SIZE;

public class LevelMap implements Screen {
    private SpriteBatch batch;
    private Texture background;

    private final int  numberOfLevel = 12;
    private Array<Button> buttons;
    private Stage stage;
    @Override
    public void show() {
        batch = new SpriteBatch();
        buttons = new Array<>();
        stage = new Stage();
        for (int i = 2; i < numberOfLevel*2+3  ; i+=2) {
            Button temp = new Button("block",-1);
            temp.getBtn().setPosition(i*BLOCK_SIZE, 5*BLOCK_SIZE);
            temp.getBtn().addListener(new InputListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    MainGame.getInstance().setScreen(new Play());
                    return super.touchDown(event, x, y, pointer, button);
                }
            });
            temp.addToStage(stage);
        }
        background = new Texture("background2.png");

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.90f, 0.90f, 0.90f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(background,0,0);
        batch.end();

        stage.act();
        stage.draw();

        if(Gdx.input.isKeyPressed(Input.Keys.BACK)){
            //TODO move between Screens
            MainGame.getInstance().setScreen(new MainMenu());
        }


    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
