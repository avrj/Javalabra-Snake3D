package org.avrj.snake3d.listeners;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import org.avrj.snake3d.Snake3DGame;

/**
 * Listens keyboard events in every scene
 */
public class CommonKeyListener extends InputAdapter {

    /**
     * Overrides the keyDown method
     *
     * @param keycode The keycode of the event
     * @return returns true if any event is handled
     */
    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.ENTER) {
            if (!Gdx.graphics.isFullscreen()) {
                Gdx.graphics.setDisplayMode(Gdx.graphics.getDisplayModes()[0]);
            } else {
                Gdx.graphics.setDisplayMode(Snake3DGame.WINDOW_WIDTH, Snake3DGame.WINDOW_HEIGHT, false);
            }

            return true;
        }

        return false;
    }
}
