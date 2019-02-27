package com.mygdx.game.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.game.Player;
import com.mygdx.game.main.Assets;
import com.mygdx.game.main.MainGame;

import static com.mygdx.game.main.Constant.BLOCK_SIZE;


public class HighScore implements Screen {
    private SpriteBatch batch;
    private Texture background;
    private Stage stage;
    private TextField textField;
    private TextField.TextFieldStyle textFieldStyle;

    private BitmapFont font;
    private ImageButton gameButton;

    //TODO replace prefs by database

    Preferences prefs;
    Array<Player> players;

    private int indexOfScore;


    @Override
    public void show() {

        batch = new SpriteBatch();
        stage = new Stage();

        indexOfScore = -1;


        float yPos = Gdx.graphics.getHeight()/8;
        float xPos = Gdx.graphics.getWidth()/2;
        TextureRegionDrawable image_1 = new TextureRegionDrawable(Assets.getInstance().getRegion("submitName",-1));
        gameButton = new ImageButton(image_1);
        gameButton.setPosition(xPos-BLOCK_SIZE*2, yPos);

        background = new Texture("background.png");
        font = new BitmapFont();
        font.setColor(Color.RED);

        textFieldStyle = new TextField.TextFieldStyle();
        textFieldStyle.font = font;
        textFieldStyle.fontColor = Color.RED;
        textFieldStyle.background = new TextureRegionDrawable(Assets.getInstance().getRegion("courser",-1));
        textField = new TextField(" ", textFieldStyle);
        textField.setWidth(gameButton.getWidth());
        textField.setHeight(gameButton.getHeight());
        textField.setAlignment(Align.center);
        textField.setPosition(gameButton.getX(),yPos+70);
        stage.addActor(gameButton);
        stage.addActor(textField);



        gameButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(indexOfScore != -1){
                    players.get(indexOfScore).setName(textField.getText());
                    rewritePreference();
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });



        Gdx.input.setInputProcessor(stage);

        players = new Array<Player>();
        prefs = Gdx.app.getPreferences("HighScorePreferences");//Gets called My preferences
        if(!prefs.contains("P_1_name")){
            firstTimePreference();
        }else{
            getDataFromPreference();
        }

        updatePreferenceArray();





    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.8f, 0.7f, 1, 0.4f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(background,0,0);
        for (int i = 0; i < players.size ; i++) {
            font.draw(batch, players.get(i).toString(), BLOCK_SIZE, BLOCK_SIZE*6 - i*BLOCK_SIZE/2);
        }
        batch.end();

        stage.draw();
        stage.act();

        if(Gdx.input.isKeyPressed(Input.Keys.BACK)){
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


    public void firstTimePreference(){
        for (int i = 0; i < 10; i++) {
            String player = "P_" + (i+1);
            players.add(new Player("Player "+(i+1), (10-i)*50));
            prefs.putString(player+"_name", "Player " + (i+1));
            prefs.putInteger(player+"_score", (10-i)*50);
        }
        prefs.flush();
    }
    public void getDataFromPreference(){
        for (int i = 0; i < 10; i++) {
            String player = "P_" + (i+1);
            players.add(new Player(prefs.getString(player+"_name"),prefs.getInteger(player+"_score")));
            prefs.flush();
        }
    }
    public void rewritePreference(){
        for (int i = 0; i < 10; i++) {
            String player = "P_" + (i+1);
            prefs.putString(player+"_name", players.get(i).getName());
            prefs.putInteger(player+"_score", players.get(i).getScore());
            prefs.flush();
        }
    }

    public void updatePreferenceArray(){
        for (int i = 0; i < 10 ; i++) {
            if(players.get(i).getScore()< MainGame.getInstance().getScore()){
                indexOfScore = i;
                players.insert(i, new Player("", MainGame.getInstance().getScore() ));
                break;
            }
        }
        MainGame.getInstance().resetScore();
        rewritePreference();


    }




}
