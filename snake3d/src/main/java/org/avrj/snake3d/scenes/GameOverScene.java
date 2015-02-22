package org.avrj.snake3d.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import org.avrj.snake3d.Snake3D;
import org.avrj.snake3d.helpers.GameState;
import org.avrj.snake3d.listeners.GameOverKeyListener;

public class GameOverScene extends Snake3DScene {

    private boolean isDone = false;
    private final Stage stage;
    private BitmapFont titleFont, playAgainGameFont, exitFont, finalScoreFont;
    private Label titleLabel, playAgainGameLabel, exitLabel, finalScoreLabel;
    private final StringBuilder stringBuilder;
    private String titleText, playAgainGameText, finalScoreText, exitText;

    public GameOverScene(Snake3D snake3d) {
        super(snake3d);

        snake3d.setGameState(GameState.Paused);

        finalScoreText = "Final score: " + snake3d.scoreBoard().getScore() + " points";
        titleText = "Game Over";
        playAgainGameText = "Press [SPACE] to play again";
        exitText = "[ESC] to exit";

        stage = new Stage();
        stringBuilder = new StringBuilder();

        loadFonts();
        createLabels();

        snake3d.getInputMultiplexer().addProcessor(new GameOverKeyListener(snake3d));
        snake3d.scoreBoard().saveScore();
    }

    private void createLabels() {
        titleLabel = new Label(" ", new Label.LabelStyle(titleFont, Color.WHITE));
        titleLabel.setPosition(stage.getWidth() / 2 - (titleFont.getBounds(titleText).width / 2), stage.getHeight() / 2);

        finalScoreLabel = new Label(" ", new Label.LabelStyle(finalScoreFont, Color.WHITE));
        finalScoreLabel.setPosition(stage.getWidth() / 2 - (finalScoreFont.getBounds(finalScoreText).width / 2), stage.getHeight() / 2 - 70);

        playAgainGameLabel = new Label(" ", new Label.LabelStyle(playAgainGameFont, Color.WHITE));
        playAgainGameLabel.setPosition(stage.getWidth() / 2 - (playAgainGameFont.getBounds(playAgainGameText).width / 2), stage.getHeight() / 2 - 150);

        exitLabel = new Label(" ", new Label.LabelStyle(exitFont, Color.WHITE));
        exitLabel.setPosition(stage.getWidth() / 2 - (exitFont.getBounds(exitText).width / 2), 100);

        stage.addActor(titleLabel);
        stage.addActor(finalScoreLabel);
        stage.addActor(playAgainGameLabel);
        stage.addActor(exitLabel);
    }

    @Override
    public void dispose() {
        titleFont.dispose();
        playAgainGameFont.dispose();
        exitFont.dispose();
        stage.dispose();
    }

    private void loadFonts() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("assets/fonts/AgentOrange.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = 100;
        titleFont = generator.generateFont(parameter);

        parameter.size = 30;
        playAgainGameFont = generator.generateFont(parameter);

        parameter.size = 20;
        exitFont = generator.generateFont(parameter);

        parameter.size = 20;
        finalScoreFont = generator.generateFont(parameter);

        generator.dispose();
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void draw(float delta) {
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        showTitleLabel();
        showFinalScoreLabel();
        showPlayAgainLabel();
        showExitLabel();

        stage.draw();
    }

    private void showTitleLabel() {
        stringBuilder.setLength(0);
        stringBuilder.append(titleText);

        titleLabel.setText(stringBuilder);
    }

    private void showFinalScoreLabel() {
        stringBuilder.setLength(0);
        stringBuilder.append(finalScoreText);

        finalScoreLabel.setText(stringBuilder);
    }

    private void showPlayAgainLabel() {
        stringBuilder.setLength(0);
        stringBuilder.append(playAgainGameText);

        playAgainGameLabel.setText(stringBuilder);
    }

    private void showExitLabel() {

        stringBuilder.setLength(0);
        stringBuilder.append(exitText);

        exitLabel.setText(stringBuilder);
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
