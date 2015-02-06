package org.avrj.snake3d.scenes;

import com.badlogic.gdx.Screen;
import org.avrj.snake3d.Snake3D;

/**
 * Defines every scene in the game
 *
 * @see Screen
 */
public abstract class Snake3DScene implements Screen {

    protected Snake3D snake3d;

    /**
     * Creates a new scene
     *
     * @param snake3d The main game class
     */
    public Snake3DScene(Snake3D snake3d) {
        this.snake3d = snake3d;
    }

    /**
     * Called when the screen is updated
     *
     * @param delta Deltatime of the game
     */
    public abstract void update(float delta);

    /**
     * Called when the screen is redrawn
     *
     * @param delta Deltatime of the game
     */
    public abstract void draw(float delta);

    public abstract boolean isDone();

    public abstract void setDone();

    /**
     * Renders every frame
     *
     * @param delta Deltatime of the game
     */
    @Override
    public void render(float delta) {
        update(delta);
        draw(delta);
    }

    /**
     * Called when the screen is resized
     *
     * @param width Width of the screen
     * @param height Height of the screen
     */
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
