package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.game.Button;
import com.mygdx.game.main.Assets;
import com.mygdx.game.main.LevelDetails;
import com.mygdx.game.main.MainGame;

import static com.mygdx.game.main.Constant.BLOCK_SIZE;

public class MainMenu implements Screen {
    private SpriteBatch batch;
	private Texture background;


    private Stage stage;
    private Array<Button> buttons;


    @Override
    public void show() {
        batch = new SpriteBatch();
        background = new Texture("background.png");
        buttons = new Array<Button>();
        stage = new Stage();
        float xPos = (Gdx.graphics.getWidth()/4- BLOCK_SIZE*3);

        buttons.add(new Button("levelButton", -1));
        buttons.add(new Button("leaderButton", -1));
        buttons.add(new Button("howTobutton", -1));
        buttons.add(new Button("whoWeAre", -1));
        buttons.add(new Button("exitButton", -1));


        for (int i = 0; i < buttons.size ; i++) {
            final Button b = buttons.get(i);
            b.getBtn().setPosition(xPos + i%2*BLOCK_SIZE*2, BLOCK_SIZE*(5-i));
            final int finalI = i;
            b.getBtn().addListener(new InputListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    switch (finalI){
                        case 0:
                            MainGame.getInstance().setScreen(new LevelMap());
                            break;
                        case 1:
                            MainGame.getInstance().setScreen(new HighScore());
                            break;
                        case 2:
                            MainGame.getInstance().setScreen(new InstructionScreen());
                            break;
                        case 3:
                            MainGame.getInstance().setScreen(new AboutScreen());
                            break;
                        case 5:
                            Gdx.app.exit();
                            break;

                    }
                    return super.touchDown(event, x, y, pointer, button);
                }
            });
        }


        for (Button b: buttons) {
            stage.addActor(b.getBtn());
        }

        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.8f, 0.7f, 1, 0.4f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(background,0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        stage.draw();
        stage.act();

        batch.begin();

        batch.end();

        update();
        if(Gdx.input.isKeyPressed(Input.Keys.BACK)){
            //TODO move between Screens
            Gdx.app.exit();
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
	public void dispose () {
		batch.dispose();

	}

    public void update() {

    }


}
