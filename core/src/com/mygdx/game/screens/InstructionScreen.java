package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.game.BackButton;

public class InstructionScreen implements Screen {
    private SpriteBatch batch;
    private Texture background;
    private Stage stage;
    private BackButton buttonBack;

    @Override
    public void show() {
        batch = new SpriteBatch();
        background = new Texture("bg_instruction.png");
        buttonBack = new BackButton();
        stage = new Stage();
        stage.addActor(buttonBack.getBtn());
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.8f, 0.7f, 1, 0.4f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(background,0,0);
        batch.end();
        stage.draw();
        stage.act();
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
