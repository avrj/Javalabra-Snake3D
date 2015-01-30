package org.avrj.snake3d.simulation;

import org.avrj.snake3d.objects.GameObject;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;
import java.util.ArrayList;
import org.avrj.snake3d.Snake3D;
import org.avrj.snake3d.logic.GameLogic;
import org.avrj.snake3d.logic.SnakeDirection;

public class GameSimulation implements Disposable {

    public ArrayList<GameObject> snakeSegments;
    public ArrayList<GameObject> planeSegments;

    public Model appleModel;
    public Model snakeModel;
    public Model planeModel;

    public GameObject appleInstance;
    public ModelBuilder modelBuilder;

    public SnakeDirection snakeDirection;

    private float timer = 0;

    private GameObject snakeInstance;
    private GameObject planeInstance;

    private final GameLogic gameLogic;

    private Snake3D snake3d;

    private double snakeSpeed = 0.5;

    public int score = 0;

    public GameSimulation(Snake3D snake3d) {
        snakeSegments = new ArrayList<>();
        planeSegments = new ArrayList<>();
        snakeDirection = SnakeDirection.UP;
        gameLogic = new GameLogic();
        this.snake3d = snake3d;

        populate();
    }

    @Override
    public void dispose() {
        appleModel.dispose();
        snakeModel.dispose();
        planeModel.dispose();
    }

    private void populate() {
        modelBuilder = new ModelBuilder();

        appleModel = modelBuilder.createSphere(4f, 4f, 4f, 20, 20, new Material(ColorAttribute.createDiffuse(Color.RED)), Usage.Position | Usage.Normal);

        appleInstance = new GameObject(appleModel);
        appleInstance.transform.setToTranslation(new Vector3(gameLogic.applePosition.x, 3f, gameLogic.applePosition.y));

        snakeModel = modelBuilder.createBox(5f, 5f, 5f, new Material(ColorAttribute.createDiffuse(Color.GREEN)), Usage.Position | Usage.Normal);

        snakeInstance = new GameObject(snakeModel);
        snakeInstance.transform.setToTranslation(new Vector3(gameLogic.snakeSegments.get(0).x, 1f, gameLogic.snakeSegments.get(0).y));

        snakeSegments.add(snakeInstance);

        planeModel = modelBuilder.createBox(5f, 1f, 5f, new Material(ColorAttribute.createDiffuse(Color.BLUE)), Usage.Position | Usage.Normal);

        createPlane();

        deployApple();
    }

    public void createPlane() {
        for (Vector2 planePosition : gameLogic.planeSegments) {
            planeInstance = new GameObject(planeModel);
            planeInstance.transform.setToTranslation(new Vector3(planePosition.x, 0, planePosition.y));
            planeSegments.add(planeInstance);
        }
    }

    public void update(float delta) {
        if (checkSnakeAppleCollision()) {
            score++;
            System.out.println(score);
            growSnake();
            deployApple();
        }

        if (checkSnakeOutOfGameAreaCollision()) {
            snake3d.multiplexer.removeProcessor(1);
            snake3d.getScreen().setDone();
        }

        if (timer >= snakeSpeed) {
            timer -= snakeSpeed;

            moveSnake();
        }

        timer += delta;
    }

    public void moveSnake() {
        gameLogic.moveSnake(snakeDirection);

        updateSnakeSegments();
    }

    public void growSnake() {
        gameLogic.growSnake();
    }

    public void deployApple() {
        gameLogic.deployApple();

        appleInstance.transform.setTranslation(gameLogic.applePosition.x, 3f, gameLogic.applePosition.y);
    }

    public boolean checkSnakeSelfCollision() {
        return gameLogic.checkSnakeSelfCollision();
    }

    public boolean checkSnakeAppleCollision() {
        return gameLogic.checkSnakeAppleCollision();
    }

    public boolean checkSnakeOutOfGameAreaCollision() {
        return gameLogic.checkSnakeOutOfGameAreaCollision();
    }

    private void updateSnakeSegments() {
        snakeSegments.clear();

        for (Vector2 snakeSegment : gameLogic.snakeSegments) {
            snakeInstance = new GameObject(snakeModel);
            snakeInstance.transform.setToTranslation(new Vector3(snakeSegment.x, 3f, snakeSegment.y));
            snakeSegments.add(snakeInstance);
        }
    }
}
