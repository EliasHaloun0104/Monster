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

    public void draw(SpriteBatch batch){
    }

    public Matrix4 getMatrix(){
        return camera.combined;
    }


    public void apply(SpriteBatch batch){
        viewport.apply();
        batch.setProjectionMatrix(camera.combined);
    }

    public void resize(int x, int y){
        viewport.update(x,y, true);
    }

    public Viewport getViewport() {
        return viewport;
    }

    public void updateCamera(int moveX, int moveY){
        camera.position.set(camera.position.x+moveX,camera.position.y+moveY,0);

    }

    public void zoom(float a){
        if(camera.zoom>=1)
            camera.zoom +=a;
        if(camera.zoom <1){
            camera.zoom=1;
        }
    }
}
