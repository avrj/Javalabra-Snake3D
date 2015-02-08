package org.avrj.snake3d;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import org.avrj.snake3d.listeners.CommonKeyListener;
import org.avrj.snake3d.scenes.Snake3DScene;
import org.avrj.snake3d.scenes.GameLoopScene;
import org.avrj.snake3d.scenes.GameOverScene;
import org.avrj.snake3d.scenes.MainMenuScene;

public class Snake3D extends Game {

    private final InputMultiplexer multiplexer = new InputMultiplexer();

    private int score = 0;

    /**
     * Renders the current screen
     */
    @Override
    public void render() {
        Snake3DScene currentScreen = getScreen();

        currentScreen.render(Gdx.graphics.getDeltaTime());

        if (currentScreen.isDone()) {
            currentScreen.dispose();

            if (currentScreen instanceof MainMenuScene) {
                setScreen(new GameLoopScene(this));
            } else {
                if (currentScreen instanceof GameLoopScene) {
                    setScreen(new GameOverScene(this));
                } else if (currentScreen instanceof GameOverScene) {
                    setScreen(new MainMenuScene(this));
                }
            }
        }

    }

    public void increaseScore() {
        score++;
    }

    public int getScore() {
        return score;
    }

    /**
     * Returns the current Snake3DScene instance
     *
     * @return current Snake3DScene instance / screen
     */
    @Override
    public Snake3DScene getScreen() {
        return (Snake3DScene) super.getScreen();
    }

    /**
     * Is called when this class is created Sets the current screen to
     * MainMenuScene
     */
    @Override
    public void create() {
        setScreen(new MainMenuScene(this));

        Gdx.input.setInputProcessor(multiplexer);

        multiplexer.addProcessor(new CommonKeyListener());
    }

    public InputMultiplexer getInputMultiplexer() {
        return multiplexer;
    }
}
