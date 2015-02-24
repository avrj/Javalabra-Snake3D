package org.avrj.snake3d.logic;

import org.avrj.snake3d.helpers.SnakeDirection;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * The snake object (consists of snake segments)
 *
 * @author araiha
 */
public class Snake {

    private SnakeDirection currentDirection;
    private final ArrayList<Vector2> snake;
    private Vector2 lastTailPosition;

    /**
     * Initializes the snake and creates the first segment
     */
    public Snake() {
        currentDirection = SnakeDirection.UP;
        snake = new ArrayList<>();

        snake.add(0, new Vector2(23f, 23f));
    }

    public ArrayList<Vector2> getVectors() {
        return snake;
    }

    public Vector2 getHead() {
        return snake.get(0);
    }

    /**
     * A method to add one segment to the tail of the snake
     */
    public void grow() {
        snake.add(lastTailPosition);
    }

    /**
     * A method to move the snake to current direction
     */
    public void move() {
        moveTo(currentDirection);
    }

    /**
     * A method to move the snake to desired direction
     *
     * @param snakeDirection The direction of the snake
     */
    public void moveTo(SnakeDirection snakeDirection) {
        if (currentDirection == snakeDirection.getOpposite()) {
            return;
        }

        lastTailPosition = snake.get(snake.size() - 1).cpy();

        for (int i = snake.size() - 1; i > 0; i--) {
            snake.get(i).set(snake.get(i - 1));
        }

        snake.get(0).add(snakeDirection.getSnakeDirection());

        currentDirection = snakeDirection;
    }

    public void setDirection(SnakeDirection snakeDirection) {
        currentDirection = snakeDirection;
    }

    public SnakeDirection getDirection() {
        return currentDirection;
    }

    /**
     * A method to check if the snake is colliding itself
     *
     * @return true if snake is colliding itself
     */
    public boolean selfCollision() {
        return hasDuplicate(snake);
    }

    /**
     * A method to check if the snake is colliding an apple
     *
     * @param vector The apple object
     * @return true if colliding
     */
    public boolean collides(Vector2 vector) {
        return snake.contains(vector);
    }

    /**
     * A method to check if snake is out of game area
     *
     * @param gameArea List of game area (GameSurface) blocks
     * @return true if out
     */
    public boolean isOutOfGameArea(ArrayList<Vector2> gameArea) {
        boolean isOutOfGameArea = true;

        for (Vector2 planeSegment : gameArea) {
            if (snake.get(0).equals(planeSegment)) {
                isOutOfGameArea = false;
            }
        }

        return isOutOfGameArea;
    }

    public Vector2 getLastTailPosition() {
        return lastTailPosition;
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
}
