package org.avrj.snake3d.listeners;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import org.avrj.snake3d.Snake3D;
import org.avrj.snake3d.helpers.GameState;
import org.avrj.snake3d.helpers.SnakeDirection;

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
    private SnakeDirection tempDirection = SnakeDirection.UP;

    /**
     * Overrides the keyDown method
     *
     * @param keyCode The keycode of the event
     * @return true if any event is handled
     */
    @Override
    public boolean keyDown(int keyCode) {
        switch (keyCode) {
            case Input.Keys.LEFT:
                if (tempDirection.equals(SnakeDirection.UP)) {
                    snake3d.snake().setDirection(SnakeDirection.LEFT);
                    tempDirection = SnakeDirection.LEFT;
                    snake3d.camera().setAngle(0);
                } else if (tempDirection.equals(SnakeDirection.LEFT)) {
                    snake3d.snake().setDirection(SnakeDirection.DOWN);
                    tempDirection = SnakeDirection.DOWN;
                    snake3d.camera().setAngle(90);
                } else if (tempDirection.equals(SnakeDirection.DOWN)) {
                    snake3d.snake().setDirection(SnakeDirection.RIGHT);
                    tempDirection = SnakeDirection.RIGHT;
                    snake3d.camera().setAngle(180);
                } else if (tempDirection.equals(SnakeDirection.RIGHT)) {
                    snake3d.snake().setDirection(SnakeDirection.UP);
                    tempDirection = SnakeDirection.UP;
                    snake3d.camera().setAngle(270);
                }

                return true;
            case Input.Keys.RIGHT:
                if (tempDirection.equals(SnakeDirection.UP)) {
                    snake3d.snake().setDirection(SnakeDirection.RIGHT);
                    tempDirection = SnakeDirection.RIGHT;

                    snake3d.camera().setAngle(-180);
                } else if (tempDirection.equals(SnakeDirection.LEFT)) {
                    snake3d.snake().setDirection(SnakeDirection.UP);
                    tempDirection = SnakeDirection.UP;
                    snake3d.camera().setAngle(-90);
                } else if (tempDirection.equals(SnakeDirection.DOWN)) {
                    snake3d.snake().setDirection(SnakeDirection.LEFT);
                    tempDirection = SnakeDirection.LEFT;
                    snake3d.camera().setAngle(0);
                } else if (tempDirection.equals(SnakeDirection.RIGHT)) {
                    snake3d.snake().setDirection(SnakeDirection.DOWN);
                    tempDirection = SnakeDirection.DOWN;
                    snake3d.camera().setAngle(90);
                }

                return true;
            case Input.Keys.ESCAPE:
                if (snake3d.getGameState().equals(GameState.Running)) {
                    snake3d.setGameState(GameState.Paused);
                } else {
                    snake3d.setGameState(GameState.Running);
                }

                return true;
        }
        return false;
    }
}
