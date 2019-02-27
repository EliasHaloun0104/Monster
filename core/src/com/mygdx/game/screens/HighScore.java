package com.mygdx.game.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.game.Player;
import com.mygdx.game.main.MainGame;

import static com.mygdx.game.main.Constant.BLOCK_SIZE;


public class HighScore implements Screen {
    private Basic screen;

    private Texture background;
    private TextField textField;
    private TextField.TextFieldStyle textFieldStyle;


    private ImageButton gameButton;

    //TODO replace prefs by database

    Preferences prefs;
    Array<Player> players;

    private int indexOfScore;


    @Override
    public void show() {
        screen = new Basic();


        indexOfScore = -1;


        float yPos = Gdx.graphics.getHeight()/8;
        float xPos = Gdx.graphics.getWidth()/2;
        TextureRegionDrawable image_1 = new TextureRegionDrawable(screen.assets.getRegion("submitName",-1));
        gameButton = new ImageButton(image_1);
        gameButton.setPosition(xPos-BLOCK_SIZE*2, yPos);

        background = new Texture("background.png");


        textFieldStyle = new TextField.TextFieldStyle();
        textFieldStyle.font = screen.font;
        textFieldStyle.fontColor = Color.RED;
        textFieldStyle.background = new TextureRegionDrawable(screen.assets.getRegion("courser",-1));
        textField = new TextField(" ", textFieldStyle);
        textField.setWidth(gameButton.getWidth());
        textField.setHeight(gameButton.getHeight());
        textField.setAlignment(Align.center);
        textField.setPosition(gameButton.getX(),yPos+70);
        screen.stage.addActor(gameButton);
        screen.stage.addActor(textField);



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



        players = new Array<>();
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
        screen.render();

        screen.batch.begin();
        screen.batch.draw(background,0,0);
        for (int i = 0; i < players.size ; i++) {
            screen.font.draw(screen.batch, players.get(i).toString(), BLOCK_SIZE, BLOCK_SIZE*6 - i*BLOCK_SIZE/2);
        }
        screen.batch.end();

        //stage.draw();
        //stage.act();

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
