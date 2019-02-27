package com.mygdx.game.main;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import static com.mygdx.game.main.Constant.WORLD_HEIGHT;
import static com.mygdx.game.main.Constant.WORLD_WIDTH;


public class ViewManager {
    int worldWidth;
    int worldHeight;
    private OrthographicCamera camera;
    private Viewport viewport;


    public ViewManager(int width, int height, int worldWidth, int worldHeight) {
        camera = new OrthographicCamera(width, height);
        camera.setToOrtho(false);
        viewport = new ExtendViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;

    }

    public void apply(SpriteBatch batch){
        viewport.apply();
        batch.setProjectionMatrix(camera.combined);
    }


    public Viewport getViewport() {
        return viewport;
    }


}
