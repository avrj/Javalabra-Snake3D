package org.avrj.snake3d.listeners;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import org.avrj.snake3d.Snake3D;
import org.avrj.snake3d.helpers.GameState;
import org.avrj.snake3d.helpers.SnakeDirection;

/**
 * Listens keyboard events in ScoreBoardScene
 *
 */
public class ScoreBoardKeyListener extends InputAdapter {

    private final Snake3D snake3d;

    /**
     * Sets snake3d variable
     *
     * @param snake3d The main game class
     */
    public ScoreBoardKeyListener(Snake3D snake3d) {
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
            snake3d.setScene(snake3d.mainMenuScene, new MainMenuKeyListener(snake3d));
            
            return true;
        }
        
        return false;
    }
}
