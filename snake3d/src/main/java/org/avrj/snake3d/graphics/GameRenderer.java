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
import com.badlogic.gdx.graphics.g3d.environment.DirectionalShadowLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.DepthShaderProvider;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * Class to render graphics in GameLoopScene
 *
 * @see GameLoopScene
 */
public class GameRenderer {

    private final Environment environment;
    private final PerspectiveCamera cam;
    private CameraInputController camController;
    private final ModelBatch modelBatch;
    private ModelBuilder modelBuilder;

    private final Stage stage;
    private final Label fpsLabel, scoreLabel;
    private final BitmapFont font;
    private final StringBuilder stringBuilder;
    private final ModelBatch shadowBatch;
    private final DirectionalShadowLight shadowLight;

    /**
     * Initialize the render class in GameLoopScene
     */
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

        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, .4f, .4f, .4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

        environment.add((shadowLight = new DirectionalShadowLight(1024, 1024, 30f, 30f, 1f, 100f)).set(0.8f, 0.8f, 0.8f, -1f, -.8f,
                -.2f));

        environment.shadowMap = shadowLight;

        modelBatch = new ModelBatch();

        cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        shadowBatch = new ModelBatch(new DepthShaderProvider());
    }

    /**
     * Called when the class should render itself.
     *
     * @param simulation The simulation helper class
     * @param delta Deltatime value of the game
     */
    public void render(GameSimulation simulation, float delta) {
        GL20 gl = Gdx.gl;

        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        gl.glEnable(GL20.GL_DEPTH_TEST);
        gl.glEnable(GL20.GL_CULL_FACE);

        setCamera();

        renderShadows(simulation);

        Gdx.gl.glClearColor(0, 0, 0, 1);

        renderObjects(simulation);

        gl.glDisable(GL20.GL_CULL_FACE);
        gl.glDisable(GL20.GL_DEPTH_TEST);

        showFPS();
        showScore(simulation);

        stage.draw();
    }

    private void showFPS() {
        stringBuilder.setLength(0);
        stringBuilder.append(" FPS: ").append(Gdx.graphics.getFramesPerSecond());

        fpsLabel.setText(stringBuilder);
    }

    private void showScore(GameSimulation simulation) {
        stringBuilder.setLength(0);
        stringBuilder.append(" Score: ").append(simulation.getScore());

        scoreLabel.setText(stringBuilder);
    }

    private void renderObjects(GameSimulation simulation) {
        modelBatch.begin(cam);

        for (final GameObject instance : simulation.getSnakeSegments()) {
            modelBatch.render(instance, environment);
        }

        for (final GameObject instance : simulation.getPlaneSegments()) {
            modelBatch.render(instance, environment);
        }

        modelBatch.render(simulation.getAppleInstance(), environment);

        modelBatch.end();
    }

    private void renderShadows(GameSimulation simulation) {
        shadowLight.begin(Vector3.Zero, cam.direction);
        shadowBatch.begin(shadowLight.getCamera());

        for (final GameObject instance : simulation.getSnakeSegments()) {
            shadowBatch.render(instance);
        }

        for (final GameObject instance : simulation.getPlaneSegments()) {
            shadowBatch.render(instance);
        }
        shadowBatch.render(simulation.getAppleInstance());

        shadowBatch.end();
        shadowLight.end();
    }

    private void setCamera() {
        cam.position.set(10f, 30f, 45f);
        cam.lookAt(0, 0, 0);
        cam.near = 1f;
        cam.far = 300f;
        cam.update();
    }

    /**
     * Called when this class is not used anymore
     */
    public void dispose() {
        modelBatch.dispose();
        font.dispose();
        stage.dispose();
    }
}
