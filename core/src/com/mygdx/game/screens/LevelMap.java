package com.mygdx.game.screens;

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
    int numberOfLevel;

    @Override
    public void show() {
        screen = new Basic();
        numberOfLevel = 5;
        Array<Button> buttons;


        buttons = new Array<>();
        Button btn;
        for (int i = 0; i < numberOfLevel ; i++) {
            final int iExtra = i+1;
            btn = new Button("block", -1, screen.assets);
            btn.getBtn().setPosition((1 + i*3)*BLOCK_SIZE, 4*BLOCK_SIZE);
            btn.getBtn().setHeight(BLOCK_SIZE*2);
            btn.getBtn().setWidth(BLOCK_SIZE*2);
            btn.getBtn().getImage().setScaling(Scaling.fill);
            btn.getBtn().addListener(new InputListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    MainGame.getInstance().setScreen(new Play(iExtra,3,2,5));
                    return false;
                }
            });
            screen.stage.addActor(btn.getBtn());
            buttons.add(new Button("block",-1, screen.assets));
        }

        background = new Texture("background2.png");
    }

    @Override
    public void render(float delta) {
        screen.render();

        screen.batch.begin();
        screen.batch.draw(background,0,0);
        for (int i = 1; i <= numberOfLevel; i++) {
            screen.font.draw(screen.batch, "Level " + i, (1 + (i-1)*3)*BLOCK_SIZE, 4*BLOCK_SIZE);
        }
        screen.batch.end();

        screen.stage.act();
        screen.stage.draw();

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
