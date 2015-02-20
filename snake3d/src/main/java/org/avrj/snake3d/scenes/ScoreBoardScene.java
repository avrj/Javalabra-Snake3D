package org.avrj.snake3d.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import java.util.ArrayList;
import java.util.Map;
import org.avrj.snake3d.Snake3D;
import org.avrj.snake3d.listeners.MainMenuKeyListener;
import org.avrj.snake3d.listeners.ScoreBoardKeyListener;

public class ScoreBoardScene extends Snake3DScene {

    private boolean isDone = false;
    private final Stage stage;
    private BitmapFont titleFont, scoreFont, fullScreenFont;
    private Label titleLabel, fullScreenLabel;
    private ArrayList<Label> scoreLabels;
    private final StringBuilder stringBuilder;
    private String titleText, fullScreenText;
    private ArrayList<String> scoreTexts;

    public ScoreBoardScene(Snake3D snake3d) {
        super(snake3d);

        titleText = "Latest scores";
        fullScreenText = "[ESC] to return to main menu";

        stage = new Stage();
        stringBuilder = new StringBuilder();
        scoreLabels = new ArrayList<>();
        scoreTexts = new ArrayList<>();

        loadFonts();
        createLabels();

        snake3d.getInputMultiplexer().addProcessor(new ScoreBoardKeyListener(snake3d));
    }

    private void createLabels() {
        titleLabel = new Label(" ", new Label.LabelStyle(titleFont, Color.WHITE));
        titleLabel.setPosition(stage.getWidth() / 2 - (titleFont.getBounds(titleText).width / 2), stage.getHeight() - 100);
        
        int y = 0;
        for (Map.Entry<String, Integer> entry : snake3d.scoreBoard().getSavedScores().entrySet()) {
            String timestamp = entry.getKey();
            Integer score = entry.getValue();

            Label scoreLabel = new Label(" ", new Label.LabelStyle(scoreFont, Color.WHITE));
            scoreLabel.setPosition(stage.getWidth() / 2 - (scoreFont.getBounds(timestamp + " " + score + " points").width / 2), stage.getHeight() - (160 + (35*y)));
            scoreLabels.add(scoreLabel);
            
            y++;
        }

        fullScreenLabel = new Label(" ", new Label.LabelStyle(fullScreenFont, Color.WHITE));
        fullScreenLabel.setPosition(stage.getWidth() / 2 - (fullScreenFont.getBounds(fullScreenText).width / 2), stage.getHeight() / 2 - 170);

        stage.addActor(titleLabel);

        for (Label scoreLabel : scoreLabels) {
            stage.addActor(scoreLabel);
        }

        stage.addActor(fullScreenLabel);

    }

    @Override
    public void dispose() {
        titleFont.dispose();
        scoreFont.dispose();
        fullScreenFont.dispose();
        stage.dispose();
    }

    private void loadFonts() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("assets/fonts/AgentOrange.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();

        parameter.size = 50;
        titleFont = generator.generateFont(parameter);

        parameter.size = 20;
        scoreFont = generator.generateFont(parameter);

        parameter.size = 20;
        fullScreenFont = generator.generateFont(parameter);

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
        showScoreLabels();
        showToggleFullscreenLabel();

        stage.draw();
    }

    private void showTitleLabel() {
        stringBuilder.setLength(0);
        stringBuilder.append(titleText);

        titleLabel.setText(stringBuilder);
    }

    private void showScoreLabels() {
        int i = 0;
        for (Map.Entry<String, Integer> entry : snake3d.scoreBoard().getSavedScores().entrySet()) {
            String timestamp = entry.getKey();
            Integer score = entry.getValue();

            stringBuilder.setLength(0);
            stringBuilder.append(timestamp).append(" ").append(score).append(" points");

            scoreLabels.get(i).setText(stringBuilder);

            i++;
        }
    }

    public void showToggleFullscreenLabel() {
        stringBuilder.setLength(0);
        stringBuilder.append(fullScreenText);

        fullScreenLabel.setText(stringBuilder);
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
