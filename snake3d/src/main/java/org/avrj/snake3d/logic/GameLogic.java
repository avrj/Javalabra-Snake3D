package org.avrj.snake3d.logic;

import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Game logic class
 */
public class GameLogic {

    private Vector2 applePosition;
    private final ArrayList<Vector2> snakeSegments;
    private final float snakeStep = 6f;
    private SnakeDirection currentDirection;
    private final ArrayList<Vector2> planeSegments;
    private boolean hasToGrow;
    private final int snakeStartSize = 3;

    /**
     * Initializes variables
     */
    public GameLogic() {
        hasToGrow = false;

        currentDirection = SnakeDirection.UP;

        snakeSegments = new ArrayList<>();
        planeSegments = new ArrayList<>();

        createSnakeSegments(snakeStartSize);

        createSurfaceSegments();

        applePosition = new Vector2(0, 0);

        moveAppleToRandomPosition();
    }

    private void createSnakeSegments(int snakeSize) {
        for(int i = 0; i < snakeSize; i++) {
            snakeSegments.add(0, new Vector2(23f, 23f - (6f * i)));
        }
    }
    
    private void createSurfaceSegments() {
        for (float x = -25f; x <= 25f; x += snakeStep) {
            for (float z = -25f; z <= 25f; z += snakeStep) {
                planeSegments.add(new Vector2(x, z));
            }
        }
    }

    /**
     * Moves the snake to SnakeDirection
     *
     * @param snakeDirection Direction of the snake
     */
    public void moveSnake(SnakeDirection snakeDirection) {
        if (!hasToGrow) {
            updateSnakeSegments();
        }

        if (currentDirection != snakeDirection.getOpposite()) {
            if (hasToGrow) {
                snakeSegments.add(0, new Vector2(snakeSegments.get(0).x, snakeSegments.get(0).y));
                hasToGrow = false;
            }

            snakeSegments.get(0).add(snakeDirection.getSnakeDirection());

            currentDirection = snakeDirection;
        }
    }

    /**
     * Method to grow the snake at next movement
     */
    public void growSnake() {
        hasToGrow = true;
    }

    /**
     * Moves the apple to a random position
     */
    public void moveAppleToRandomPosition() {
        Vector2 applePositionOld = applePosition;

        applePosition = planeSegments.get(new Random().nextInt(planeSegments.size()));

        while (applePosition == applePositionOld || snakeSegments.contains(applePosition)) {
            applePosition = planeSegments.get(new Random().nextInt(planeSegments.size()));
        }
    }

    private <T> boolean hasDuplicate(Iterable<T> all) {
        Set<T> set = new HashSet<>();

        for (T each : all) {
            if (!set.add(each)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if snake collides itself
     *
     * @return true if snake collides itself
     */
    public boolean checkSnakeSelfCollision() {
        return hasDuplicate(snakeSegments);
    }

    /**
     * Checks if snake collides apple
     *
     * @return true if snake collides apple
     */
    public boolean checkSnakeAppleCollision() {
        return snakeSegments.contains(applePosition);
    }

    /**
     * Checks if snake is out of game area
     *
     * @return true if snake is out of game area
     */
    public boolean checkSnakeOutOfGameAreaCollision() {
        boolean isOutOfGameArea = true;

        for (Vector2 planeSegment : planeSegments) {
            if (snakeSegments.get(0).equals(planeSegment)) {
                isOutOfGameArea = false;
            }
        }

        return isOutOfGameArea;
    }

    private void updateSnakeSegments() {
        for (int i = snakeSegments.size() - 1; i > 0; i--) {
            snakeSegments.get(i).set(snakeSegments.get(i - 1));
        }
    }

    public Vector2 getApplePosition() {
        return applePosition;
    }
    
    public ArrayList<Vector2> getSnakeSegments() {
        return snakeSegments;
    }
    
    public ArrayList<Vector2> getPlaneSegments() {
        return planeSegments;
    }

    public void setApplePosition(Vector2 applePosition) {
        this.applePosition = applePosition;
    }

}
