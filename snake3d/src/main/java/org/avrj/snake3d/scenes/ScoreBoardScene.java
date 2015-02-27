package org.avrj.snake3d.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import java.util.ArrayList;
import org.avrj.snake3d.Snake3D;
import org.avrj.snake3d.helpers.ScoreBoardItem;
import org.avrj.snake3d.listeners.ScoreBoardKeyListener;

public class ScoreBoardScene extends Snake3DScene {

    private boolean isDone = false;
    private final Stage stage;
    private BitmapFont titleFont, scoreFont, returnToMainMenuFont;
    private Label titleLabel, returnToMainMenuLabel;
    private ArrayList<Label> scoreLabels;
    private final StringBuilder stringBuilder;
    private String titleText, returnToMainMenuText;
    private ArrayList<String> scoreTexts;

    public ScoreBoardScene(Snake3D snake3d) {
        super(snake3d);

        stage = new Stage();
        stringBuilder = new StringBuilder();
        scoreLabels = new ArrayList<>();
        scoreTexts = new ArrayList<>();

        addLabels();

        snake3d.getInputMultiplexer().addProcessor(new ScoreBoardKeyListener(snake3d));
    }

    private void setLabelTexts() {
        titleText = "High scores";
        returnToMainMenuText = "[ESC] to return to main menu";
    }

    private void addLabels() {
        setLabelTexts();
        createLabels();

        stage.addActor(titleLabel);

        for (Label scoreLabel : scoreLabels) {
            stage.addActor(scoreLabel);
        }

        stage.addActor(returnToMainMenuLabel);
    }

    private void createLabels() {
        titleFont = generateFont(50);
        titleLabel = createLabel(stage.getWidth() / 2 - (titleFont.getBounds(titleText).width / 2), stage.getHeight() - 100, titleFont);

        scoreFont = generateFont(20);
        int y = 0;
        for (ScoreBoardItem scoreBoardItem : snake3d.scoreBoard().getSavedScores()) {
            String timestamp = snake3d.scoreBoard().formatTimestamp(scoreBoardItem.getTimestamp());
            Integer score = scoreBoardItem.getScore();

            Label scoreLabel = createLabel(stage.getWidth() / 2 - (scoreFont.getBounds(timestamp + " " + score + " points").width / 2), stage.getHeight() - (160 + (35 * y)), scoreFont);
            scoreLabels.add(scoreLabel);

            y++;
        }

        returnToMainMenuFont = generateFont(20);
        returnToMainMenuLabel = createLabel(stage.getWidth() / 2 - (returnToMainMenuFont.getBounds(returnToMainMenuText).width / 2), 100, returnToMainMenuFont);

    }

    private Label createLabel(float x, float y, BitmapFont titleFont) {
        Label label = new Label(" ", new Label.LabelStyle(titleFont, Color.WHITE));
        label.setPosition(x, y);

        return label;
    }

    @Override
    public void dispose() {
        titleFont.dispose();
        scoreFont.dispose();
        returnToMainMenuFont.dispose();
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

        showLabels();

        stage.draw();
    }

    private void showLabels() {
        titleLabel.setText(titleText);

        int i = 0;
        for (ScoreBoardItem scoreBoardItem : snake3d.scoreBoard().getSavedScores()) {
            String timestamp = snake3d.scoreBoard().formatTimestamp(scoreBoardItem.getTimestamp());
            Integer score = scoreBoardItem.getScore();

            scoreLabels.get(i).setText(timestamp + " " + score + " points");

            i++;
        }

        returnToMainMenuLabel.setText(returnToMainMenuText);
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
