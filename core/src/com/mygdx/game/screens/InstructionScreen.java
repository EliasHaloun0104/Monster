package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.main.MainGame;
import com.mygdx.game.main.ViewManager;

public class InstructionScreen implements Screen {
    private ViewManager viewManager;

    private SpriteBatch batch;
    private Texture background;
    private Stage stage;

    @Override
    public void show() {
        batch = new SpriteBatch();
        background = new Texture("bg_instruction.png");
        viewManager = new ViewManager(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        stage = new Stage(viewManager.getViewport());
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.90f, 0.90f, 0.90f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        viewManager.apply(batch);

        batch.begin();
        batch.draw(background,0,0);
        batch.end();
        stage.draw();
        stage.act();

        if(Gdx.input.isKeyJustPressed(Input.Keys.BACK)){
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
