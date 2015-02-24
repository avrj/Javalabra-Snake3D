package org.avrj.snake3d.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalShadowLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.DepthShaderProvider;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import java.util.ArrayList;
import org.avrj.snake3d.Snake3D;
import org.avrj.snake3d.helpers.GameState;
import org.avrj.snake3d.logic.Apple;
import org.avrj.snake3d.logic.Camera;
import org.avrj.snake3d.logic.GameSurface;
import org.avrj.snake3d.logic.Snake;

public class GameLoopScene extends Snake3DScene {

    private final ArrayList<ModelInstance> snakeSegments;
    private final ArrayList<ModelInstance> planeSegments;

    private Model appleModel;
    private Model snakeModel;
    private Model planeModel;

    private ModelBuilder modelBuilder;
    private CameraInputController camController;
    private ModelInstance snakeInstance;
    private ModelInstance planeInstance;
    private ModelInstance appleInstance;

    private final ModelBatch modelBatch;
    private final ModelBatch shadowBatch;
    private final Environment environment;
    private final PerspectiveCamera cam;
    private final Stage stage;
    private final Label fpsLabel, scoreLabel;
    private final BitmapFont font;
    private final StringBuilder stringBuilder;
    private final DirectionalShadowLight shadowLight;

    private float timer = 0f;
    private float snakeSpeed = 0.45f;

    private boolean isDone = false;

    private Apple apple;
    private Snake snake;
    private Camera camera;
    private GameSurface gameSurface;

    public GameLoopScene(Snake3D snake3d) {
        super(snake3d);

        snake3d.scoreBoard().clearScore();

        apple = new Apple();
        snake = new Snake();
        camera = new Camera();
        gameSurface = new GameSurface();

        apple.moveAppleToRandomPosition(gameSurface.getVectors(), snake.getVectors());

        snake3d.setGameState(GameState.Running);

        snakeSegments = new ArrayList<>();
        planeSegments = new ArrayList<>();

        createModels();

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
        cam.near = 1f;
        cam.far = 300f;
        shadowBatch = new ModelBatch(new DepthShaderProvider());
    }

    public Apple apple() {
        return apple;
    }

    public Snake snake() {
        return snake;
    }

    public Camera camera() {
        return camera;
    }

    public GameSurface gameSurface() {
        return gameSurface;
    }

    private void createModels() {
        modelBuilder = new ModelBuilder();

        appleModel = modelBuilder.createSphere(4f, 4f, 4f, 20, 20, new Material(ColorAttribute.createDiffuse(Color.RED)), Usage.Position | Usage.Normal);

        appleInstance = new ModelInstance(appleModel);
        appleInstance.transform.setToTranslation(new Vector3(apple().getPosition().x, 3f, apple().getPosition().y));

        snakeModel = modelBuilder.createBox(5f, 5f, 5f, new Material(ColorAttribute.createDiffuse(Color.GREEN)), Usage.Position | Usage.Normal);

        snakeInstance = new ModelInstance(snakeModel);
        snakeInstance.transform.setToTranslation(new Vector3(snake().getVectors().get(0).x, 1f, snake().getVectors().get(0).y));

        snakeSegments.add(snakeInstance);

        planeModel = modelBuilder.createBox(5f, 1f, 5f, new Material(ColorAttribute.createDiffuse(Color.BLUE)), Usage.Position | Usage.Normal);

        createPlane();

        updateAppleInstancePosition();
    }

    private void createPlane() {
        for (Vector2 planePosition : gameSurface().getVectors()) {
            planeInstance = new ModelInstance(planeModel);
            planeInstance.transform.setToTranslation(new Vector3(planePosition.x, 0, planePosition.y));
            planeSegments.add(planeInstance);
        }
    }

    @Override
    public void dispose() {
        appleModel.dispose();
        snakeModel.dispose();
        planeModel.dispose();
        modelBatch.dispose();
        font.dispose();
        stage.dispose();
    }

