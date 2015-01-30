package org.avrj.snake3d.logic;

import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GameLogic {

    public Vector2 applePosition;
    public ArrayList<Vector2> snakeSegments;
    public float snakeStep = 6f;
    public SnakeDirection currentDirection;
    public ArrayList<Vector2> planeSegments;
    private boolean hasToGrow;

    public GameLogic() {
        hasToGrow = false;

        currentDirection = SnakeDirection.UP;

        snakeSegments = new ArrayList<>();
        planeSegments = new ArrayList<>();

        snakeSegments.add(new Vector2(23f, 23f));

        createSurfaceSegments();

        applePosition = new Vector2(0, 0);

        deployApple();
    }

    private void createSurfaceSegments() {
        for (float x = -25f; x <= 25f; x += snakeStep) {
            for (float z = -25f; z <= 25f; z += snakeStep) {
                planeSegments.add(new Vector2(x, z));
            }
        }
    }

    public void moveSnake(SnakeDirection snakeDirection) {
        if (!hasToGrow) {
            updateSnakeSegments();
        }

        switch (snakeDirection) {
            case LEFT:
                moveSnakeLeft();
                break;
            case RIGHT:
                moveSnakeRight();
                break;
            case UP:
                moveSnakeUp();
                break;
            case DOWN:
                moveSnakeDown();
                break;
        }
    }

    private void moveSnakeLeft() {
        if (currentDirection != SnakeDirection.RIGHT) {
            if (hasToGrow) {
                snakeSegments.add(0, new Vector2(snakeSegments.get(0).x - snakeStep, snakeSegments.get(0).y));

                hasToGrow = false;
            } else {
                snakeSegments.set(0, new Vector2(snakeSegments.get(0).x - snakeStep, snakeSegments.get(0).y));
            }

            currentDirection = SnakeDirection.LEFT;
        }
    }

    private void moveSnakeRight() {
        if (currentDirection != SnakeDirection.LEFT) {
            if (hasToGrow) {
                snakeSegments.add(0, new Vector2(snakeSegments.get(0).x + snakeStep, snakeSegments.get(0).y));

                hasToGrow = false;
            } else {
                snakeSegments.set(0, new Vector2(snakeSegments.get(0).x + snakeStep, snakeSegments.get(0).y));
            }

            currentDirection = SnakeDirection.RIGHT;
        }
    }

    private void moveSnakeUp() {
        if (currentDirection != SnakeDirection.DOWN) {
            if (hasToGrow) {
                snakeSegments.add(0, new Vector2(snakeSegments.get(0).x, snakeSegments.get(0).y - snakeStep));

                hasToGrow = false;
            } else {
                snakeSegments.set(0, new Vector2(snakeSegments.get(0).x, snakeSegments.get(0).y - snakeStep));
            }

            currentDirection = SnakeDirection.UP;
        }
    }

    private void moveSnakeDown() {
        if (currentDirection != SnakeDirection.UP) {
            if (hasToGrow) {
                snakeSegments.add(0, new Vector2(snakeSegments.get(0).x, snakeSegments.get(0).y + snakeStep));

                hasToGrow = false;
            } else {
                snakeSegments.set(0, new Vector2(snakeSegments.get(0).x, snakeSegments.get(0).y + snakeStep));
            }

            currentDirection = SnakeDirection.DOWN;
        }
    }

    public void growSnake() {
        hasToGrow = true;
    }

    public void deployApple() {
        Vector2 applePositionOld = applePosition;

        applePosition = planeSegments.get(new Random().nextInt(planeSegments.size()));

        while (applePosition == applePositionOld || snakeSegments.contains(applePosition)) {
            applePosition = planeSegments.get(new Random().nextInt(planeSegments.size()));
        }
    }

    public static <T> boolean hasDuplicate(Iterable<T> all) {
        Set<T> set = new HashSet<>();

        for (T each : all) {
            if (!set.add(each)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkSnakeSelfCollision() {
        return hasDuplicate(snakeSegments);
    }

    public boolean checkSnakeAppleCollision() {
        return snakeSegments.contains(applePosition);
    }

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
            snakeSegments.set(i, snakeSegments.get(i - 1));
        }
    }

}
