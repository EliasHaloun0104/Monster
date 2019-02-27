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
import com.badlogic.gdx.utils.Scaling;
import com.mygdx.game.game.Button;
import com.mygdx.game.main.MainGame;
import com.mygdx.game.main.ViewManager;

import static com.mygdx.game.main.Constant.BLOCK_SIZE;

public class LevelMap implements Screen {
    private ViewManager viewManager;

    private SpriteBatch batch;
    private Texture background;

    private final int  numberOfLevel = 1;
    private Array<Button> buttons;
    private Stage stage;
    @Override
    public void show() {

        batch = new SpriteBatch();
        buttons = new Array<>();
        viewManager = new ViewManager();
        stage = new Stage(viewManager.getViewport());
        for (int i = 0; i < numberOfLevel ; i++) {
            buttons.add(new Button("block",-1));
        }
        for (int i = 0; i < numberOfLevel ; i++) {
            buttons.get(i).getBtn().setPosition((1 + i*3)*BLOCK_SIZE, 5*BLOCK_SIZE);
            buttons.get(i).getBtn().setHeight(BLOCK_SIZE*2);
            buttons.get(i).getBtn().setWidth(BLOCK_SIZE*2);
            buttons.get(i).getBtn().getImage().setScaling(Scaling.fill);
            buttons.get(i).getBtn().addListener(new InputListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    Gdx.app.log("EliasHaloun: ", "Level i");
                    MainGame.getInstance().setScreen(new Play());
                    return false;
                }
            });
            stage.addActor(buttons.get(i).getBtn());
        }
        background = new Texture("background2.png");

        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.90f, 0.90f, 0.90f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        viewManager.apply(batch, stage);

        batch.begin();
        batch.draw(background,0,0);
        batch.end();

        stage.act();
        stage.draw();

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
