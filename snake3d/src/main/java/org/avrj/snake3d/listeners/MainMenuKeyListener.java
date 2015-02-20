package org.avrj.snake3d.listeners;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import org.avrj.snake3d.Snake3D;

/**
 * Listens the keyboard events in MainMenuScene
 */
public class MainMenuKeyListener extends InputAdapter {

    private final Snake3D snake3d;

    /**
     * Sets the snake3d variable
     *
     * @param snake3d The main game class
     */
    public MainMenuKeyListener(Snake3D snake3d) {
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
        if (keyCode == Input.Keys.SPACE) {
            snake3d.getScreen().setDone();
            snake3d.getInputMultiplexer().removeProcessor(this);

            return true;
        }
        if (keyCode == Input.Keys.S) {
            snake3d.setScene(snake3d.scoreBoardScene, new ScoreBoardKeyListener(snake3d));
            return true;
        }
        return false;
    }
}
