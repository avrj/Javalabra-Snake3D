package org.avrj.snake3d.logic;

import com.badlogic.gdx.math.Vector2;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameLogicTest {

    private final GameLogic gameLogic;

    public GameLogicTest() {
        gameLogic = new GameLogic();
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
    public void isSnakeSizeRightAtStart() {
        assertEquals(3, gameLogic.getSnakeSegments().size());
    }

    @Test
    public void isSnakeMovingLeft() {
        gameLogic.moveSnake(SnakeDirection.LEFT);

        assertEquals(17f, gameLogic.getSnakeSegments().get(0).x, 0.0f);
        assertEquals(11f, gameLogic.getSnakeSegments().get(0).y, 0.0f);
    }

    @Test
    public void isSnakeMovingRight() {
        gameLogic.moveSnake(SnakeDirection.RIGHT);

        assertEquals(29f, gameLogic.getSnakeSegments().get(0).x, 0.0f);
        assertEquals(11f, gameLogic.getSnakeSegments().get(0).y, 0.0f);
    }

    @Test
    public void isSnakeMovingUp() {
        gameLogic.moveSnake(SnakeDirection.UP);

        assertEquals(23f, gameLogic.getSnakeSegments().get(0).x, 0.0f);
        assertEquals(5f, gameLogic.getSnakeSegments().get(0).y, 0.0f);
    }

    @Test
    public void isSnakeMovingDown() {
        gameLogic.moveSnake(SnakeDirection.DOWN);

        assertEquals(23f, gameLogic.getSnakeSegments().get(0).x, 0.0f);
        assertEquals(11f, gameLogic.getSnakeSegments().get(0).y, 0.0f);
    }

    @Test
    public void isSnakeGrowing() {
        gameLogic.growSnake();
        gameLogic.moveSnake(SnakeDirection.UP);

        assertEquals(4, gameLogic.getSnakeSegments().size());
    }

    @Test
    public void isSnakeRealSelfCollisionDetected() {
        gameLogic.growSnake();
        gameLogic.moveSnake(SnakeDirection.UP);
        gameLogic.growSnake();
        gameLogic.moveSnake(SnakeDirection.UP);
        gameLogic.growSnake();
        gameLogic.moveSnake(SnakeDirection.UP);
        gameLogic.growSnake();

        gameLogic.moveSnake(SnakeDirection.LEFT);
        gameLogic.moveSnake(SnakeDirection.DOWN);
        gameLogic.moveSnake(SnakeDirection.RIGHT);

        assertTrue(gameLogic.checkSnakeSelfCollision());
    }

    @Test
    public void isSnakeFakeSelfCollisionDetected() {
        assertFalse(gameLogic.checkSnakeSelfCollision());
    }

    @Test
    public void isSnakePossibleToMoveTowardsTail() {
        Vector2 oldPosition = gameLogic.getSnakeSegments().get(0);

        gameLogic.moveSnake(SnakeDirection.DOWN);

        assertSame(oldPosition, gameLogic.getSnakeSegments().get(0));
    }

    @Test
    public void isSnakeRealAppleCollisionDetected() {
        gameLogic.setApplePosition(gameLogic.getPlaneSegments().get(gameLogic.getPlaneSegments().size() - 2));
        gameLogic.moveSnake(SnakeDirection.UP);

        assertTrue(gameLogic.checkSnakeAppleCollision());
    }

    @Test
    public void isSnakeFakeAppleCollisionDetected() {
        assertFalse(gameLogic.checkSnakeAppleCollision());
    }

    @Test
    public void isNewApplePositionDifferentThanBefore() {
        Vector2 oldPosition = gameLogic.getApplePosition();

        gameLogic.moveAppleToRandomPosition();

        assertNotSame(oldPosition, gameLogic.getApplePosition());
    }

    @Test
    public void isNewApplePositionOnPlaneSegments() {
        gameLogic.moveAppleToRandomPosition();

        assertTrue(gameLogic.getPlaneSegments().contains(gameLogic.getApplePosition()));
    }

    @Test
    public void isSnakeOutOfGameAreaDetected() {
        gameLogic.moveSnake(SnakeDirection.RIGHT);

        assertTrue(gameLogic.checkSnakeOutOfGameAreaCollision());
    }
}
