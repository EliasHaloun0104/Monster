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
import com.mygdx.game.main.MainGame;

import static com.mygdx.game.main.Constant.BLOCK_SIZE;

public class MainMenu implements Screen {
    private SpriteBatch batch;
	private Texture background;


    private Stage stage;
    private Array<Button> buttons;

    private int courser;
    private Sprite courserImage;


    @Override
    public void show() {
        batch = new SpriteBatch();
        background = new Texture("background.png");
        buttons = new Array<Button>();
        courser = 0;
        stage = new Stage();
        float xPos = (Gdx.graphics.getWidth()/4- BLOCK_SIZE*3);

        buttons.add(new Button("startButton", -1));
        buttons.add(new Button("loadButton", -1));
        buttons.add(new Button("aboutButton", -1));
        buttons.add(new Button("highScoreButton", -1));
        buttons.add(new Button("exitButton", -1));


        for (int i = 0; i < buttons.size ; i++) {
            final Button b = buttons.get(i);
            b.getBtn().setPosition(xPos + i%2*BLOCK_SIZE*2, BLOCK_SIZE*(5-i));
            final int finalI = i;
            b.getBtn().addListener(new InputListener(){
                @Override
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    courserImage.setPosition(b.getBtn().getX(), b.getBtn().getY());
                    super.enter(event, x, y, pointer, fromActor);
                }

                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    switch (finalI){
                        case 0:
                            MainGame.getInstance().setScreen(new Play());
                            break;
                        case 1:
                            MainGame.getInstance().setScreen(new InstructionScreen());
                            break;
                        case 2:
                            MainGame.getInstance().setScreen(new AboutScreen());
                            break;
                        case 3:
                            MainGame.getInstance().setScreen(new HighScore());
                            break;
                        case 4:
                            Gdx.app.exit();
                            break;

                    }
                    return super.touchDown(event, x, y, pointer, button);
                }
            });
        }


        courserImage = new Sprite(Assets.getInstance().getRegion("courser", -1));
        courserImage.setPosition(xPos, BLOCK_SIZE*5);



        for (Button b: buttons) {
            stage.addActor(b.getBtn());
        }

        Gdx.input.setInputProcessor(stage);
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

        courserImage.draw(batch);
        batch.end();

        update();


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
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            courser--;
            if(courser<0){
                courser = 4;
            }
            courserImage.setPosition(buttons.get(courser).getBtn().getX(),buttons.get(courser).getBtn().getY());
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
            courser++;
            if(courser>4){
                courser = 0;
            }
            courserImage.setPosition(buttons.get(courser).getBtn().getX(),buttons.get(courser).getBtn().getY());
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){

        }
    }


}
