package org.avrj.snake3d.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import org.avrj.snake3d.Snake3D;
import org.avrj.snake3d.listeners.MainMenuKeyListener;

public class MainMenuScene extends Snake3DScene {

    public boolean isDone = false;
    private Stage stage;
    private BitmapFont font;
    private Label label;
    private StringBuilder stringBuilder;

    public MainMenuScene(Snake3D snake3d) {
        super(snake3d);

        stage = new Stage();
        font = new BitmapFont();
        label = new Label(" ", new Label.LabelStyle(font, Color.WHITE));
        stage.addActor(label);
        stringBuilder = new StringBuilder();

        snake3d.multiplexer.addProcessor(new MainMenuKeyListener(snake3d, this));
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void draw(float delta) {
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        stringBuilder.setLength(0);
        stringBuilder.append("Start game");
        label.setText(stringBuilder);
        stage.draw();
    }

    @Override
    public boolean isDone() {
        return isDone;
    }

}
