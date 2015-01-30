package org.avrj.snake3d.graphics;

import org.avrj.snake3d.simulation.GameSimulation;
import org.avrj.snake3d.objects.GameObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class GameRenderer {

    public Environment environment;
    public PerspectiveCamera cam;
    public CameraInputController camController;
    public ModelBatch modelBatch;
    public ModelBuilder modelBuilder;

    private final Stage stage;
    private final Label fpsLabel, scoreLabel;
    private final BitmapFont font;
    private final StringBuilder stringBuilder;

    public GameRenderer() {
        stage = new Stage();

        font = new BitmapFont();

        fpsLabel = new Label(" ", new Label.LabelStyle(font, Color.WHITE));
        scoreLabel = new Label(" ", new Label.LabelStyle(font, Color.WHITE));

        fpsLabel.setPosition(0, 0);
        scoreLabel.setPosition(0, stage.getHeight() - 20);

        stage.addActor(fpsLabel);
        stage.addActor(scoreLabel);

        stringBuilder = new StringBuilder();

        environment = new Environment();

        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

        modelBatch = new ModelBatch();

        cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    }

    public void render(GameSimulation simulation, float delta) {
        GL20 gl = Gdx.gl;

        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        gl.glEnable(GL20.GL_DEPTH_TEST);
        gl.glEnable(GL20.GL_CULL_FACE);

        setCamera();

        modelBatch.begin(cam);

        for (final GameObject instance : simulation.snakeSegments) {
            modelBatch.render(instance, environment);
        }

        for (final GameObject instance : simulation.planeSegments) {
            modelBatch.render(instance, environment);
        }

        modelBatch.render(simulation.appleInstance, environment);

        modelBatch.end();

        gl.glDisable(GL20.GL_CULL_FACE);
        gl.glDisable(GL20.GL_DEPTH_TEST);

        stringBuilder.setLength(0);
        stringBuilder.append(" FPS: ").append(Gdx.graphics.getFramesPerSecond());

        fpsLabel.setText(stringBuilder);

        stringBuilder.setLength(0);
        stringBuilder.append(" Score: ").append(simulation.score);

        scoreLabel.setText(stringBuilder);

        stage.draw();
    }

    private void setCamera() {
        cam.position.set(10f, 30f, 45f);
        cam.lookAt(0, 0, 0);
        cam.near = 1f;
        cam.far = 300f;
        cam.update();
    }

    public void dispose() {
        modelBatch.dispose();
        font.dispose();
        stage.dispose();
    }
}
