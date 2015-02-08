package org.avrj.snake3d.logic;

import com.badlogic.gdx.math.Vector2;

/**
 * Defines the direction of the snake
 */
public enum SnakeDirection {

    LEFT(new Vector2(-6f, 0)),
    RIGHT(new Vector2(6f, 0)),
    UP(new Vector2(0, -6f)),
    DOWN(new Vector2(0, 6f));

    private final Vector2 snakeDirection;

    private SnakeDirection(Vector2 snakeDirection) {
        this.snakeDirection = snakeDirection;
    }

    public Vector2 getSnakeDirection() {
        return snakeDirection;
    }

    public SnakeDirection getOpposite() {
        switch (this) {
            case LEFT:
                return RIGHT;
            case RIGHT:
                return LEFT;
            case UP:
                return DOWN;
            case DOWN:
                return UP;
        }

        return null;
    }
}
