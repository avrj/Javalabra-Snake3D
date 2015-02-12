package org.avrj.snake3d.scenes;

import com.badlogic.gdx.Screen;
import org.avrj.snake3d.Snake3D;

public abstract class Snake3DScene implements Screen {

    protected Snake3D snake3d;

    public Snake3DScene(Snake3D snake3d) {
        this.snake3d = snake3d;
    }

    public abstract boolean isDone();

    public abstract void setDone();
    
    public abstract void update(float delta);
    public abstract void draw(float delta);

    @Override
    public void render(float delta) {
        update(delta);
        draw(delta);
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }

}
