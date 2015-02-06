package org.avrj.snake3d.listeners;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import org.avrj.snake3d.Snake3D;
import org.avrj.snake3d.logic.SnakeDirection;
import org.avrj.snake3d.simulation.GameSimulation;

/**
 * Listens keyboard events in GameLoopScene
 *
 */
public class GameLoopKeyListener extends InputAdapter {

    private final Snake3D snake3d;
    private final GameSimulation simulation;

    /**
     * Sets snake3d and simulation variables
     *
     * @param snake3d The main game class
     * @param simulation The simulation helper class
     */
    public GameLoopKeyListener(Snake3D snake3d, GameSimulation simulation) {
        this.snake3d = snake3d;
        this.simulation = simulation;
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
                    simulation.setSnakeDirection(SnakeDirection.UP);

                    return true;
                case Input.Keys.DOWN:
                    simulation.setSnakeDirection(SnakeDirection.DOWN);

                    return true;
                case Input.Keys.LEFT:
                    simulation.setSnakeDirection(SnakeDirection.LEFT);

                    return true;
                case Input.Keys.RIGHT:
                    simulation.setSnakeDirection(SnakeDirection.RIGHT);

                    return true;
            }
        return false;
    }
}
