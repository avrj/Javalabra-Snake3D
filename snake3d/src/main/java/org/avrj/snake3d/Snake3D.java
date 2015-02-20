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
import org.avrj.snake3d.logic.Apple;
import org.avrj.snake3d.logic.Camera;
import org.avrj.snake3d.logic.GameSurface;
import org.avrj.snake3d.logic.ScoreBoard;
import org.avrj.snake3d.logic.Snake;
import org.avrj.snake3d.scenes.Snake3DScene;
import org.avrj.snake3d.scenes.GameLoopScene;
import org.avrj.snake3d.scenes.GameOverScene;
import org.avrj.snake3d.scenes.MainMenuScene;
import org.avrj.snake3d.scenes.ScoreBoardScene;

public class Snake3D extends Game {

    private Apple apple;
    private Snake snake;
    private Camera camera;
    private GameSurface gameSurface;
    private ScoreBoard scoreBoard;
    private GameState gameState;

    private final InputMultiplexer inputMultiplexer = new InputMultiplexer();

    private InputProcessor currentInputProcessor;

    public ScoreBoardScene scoreBoardScene;
    public MainMenuScene mainMenuScene;

    public Apple apple() {
        return apple;
    }

    public Snake snake() {
        return snake;
    }

    public Camera camera() {
        return camera;
    }

    public GameSurface gameSurface() {
        return gameSurface;
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

    @Override
    public void render() {
        Snake3DScene currentScreen = getScreen();

        currentScreen.render(Gdx.graphics.getDeltaTime());

        handleSceneChanges(currentScreen);

    }

    private void handleSceneChanges(Snake3DScene currentScreen) {
        if (currentScreen.isDone()) {
            currentScreen.dispose();

            if (currentInputProcessor != null) {
                inputMultiplexer.removeProcessor(currentInputProcessor);
            }

            if (currentScreen instanceof MainMenuScene) {
                setScene(new GameLoopScene(this), new GameLoopKeyListener(this));
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

    @Override
    public void create() {
        apple = new Apple();
        snake = new Snake();
        camera = new Camera();
        gameSurface = new GameSurface();
        scoreBoard = new ScoreBoard();
        gameState = GameState.Paused;
        scoreBoardScene = new ScoreBoardScene(this);
        mainMenuScene = new MainMenuScene(this);

        apple.moveAppleToRandomPosition(gameSurface.getVectors(), snake.getVectors());

        Gdx.input.setInputProcessor(inputMultiplexer);

        inputMultiplexer.addProcessor(new CommonKeyListener());

        setScene(new MainMenuScene(this), null);
    }

    public void setScene(Snake3DScene scene, InputProcessor inputProcessor) {
        setScreen(scene);

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
}
