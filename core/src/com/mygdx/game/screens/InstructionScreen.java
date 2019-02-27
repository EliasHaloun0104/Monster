package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.main.MainGame;

public class InstructionScreen implements Screen {
    Basic screen;

    private Texture background;

    @Override
    public void show() {
        screen = new Basic();
        background = new Texture("bg_instruction.png");
    }

    @Override
    public void render(float delta) {

        screen.render();
        screen.batch.begin();
        screen.batch.draw(background,0,0);
        screen.batch.end();
        screen.stage.draw();
        screen.stage.act();

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
        screen.dispose();
    }
}
