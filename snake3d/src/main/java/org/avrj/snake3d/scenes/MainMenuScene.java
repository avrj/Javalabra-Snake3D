package org.avrj.snake3d.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import org.avrj.snake3d.Snake3D;
import org.avrj.snake3d.listeners.MainMenuKeyListener;

public class MainMenuScene extends Snake3DScene {

    private boolean isDone = false;
    private final Stage stage;
    private BitmapFont titleFont, startGameFont, fullScreenFont, viewScoreBoardFont;
    private Label titleLabel, startGameLabel, fullScreenLabel, viewScoreBoardLabel;
    private final StringBuilder stringBuilder;
    private String startGameText, titleText, fullScreenText, viewScoreBoardText;

    public MainMenuScene(Snake3D snake3d) {
        super(snake3d);

        stage = new Stage();
        stringBuilder = new StringBuilder();

        addLabels();

        snake3d.getInputMultiplexer().addProcessor(new MainMenuKeyListener(snake3d));
    }

    private void setLabelTexts() {
        startGameText = "Press [SPACE] to start";
        titleText = "Snake3D";
        fullScreenText = "[ENTER] to enter fullscreen";
        viewScoreBoardText = "[S] to view high scores";
    }

    private void addLabels() {
        setLabelTexts();
        createLabels();

        stage.addActor(titleLabel);
        stage.addActor(startGameLabel);
        stage.addActor(fullScreenLabel);
        stage.addActor(viewScoreBoardLabel);
    }

    private void createLabels() {
        titleFont = generateFont(100);
        titleLabel = createLabel(stage.getWidth() / 2 - (titleFont.getBounds(titleText).width / 2), stage.getHeight() / 2, titleFont);

        startGameFont = generateFont(30);
        startGameLabel = createLabel(stage.getWidth() / 2 - (startGameFont.getBounds(startGameText).width / 2), stage.getHeight() / 2 - 100, startGameFont);

        fullScreenFont = generateFont(20);
        fullScreenLabel = createLabel(stage.getWidth() / 2 - (fullScreenFont.getBounds(fullScreenText).width / 2), 100, fullScreenFont);

        viewScoreBoardFont = generateFont(20);
        viewScoreBoardLabel = createLabel(stage.getWidth() / 2 - (viewScoreBoardFont.getBounds(viewScoreBoardText).width / 2), stage.getHeight() / 2 - 170, viewScoreBoardFont);
    }

    private Label createLabel(float x, float y, BitmapFont titleFont) {
        Label label = new Label(" ", new Label.LabelStyle(titleFont, Color.WHITE));
        label.setPosition(x, y);

        return label;
    }

    @Override
    public void dispose() {
        titleFont.dispose();
        startGameFont.dispose();
        fullScreenFont.dispose();
        viewScoreBoardFont.dispose();
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
        startGameLabel.setText(startGameText);
        fullScreenLabel.setText(fullScreenText);
        viewScoreBoardLabel.setText(viewScoreBoardText);
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
