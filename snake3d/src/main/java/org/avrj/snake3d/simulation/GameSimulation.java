package org.avrj.snake3d.simulation;

import com.badlogic.gdx.Input;
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

/**
 * Helper class for the game logic
 *
 * @see Disposable
 */
public class GameSimulation implements Disposable {

    private final ArrayList<GameObject> snakeSegments;
    private final ArrayList<GameObject> planeSegments;

    private Model appleModel;
    private Model snakeModel;
    private Model planeModel;

    private GameObject appleInstance;
    private ModelBuilder modelBuilder;

    private SnakeDirection snakeDirection;

    private float timer = 0;

    private GameObject snakeInstance;
    private GameObject planeInstance;

    private final GameLogic gameLogic;

    private final Snake3D snake3d;

    private final double snakeSpeed = 0.5;

    private int score = 0;

    /**
     * Initializes variables
     *
     * @param snake3d the main game class
     */
    public GameSimulation(Snake3D snake3d) {
        snakeSegments = new ArrayList<>();
        planeSegments = new ArrayList<>();
        snakeDirection = SnakeDirection.UP;
        gameLogic = new GameLogic();
        this.snake3d = snake3d;

        populate();
    }

    public ArrayList<GameObject> getSnakeSegments() {
        return snakeSegments;
    }

    public ArrayList<GameObject> getPlaneSegments() {
        return planeSegments;
    }

    /**
     * Called when this class is not used anymore
     */
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
        appleInstance.transform.setToTranslation(new Vector3(gameLogic.getApplePosition().x, 3f, gameLogic.getApplePosition().y));

        snakeModel = modelBuilder.createBox(5f, 5f, 5f, new Material(ColorAttribute.createDiffuse(Color.GREEN)), Usage.Position | Usage.Normal);

        snakeInstance = new GameObject(snakeModel);
        snakeInstance.transform.setToTranslation(new Vector3(gameLogic.getSnakeSegments().get(0).x, 1f, gameLogic.getSnakeSegments().get(0).y));

        snakeSegments.add(snakeInstance);

        planeModel = modelBuilder.createBox(5f, 1f, 5f, new Material(ColorAttribute.createDiffuse(Color.BLUE)), Usage.Position | Usage.Normal);

        createPlane();

        moveAppleToRandomPosition();
    }

    private void createPlane() {
        for (Vector2 planePosition : gameLogic.getPlaneSegments()) {
            planeInstance = new GameObject(planeModel);
            planeInstance.transform.setToTranslation(new Vector3(planePosition.x, 0, planePosition.y));
            planeSegments.add(planeInstance);
        }
    }

    public int getScore() {
        return score;
    }

    public void increaseScore() {
        score++;
    }

    /**
     * Called when the screen is updated
     *
     * @param delta Deltatime of the game
     */
    public void update(float delta) {
        if (timer >= snakeSpeed) {
            timer -= snakeSpeed;

            moveSnake();
                        if (checkSnakeAppleCollision()) {
                increaseScore();
                growSnake();
                moveAppleToRandomPosition();
            }

            if (checkSnakeOutOfGameAreaCollision() || checkSnakeSelfCollision()) {
                snake3d.getInputMultiplexer().removeProcessor(1);
                snake3d.getScreen().setDone();
            }
        }

        timer += delta;
    }

    /**
     * Moves the snake and updates snake segments
     */
    public void moveSnake() {
        gameLogic.moveSnake(snakeDirection);

        updateSnakeSegments();
    }

    /**
     * Makes the snake grow
     */
    public void growSnake() {
        gameLogic.growSnake();
    }

    /**
     * Moves the apple to a random position
     *
     */
    public void moveAppleToRandomPosition() {
        gameLogic.moveAppleToRandomPosition();

        appleInstance.transform.setTranslation(gameLogic.getApplePosition().x, 3f, gameLogic.getApplePosition().y);
    }

    /**
     * Checks if snake collides itself
     *
     * @return true if collides
     */
    public boolean checkSnakeSelfCollision() {
        return gameLogic.checkSnakeSelfCollision();
    }

    /**
     * Checks if snake collides apple
     *
     * @return true if collides
     */
    public boolean checkSnakeAppleCollision() {
        return gameLogic.checkSnakeAppleCollision();
    }

    /**
     * Checks if snake is out of game area
     *
     * @return true if is out
     */
    public boolean checkSnakeOutOfGameAreaCollision() {
        return gameLogic.checkSnakeOutOfGameAreaCollision();
    }

    private void updateSnakeSegments() {
        snakeSegments.clear();

        for (Vector2 snakeSegment : gameLogic.getSnakeSegments()) {
            snakeInstance = new GameObject(snakeModel);
            snakeInstance.transform.setToTranslation(new Vector3(snakeSegment.x, 3f, snakeSegment.y));
            snakeSegments.add(snakeInstance);
        }
    }

    public SnakeDirection getSnakeDirection() {
        return snakeDirection;
    }

    public void setSnakeDirection(SnakeDirection snakeDirection) {

        if ((snakeDirection == SnakeDirection.UP && !getSnakeDirection().equals(SnakeDirection.DOWN))
                || (snakeDirection == SnakeDirection.DOWN && !getSnakeDirection().equals(SnakeDirection.UP))
                || (snakeDirection == SnakeDirection.LEFT && !getSnakeDirection().equals(SnakeDirection.RIGHT))
                || (snakeDirection == SnakeDirection.RIGHT && !getSnakeDirection().equals(SnakeDirection.LEFT))) {
            this.snakeDirection = snakeDirection;
        }
    }

    public GameObject getAppleInstance() {
        return appleInstance;
    }
}
