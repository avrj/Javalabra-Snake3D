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

    /**
     * Overrides the keyDown method
     *
     * @param keyCode The keycode of the event
     * @return true if any event is handled
     */
    @Override
    public boolean keyDown(int keyCode) {
        if (keyCode == Input.Keys.LEFT) {
            switch (snake3d.gameLoopScene.snake().getDirection()) {
                case UP:
                    snake3d.gameLoopScene.snake().setDirection(SnakeDirection.LEFT);

                    snake3d.gameLoopScene.camera().setAngle(0);
                    break;
                case LEFT:
                    snake3d.gameLoopScene.snake().setDirection(SnakeDirection.DOWN);

                    snake3d.gameLoopScene.camera().setAngle(90);
                    break;
                case DOWN:
                    snake3d.gameLoopScene.snake().setDirection(SnakeDirection.RIGHT);

                    snake3d.gameLoopScene.camera().setAngle(180);
                    break;
                case RIGHT:
                    snake3d.gameLoopScene.snake().setDirection(SnakeDirection.UP);

                    snake3d.gameLoopScene.camera().setAngle(270);
                    break;
            }

            return true;
        } else if (keyCode == Input.Keys.RIGHT) {
            switch (snake3d.gameLoopScene.snake().getDirection()) {
                case UP:
                    snake3d.gameLoopScene.snake().setDirection(SnakeDirection.RIGHT);

                    snake3d.gameLoopScene.camera().setAngle(-180);
                    break;
                case LEFT:
                    snake3d.gameLoopScene.snake().setDirection(SnakeDirection.UP);

                    snake3d.gameLoopScene.camera().setAngle(-90);
                    break;
                case DOWN:
                    snake3d.gameLoopScene.snake().setDirection(SnakeDirection.LEFT);

                    snake3d.gameLoopScene.camera().setAngle(0);
                    break;
                case RIGHT:
                    snake3d.gameLoopScene.snake().setDirection(SnakeDirection.DOWN);

                    snake3d.gameLoopScene.camera().setAngle(90);
                    break;
            }

            return true;
        } else if (keyCode == Input.Keys.ESCAPE) {
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
