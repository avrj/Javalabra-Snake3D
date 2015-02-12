package org.avrj.snake3d.logic;

import org.avrj.snake3d.helpers.SnakeDirection;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author araiha
 */
public class SnakeTest {

    private Snake snake;

    public SnakeTest() {
        snake = new Snake();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void snakeSizeIsOneAtStart() {
        assertEquals(snake.getVectors().size(), 1);
    }

    @Test
    public void snakeDirectionIsUpAtStart() {
        assertEquals(snake.getDirection(), SnakeDirection.UP);
    }

    @Test
    public void snakeDirectionIsSetRight() {
        snake.setDirection(SnakeDirection.DOWN);

        assertEquals(snake.getDirection(), SnakeDirection.DOWN);
    }

    @Test
    public void snakeContainsRightVectorsAtStart() {
        ArrayList<Vector2> vectors = new ArrayList<>();

        vectors.add(0, new Vector2(23f, 23f));

        assertEquals(snake.getVectors(), vectors);
    }

    @Test
    public void getHeadReturnsHead() {
        assertEquals(snake.getHead(), new Vector2(23f, 23f));
    }

    @Test
    public void snakeIsGrowing() {
        snake.grow();

        assertEquals(snake.getVectors().size(), 2);
    }

    @Test
    public void snakeCantMoveToCurrentOppositeDirection() {
        snake.setDirection(SnakeDirection.UP);

        ArrayList<Vector2> snakePositionsOld = snake.getVectors();

        snake.move();

        assertEquals(snakePositionsOld, snake.getVectors());
    }

    @Test
    public void snakeCantMoveToDesiredOppositeDirection() {
        snake.setDirection(SnakeDirection.UP);

        ArrayList<Vector2> snakePositionsOld = snake.getVectors();

        snake.moveTo(SnakeDirection.DOWN);

        assertEquals(snakePositionsOld, snake.getVectors());
    }

    @Test
    public void snakeIsMovingToCurrentDirection() {
        Vector2 snakeHeadPositionOld = snake.getHead();

        snake.setDirection(SnakeDirection.UP);

        snake.move();

        assertEquals(snake.getHead(), snakeHeadPositionOld.add(SnakeDirection.UP.getSnakeDirection()));
    }

    @Test
    public void snakeIsMovingToDesiredDirection() {
        Vector2 snakeHeadPositionOld = snake.getHead();

        snake.moveTo(SnakeDirection.UP);

        assertEquals(snake.getHead(), snakeHeadPositionOld.add(SnakeDirection.UP.getSnakeDirection()));
    }

    @Test
    public void snakeSelfCollisionIsDetected() {
        assertFalse(snake.selfCollision());
        snake.moveTo(SnakeDirection.UP);
        snake.grow();
        assertFalse(snake.selfCollision());
        snake.moveTo(SnakeDirection.UP);
        snake.grow();
        snake.moveTo(SnakeDirection.UP);
        snake.grow();
        assertFalse(snake.selfCollision());
        snake.moveTo(SnakeDirection.UP);
        snake.grow();
        assertFalse(snake.selfCollision());
        snake.moveTo(SnakeDirection.LEFT);
        assertFalse(snake.selfCollision());
        snake.moveTo(SnakeDirection.DOWN);
        assertFalse(snake.selfCollision());
        snake.moveTo(SnakeDirection.RIGHT);
        assertTrue(snake.selfCollision());
    }

    @Test
    public void snakeCollisionIsDetected() {
        assertFalse(snake.collides(new Vector2(0f, 0f)));
        assertTrue(snake.collides(new Vector2(23f, 23f)));
    }

    @Test
    public void snakeOutOfGameAreaIsDetected() {
        ArrayList<Vector2> gameArea = new ArrayList<>();

        gameArea.add(new Vector2(0f, 0f));

        assertTrue(snake.isOutOfGameArea(gameArea));

        gameArea = new ArrayList<>();

        gameArea.add(new Vector2(23f, 23f));

        assertFalse(snake.isOutOfGameArea(gameArea));
    }
}
