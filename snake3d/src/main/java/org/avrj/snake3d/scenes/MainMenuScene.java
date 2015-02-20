package org.avrj.snake3d.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
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

        startGameText = "Press [SPACE] to start";
        titleText = "Snake3D";
        fullScreenText = "[ENTER] to enter fullscreen";
        viewScoreBoardText = "[S] to view scoreboard";

        stage = new Stage();
        stringBuilder = new StringBuilder();

        loadFonts();
        createLabels();

        snake3d.getInputMultiplexer().addProcessor(new MainMenuKeyListener(snake3d));
    }

    private void createLabels() {
        titleLabel = new Label(" ", new Label.LabelStyle(titleFont, Color.WHITE));
        titleLabel.setPosition(stage.getWidth() / 2 - (titleFont.getBounds(titleText).width / 2), stage.getHeight() / 2);

        startGameLabel = new Label(" ", new Label.LabelStyle(startGameFont, Color.WHITE));
        startGameLabel.setPosition(stage.getWidth() / 2 - (startGameFont.getBounds(startGameText).width / 2), stage.getHeight() / 2 - 100);

        fullScreenLabel = new Label(" ", new Label.LabelStyle(fullScreenFont, Color.WHITE));
        fullScreenLabel.setPosition(stage.getWidth() / 2 - (fullScreenFont.getBounds(fullScreenText).width / 2), stage.getHeight() / 2 - 170);

        viewScoreBoardLabel = new Label(" ", new Label.LabelStyle(viewScoreBoardFont, Color.WHITE));
        viewScoreBoardLabel.setPosition(stage.getWidth() / 2 - (viewScoreBoardFont.getBounds(viewScoreBoardText).width / 2), 100);

        stage.addActor(titleLabel);
        stage.addActor(startGameLabel);
        stage.addActor(fullScreenLabel);
        stage.addActor(viewScoreBoardLabel);
    }

    @Override
    public void dispose() {
        titleFont.dispose();
        startGameFont.dispose();
        fullScreenFont.dispose();
        viewScoreBoardFont.dispose();
        stage.dispose();
    }

    private void loadFonts() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("assets/fonts/AgentOrange.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();

        parameter.size = 100;
        titleFont = generator.generateFont(parameter);

        parameter.size = 30;
        startGameFont = generator.generateFont(parameter);

        parameter.size = 20;
        fullScreenFont = generator.generateFont(parameter);

        parameter.size = 20;
        viewScoreBoardFont = generator.generateFont(parameter);

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
        showPlayLabel();
        showToggleFullscreenLabel();
        showViewScoreBoardLabel();

        stage.draw();
    }

    private void showTitleLabel() {
        stringBuilder.setLength(0);
        stringBuilder.append(titleText);

        titleLabel.setText(stringBuilder);
    }

    private void showPlayLabel() {
        stringBuilder.setLength(0);
        stringBuilder.append(startGameText);

        startGameLabel.setText(stringBuilder);
    }

    public void showToggleFullscreenLabel() {
        stringBuilder.setLength(0);
        stringBuilder.append(fullScreenText);

        fullScreenLabel.setText(stringBuilder);
    }

    public void showViewScoreBoardLabel() {
        stringBuilder.setLength(0);
        stringBuilder.append(viewScoreBoardText);

        viewScoreBoardLabel.setText(stringBuilder);
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