    public void updateAppleInstancePosition() {
        appleInstance.transform.setTranslation(apple().getPosition().x, 3f, apple().getPosition().y);
    }

    private void updateSnakeSegmentInstances() {
        snakeSegments.clear();

        for (Vector2 snakeSegment : snake().getVectors()) {
            snakeInstance = new ModelInstance(snakeModel);
            snakeInstance.transform.setToTranslation(new Vector3(snakeSegment.x, 3f, snakeSegment.y));
            snakeSegments.add(snakeInstance);
        }
    }

    @Override
    public void update(float delta) {
        if (snake3d.getGameState().equals(GameState.Paused)) {
            return;
        }

        if (timer >= snakeSpeed) {
            timer -= snakeSpeed;

            snake().move();
            updateSnakeSegmentInstances();

            if (snake().collides(apple().getPosition())) {
                snake3d.scoreBoard().increaseScore();
                snake().grow();
                apple().moveAppleToRandomPosition(gameSurface().getVectors(), snake().getVectors());

                updateAppleInstancePosition();
            }

            if (snake().isOutOfGameArea(gameSurface().getVectors()) || snake().selfCollision()) {
                isDone = true;
            }
        }

        timer += delta;
    }

    @Override
    public void draw(float delta) {
        GL20 gl = Gdx.gl;

        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        gl.glEnable(GL20.GL_DEPTH_TEST);
        gl.glEnable(GL20.GL_CULL_FACE);

        updateCameraRotation();

        renderShadows();

        Gdx.gl.glClearColor(0, 0, 0, 1);

        renderObjects();

        gl.glDisable(GL20.GL_CULL_FACE);
        gl.glDisable(GL20.GL_DEPTH_TEST);

        showFPS();
        showScore();

        stage.draw();
    }

    @Override
    public boolean isDone() {
        return isDone;
    }

    private void showFPS() {
        stringBuilder.setLength(0);
        stringBuilder.append(" FPS: ").append(Gdx.graphics.getFramesPerSecond());

        fpsLabel.setText(stringBuilder);
    }

    private void showScore() {
        stringBuilder.setLength(0);
        stringBuilder.append(" Score: ").append(snake3d.scoreBoard().getScore());

        scoreLabel.setText(stringBuilder);
    }

    private void renderObjects() {
        modelBatch.begin(cam);

        for (final ModelInstance instance : snakeSegments()) {
            modelBatch.render(instance, environment);
        }

        for (final ModelInstance instance : getPlaneSegments()) {
            modelBatch.render(instance, environment);
        }

        modelBatch.render(appleInstance, environment);

        modelBatch.end();
    }

    private void renderShadows() {
        shadowLight.begin(Vector3.Zero, cam.direction);
        shadowBatch.begin(shadowLight.getCamera());

        for (final ModelInstance instance : snakeSegments()) {
            shadowBatch.render(instance);
        }

        for (final ModelInstance instance : getPlaneSegments()) {
            shadowBatch.render(instance);
        }
        shadowBatch.render(appleInstance);

        shadowBatch.end();
        shadowLight.end();
    }

    private void updateCameraRotation() {
        Vector3 snakeHeadPosition = new Vector3();

        snakeSegments.get(0).transform.getTranslation(snakeHeadPosition);

        rotateCameraAround(snakeHeadPosition, camera().getAngle());
    }

    private void rotateCameraAround(Vector3 position, float angle) {
        cam.position.set(30f, 20f, 0).rotate(Vector3.Y, angle).add(position);
        cam.up.set(Vector3.Y);
        cam.lookAt(position);
        cam.update();
    }

    @Override
    public void setDone() {
        isDone = true;
    }

    public ArrayList<ModelInstance> snakeSegments() {
        return snakeSegments;
    }

    public ArrayList<ModelInstance> getPlaneSegments() {
        return planeSegments;
    }
}
