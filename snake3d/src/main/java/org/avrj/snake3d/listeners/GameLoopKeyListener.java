package org.avrj.snake3d.listeners;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import org.avrj.snake3d.Snake3D;
import org.avrj.snake3d.logic.SnakeDirection;
import org.avrj.snake3d.simulation.GameSimulation;

public class GameLoopKeyListener extends InputAdapter {

    private final Snake3D snake3d;
    private final GameSimulation simulation;

    public GameLoopKeyListener(Snake3D snake3d, GameSimulation simulation) {
        this.snake3d = snake3d;
        this.simulation = simulation;
    }

    @Override
    public boolean keyDown(int keyCode) {
        switch (keyCode) {
            case Input.Keys.Q:
                snake3d.getScreen().setDone();
                snake3d.multiplexer.removeProcessor(this);
                
                return true;
            case Input.Keys.UP:
                if (!simulation.snakeDirection.equals(SnakeDirection.DOWN)) {
                    simulation.snakeDirection = SnakeDirection.UP;
                }

                return true;
            case Input.Keys.DOWN:
                if (!simulation.snakeDirection.equals(SnakeDirection.UP)) {
                    simulation.snakeDirection = SnakeDirection.DOWN;
                }

                return true;
            case Input.Keys.LEFT:
                if (!simulation.snakeDirection.equals(SnakeDirection.RIGHT)) {
                    simulation.snakeDirection = SnakeDirection.LEFT;
                }

                return true;
            case Input.Keys.RIGHT:
                if (!simulation.snakeDirection.equals(SnakeDirection.LEFT)) {
                    simulation.snakeDirection = SnakeDirection.RIGHT;
                }

                return true;
        }

        return false;
    }
}
