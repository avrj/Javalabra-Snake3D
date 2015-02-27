package org.avrj.snake3d.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
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
    private String titleText, playAgainGameText, finalScoreText, exitText;

    public GameOverScene(Snake3D snake3d) {
        super(snake3d);

        snake3d.setGameState(GameState.Paused);

        stage = new Stage();
        
        addLabels();

        snake3d.getInputMultiplexer().addProcessor(new GameOverKeyListener(snake3d));
        snake3d.scoreBoard().saveScore();
    }

    private void addLabels() {
        setLabelTexts();
        createLabels();

        stage.addActor(titleLabel);
        stage.addActor(finalScoreLabel);
        stage.addActor(playAgainGameLabel);
        stage.addActor(exitLabel);
    }

    private void setLabelTexts() {
        finalScoreText = "Final score: " + snake3d.scoreBoard().getScore() + " points";
        titleText = "Game Over";
        playAgainGameText = "Press [SPACE] to play again";
        exitText = "[ESC] to exit";
    }

    private void createLabels() {
        titleFont = generateFont(100);
        titleLabel = createLabel(stage.getWidth() / 2 - (titleFont.getBounds(titleText).width / 2), stage.getHeight() / 2, titleFont);

        finalScoreFont = generateFont(20);
        finalScoreLabel = createLabel(stage.getWidth() / 2 - (finalScoreFont.getBounds(finalScoreText).width / 2), stage.getHeight() / 2 - 70, finalScoreFont);
        
        playAgainGameFont = generateFont(30);
        playAgainGameLabel = createLabel(stage.getWidth() / 2 - (playAgainGameFont.getBounds(playAgainGameText).width / 2), stage.getHeight() / 2 - 150, playAgainGameFont);
        
        exitFont = generateFont(20);
        exitLabel = createLabel(stage.getWidth() / 2 - (exitFont.getBounds(exitText).width / 2), 100, exitFont);
    }

    private Label createLabel(float x, float y, BitmapFont titleFont) {
        Label label = new Label(" ", new Label.LabelStyle(titleFont, Color.WHITE));
        label.setPosition(x, y);

        return label;
    }

    @Override
    public void dispose() {
        titleFont.dispose();
        playAgainGameFont.dispose();
        exitFont.dispose();
        stage.dispose();
    }

    private BitmapFont generateFont(int fontSize) {
        FreeTypeFontParameter parameters = new FreeTypeFontParameter();

        parameters.size = fontSize;
        return snake3d.getFontGenerator().generateFont(parameters);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void draw(float delta) {
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        setLabels();

        stage.draw();
    }

    private void setLabels() {
        titleLabel.setText(titleText);
        finalScoreLabel.setText(finalScoreText);
        playAgainGameLabel.setText(playAgainGameText);
        exitLabel.setText(exitText);
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
