package org.avrj.snake3d.scenes;

import org.avrj.snake3d.graphics.GameRenderer;
import org.avrj.snake3d.simulation.GameSimulation;
import org.avrj.snake3d.Snake3D;
import org.avrj.snake3d.listeners.GameLoopKeyListener;

/**
 * A scene for the game loop
 */
public class GameLoopScene extends Snake3DScene {

    private final GameSimulation simulation;
    private final GameRenderer renderer;
    private boolean isDone = false;

    private final GameLoopKeyListener keyListener;

    /**
     * Initializes variables
     *
     * @param snake3d The main game class
     */
    public GameLoopScene(Snake3D snake3d) {
        super(snake3d);

        simulation = new GameSimulation(snake3d);
        renderer = new GameRenderer();

        keyListener = new GameLoopKeyListener(snake3d, simulation);

        snake3d.getInputMultiplexer().addProcessor(keyListener);
    }

    /**
     * Called when this class is not used anymore
     *
     */
    @Override
    public void dispose() {
        renderer.dispose();
        simulation.dispose();
    }

    /**
     * Called when screen is updated
     *
     * @param delta Deltatime value of the game
     */
    @Override
    public void update(float delta) {
        simulation.update(delta);
    }

    /**
     * Called when the screen is redrawn
     *
     * @param delta Deltatime value of the game
     */
    @Override
    public void draw(float delta) {
        renderer.render(simulation, delta);
    }

    @Override
    public boolean isDone() {
        return isDone;
    }

    @Override
    public void setDone() {
        this.isDone = true;
    }

}
