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

public class AboutScreen implements Screen {
    private SpriteBatch batch;
    private Texture background;
    private Texture aboutUs;
    private Stage stage;
    private ViewManager viewManager;

    @Override
    public void show() {

        batch = new SpriteBatch();
        background = new Texture("background.png");
        aboutUs = new Texture("backgroundAbout.png");
        stage = new Stage();
        viewManager = new ViewManager();
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.90f, 0.90f, 0.90f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        viewManager.apply(batch, stage);

        batch.begin();
        //batch.draw(background,0,0);
        batch.draw(aboutUs,10,10);
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
        batch.dispose();
        background.dispose();
        stage.dispose();
    }
}
