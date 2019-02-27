package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.game.Button;
import com.mygdx.game.main.MainGame;

import static com.mygdx.game.main.Constant.BLOCK_SIZE;

public class MainMenu implements Screen {
    Basic screen;
	private Texture background;
	private Array<Button> buttons;


    @Override
    public void show() {
        screen = new Basic();
        background = new Texture("background.png");
        buttons = new Array<>();
        int xPos = BLOCK_SIZE*2;

        buttons.add(new Button("levelButton", -1, screen.assets));
        buttons.add(new Button("leaderButton", -1, screen.assets));
        buttons.add(new Button("howTobutton", -1, screen.assets));
        buttons.add(new Button("whoWeAre", -1, screen.assets));
        buttons.add(new Button("exitButton", -1, screen.assets));


        for (int i = 0; i < buttons.size ; i++) {
            final Button b = buttons.get(i);
            b.getBtn().setPosition(xPos, BLOCK_SIZE*(4.2f-i));
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
                        case 4:
                            Gdx.app.exit();
                            break;

                    }
                    return super.touchDown(event, x, y, pointer, button);
                }
            });
        }


        for (Button b: buttons) {
            screen.stage.addActor(b.getBtn());
        }

        Gdx.input.setCatchBackKey(true);

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
		screen.dispose();
	}



}
