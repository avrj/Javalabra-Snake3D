package org.avrj.snake3d.listeners;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import org.avrj.snake3d.Snake3D;
import org.avrj.snake3d.simulation.GameSimulation;
import org.avrj.snake3d.scenes.GameLoopScene;

public class GameLoopKeyListener extends InputAdapter {

    private final Snake3D snake3d;
    private GameLoopScene scene;
    private GameSimulation simulation;

    public GameLoopKeyListener(Snake3D snake3d, GameLoopScene scene, GameSimulation simulation) {
        this.snake3d = snake3d;
        this.scene = scene;
        this.simulation = simulation;
    }

    @Override
    public boolean keyDown(int keyCode) {
        if (keyCode == Input.Keys.Q) {
            scene.isDone = true;

            System.out.println("debug");

            snake3d.multiplexer.removeProcessor(this);

            return true;
        }

        if (keyCode == Input.Keys.UP) {
            if (!simulation.snakeDirection.equals("Z+")) {
                simulation.snakeDirection = "Z-";
            }

            return true;
        }
        if (keyCode == Input.Keys.DOWN) {
            if (!simulation.snakeDirection.equals("Z-")) {
                simulation.snakeDirection = "Z+";
            }

            return true;
        }
        if (keyCode == Input.Keys.LEFT) {
            if (!simulation.snakeDirection.equals("X+")) {
                simulation.snakeDirection = "X-";
            }
            return true;
        }
        if (keyCode == Input.Keys.RIGHT) {
            if (!simulation.snakeDirection.equals("X-")) {
                simulation.snakeDirection = "X+";
            }

            return true;
        }
        
        return false;
    }
}
