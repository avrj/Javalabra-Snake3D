package org.avrj.snake3d;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import org.avrj.snake3d.helpers.GameState;
import org.avrj.snake3d.listeners.CommonKeyListener;
import org.avrj.snake3d.listeners.GameLoopKeyListener;
import org.avrj.snake3d.listeners.GameOverKeyListener;
import org.avrj.snake3d.listeners.ScoreBoardKeyListener;
import org.avrj.snake3d.logic.ScoreBoard;
import org.avrj.snake3d.scenes.Snake3DScene;
import org.avrj.snake3d.scenes.GameLoopScene;
import org.avrj.snake3d.scenes.GameOverScene;
import org.avrj.snake3d.scenes.MainMenuScene;
import org.avrj.snake3d.scenes.ScoreBoardScene;

public class Snake3D extends Game {

    private ScoreBoard scoreBoard;
    private GameState gameState;

    private InputMultiplexer inputMultiplexer;

    private InputProcessor currentInputProcessor;

    public GameLoopScene gameLoopScene;
    
    @Override
    public void create() {
        inputMultiplexer = new InputMultiplexer();
        
        scoreBoard = new ScoreBoard();

        gameState = GameState.Paused;

        Gdx.input.setInputProcessor(inputMultiplexer);

        inputMultiplexer.addProcessor(new CommonKeyListener());

        setScene(new MainMenuScene(this), null);
    }

    @Override
    public void render() {
        Snake3DScene currentScreen = getScreen();

        currentScreen.render(Gdx.graphics.getDeltaTime());

        handleSceneChanges(currentScreen);

    }

    private void handleSceneChanges(Snake3DScene currentScreen) {
        if (currentScreen.isDone()) {
            if (currentScreen instanceof MainMenuScene) {
                setScene(gameLoopScene = new GameLoopScene(this), new GameLoopKeyListener(this));
            } else if (currentScreen instanceof ScoreBoardScene) {
                setScene(new ScoreBoardScene(this), new ScoreBoardKeyListener(this));
            } else {
                if (currentScreen instanceof GameLoopScene) {
                    setScene(new GameOverScene(this), new GameOverKeyListener(this));
                } else if (currentScreen instanceof GameOverScene) {
                    setScene(new MainMenuScene(this), null);
                }
            }
        }
    }

    @Override
    public Snake3DScene getScreen() {
        return (Snake3DScene) super.getScreen();
    }

    public void setScene(Snake3DScene scene, InputProcessor inputProcessor) {
        Snake3DScene currentScreen = getScreen();

        if (currentScreen != null) {
            currentScreen.dispose();
        }

        if (currentInputProcessor != null) {
            inputMultiplexer.removeProcessor(currentInputProcessor);
        }

        setScreen(scene);

        setCurrentInputProcessor(inputProcessor);
    }

    private void setCurrentInputProcessor(InputProcessor inputProcessor) {
        if (inputProcessor != null) {
            currentInputProcessor = inputProcessor;

            inputMultiplexer.addProcessor(inputProcessor);
        }
    }

    private InputProcessor getCurrentInputProcessor() {
        return currentInputProcessor;
    }

    public InputMultiplexer getInputMultiplexer() {
        return inputMultiplexer;
    }

    public ScoreBoard scoreBoard() {
        return scoreBoard;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
}
