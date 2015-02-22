package org.avrj.snake3d.listeners;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import org.avrj.snake3d.Snake3D;

/**
 * Listens the keyboard events in GameOverScene
 *
 */
public class GameOverKeyListener extends InputAdapter {

    private final Snake3D snake3d;

    /**
     * Sets snake3d variable
     *
     * @param snake3d The main game class
     */
    public GameOverKeyListener(Snake3D snake3d) {
        this.snake3d = snake3d;
    }

    /**
     * Overrides the keyDown method
     *
     * @param keyCode The keycode of the event
     * @return true if any event is handled
     */
    @Override
    public boolean keyDown(int keyCode) {
        if (keyCode == Input.Keys.ESCAPE) {
            snake3d.getInputMultiplexer().removeProcessor(this);

            Gdx.app.exit();

            return true;
        } else if (keyCode == Input.Keys.SPACE) {
            snake3d.getInputMultiplexer().removeProcessor(this);

            snake3d.getScreen().setDone();

            return true;
        }
        return false;
    }
}
