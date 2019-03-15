package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
import com.mygdx.game.game.Button;
import com.mygdx.game.main.MainGame;

import static com.mygdx.game.main.Constant.BLOCK_SIZE;

public class LevelMap implements Screen {
    Basic screen;
    private Texture background;

    private final int  numberOfLevel = 1;
    private Array<Button> buttons;
    @Override
    public void show() {
        screen = new Basic();

        buttons = new Array<>();
        for (int i = 0; i < numberOfLevel ; i++) {
            buttons.add(new Button("block",-1, screen.assets));
        }
        for (int i = 0; i < numberOfLevel ; i++) {
            buttons.get(i).getBtn().setPosition((1 + i*3)*BLOCK_SIZE, 5*BLOCK_SIZE);
            buttons.get(i).getBtn().setHeight(BLOCK_SIZE*2);
            buttons.get(i).getBtn().setWidth(BLOCK_SIZE*2);
            buttons.get(i).getBtn().getImage().setScaling(Scaling.fill);
            buttons.get(i).getBtn().addListener(new InputListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    MainGame.getInstance().setScreen(new Play());
                    return false;
                }
            });
            screen.stage.addActor(buttons.get(i).getBtn());
        }
        background = new Texture("background2.png");
    }

    @Override
    public void render(float delta) {
        screen.render();

        screen.batch.begin();
        screen.batch.draw(background,0,0);
        screen.batch.end();

        screen.stage.act();
        screen.stage.draw();

        if(Gdx.input.isKeyJustPressed(Input.Keys.BACK)){
            //TODO move between Screens
            MainGame.getInstance().setScreen(new LevelMap());
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
