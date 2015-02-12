package org.avrj.snake3d.listeners;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import org.avrj.snake3d.Snake3D;
import org.avrj.snake3d.logic.SnakeDirection;

/**
 * Listens keyboard events in GameLoopScene
 *
 */
public class GameLoopKeyListener extends InputAdapter {

    private final Snake3D snake3d;
    /**
     * Sets snake3d and simulation variables
     *
     * @param snake3d The main game class
     */
    public GameLoopKeyListener(Snake3D snake3d) {
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
        switch (keyCode) {
            case Input.Keys.UP:
                snake3d.snake().setDirection(SnakeDirection.UP);
                snake3d.camera().setTargetAngle(-90);
                return true;
            case Input.Keys.DOWN:
                snake3d.snake().setDirection(SnakeDirection.DOWN);
                return true;
            case Input.Keys.LEFT:
                snake3d.snake().setDirection(SnakeDirection.LEFT);
                snake3d.camera().setTargetAngle(90);
                return true;
            case Input.Keys.RIGHT:
                snake3d.snake().setDirection(SnakeDirection.RIGHT);
                snake3d.camera().setTargetAngle(-90);
                return true;
        }
        return false;
    }
}
