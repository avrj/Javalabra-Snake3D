package org.avrj.snake3d.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import org.avrj.snake3d.Snake3D;
import org.avrj.snake3d.listeners.GameOverKeyListener;

/**
 * Scene for game over
 */
public class GameOverScene extends Snake3DScene {

    private boolean isDone = false;
    private final Stage stage;
    private BitmapFont titleFont, playAgainGameFont, exitFont;
    private final Label titleLabel, playAgainGameLabel, exitLabel;
    private final StringBuilder stringBuilder;

    /**
     * Initializes variables
     *
     * @param snake3d the main game class
     */
    public GameOverScene(Snake3D snake3d) {
        super(snake3d);

        stage = new Stage();

        loadFont();

        titleLabel = new Label(" ", new Label.LabelStyle(titleFont, Color.WHITE));
        titleLabel.setPosition(stage.getWidth() / 2 - 200, stage.getHeight() / 2);

        playAgainGameLabel = new Label(" ", new Label.LabelStyle(playAgainGameFont, Color.WHITE));
        playAgainGameLabel.setPosition(stage.getWidth() / 2 - 100, stage.getHeight() / 2 - 100);

        exitLabel = new Label(" ", new Label.LabelStyle(exitFont, Color.WHITE));
        exitLabel.setPosition(stage.getWidth() / 2 + 100, stage.getHeight() / 2 - 170);

        stage.addActor(titleLabel);
        stage.addActor(playAgainGameLabel);
        stage.addActor(exitLabel);

        stringBuilder = new StringBuilder();

        snake3d.getInputMultiplexer().addProcessor(new GameOverKeyListener(snake3d));
    }

    /**
     * Called when this class is not used anymore
     */
    @Override
    public void dispose() {
        titleFont.dispose();
        playAgainGameFont.dispose();
        exitFont.dispose();
        stage.dispose();
    }

    private void loadFont() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("assets/fonts/AgentOrange.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = 100;
        titleFont = generator.generateFont(parameter);

        parameter.size = 30;
        playAgainGameFont = generator.generateFont(parameter);

        parameter.size = 20;
        exitFont = generator.generateFont(parameter);

        generator.dispose();
    }

    /**
     * Called when the screen is updated
     *
     * @param delta Deltatime value of the game
     */
    @Override
    public void update(float delta) {

    }

    /**
     * Called when the screen is redrawn
     *
     * @param delta Deltatime value of the game
     */
    @Override
    public void draw(float delta) {
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        stringBuilder.setLength(0);
        stringBuilder.append("Game over");

        titleLabel.setText(stringBuilder);

        stringBuilder.setLength(0);
        stringBuilder.append("Press [SPACE] to play again");

        playAgainGameLabel.setText(stringBuilder);

        stringBuilder.setLength(0);
        stringBuilder.append("[ESC] to exit");

        exitLabel.setText(stringBuilder);
        stage.draw();

        stage.draw();
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
