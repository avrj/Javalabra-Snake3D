package org.avrj.snake3d.scenes;

import org.avrj.snake3d.graphics.GameRenderer;
import org.avrj.snake3d.simulation.GameSimulation;
import org.avrj.snake3d.Snake3D;
import org.avrj.snake3d.listeners.GameLoopKeyListener;

public class GameLoopScene extends Snake3DScene {

    private final GameSimulation simulation;
    private final GameRenderer renderer;
    public boolean isDone = false;
    private double snakeSpeed = 0.5;

    public GameLoopScene(Snake3D snake3d) {
        super(snake3d);

        simulation = new GameSimulation();
        renderer = new GameRenderer();

        snake3d.multiplexer.addProcessor(new GameLoopKeyListener(snake3d, this, simulation));
    }

    @Override
    public void dispose() {
        renderer.dispose();
        simulation.dispose();
    }
    private float timer = 0;

    @Override
    public void update(float delta) {
        simulation.update();
        
        if (timer >= snakeSpeed) {
            timer -= snakeSpeed;

            simulation.moveSnake();
        }
        
        if (simulation.checkSnakeAppleCollision()) {
            simulation.growSnake();
            simulation.deployApple();
        }
        
        if(simulation.checkSnakeOutOfGameAreaCollision()) {
            isDone = true;
        }
        
        
        timer += delta;
    }

    @Override
    public void draw(float delta) {
        renderer.render(simulation, delta);
    }

    @Override
    public boolean isDone() {
        return isDone;
    }

}
