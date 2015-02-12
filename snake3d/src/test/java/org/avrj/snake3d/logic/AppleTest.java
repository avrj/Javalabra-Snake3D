package org.avrj.snake3d.logic;

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
public class AppleTest {

    private Apple apple;

    public AppleTest() {
        apple = new Apple();
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
    public void appleIsAtZeroPositionWhenInitialized() {
        assertEquals(apple.getPosition(), new Vector2(0, 0));
    }

    @Test
    public void applePositionIsSetRight() {
        Vector2 position = new Vector2(25, 10);
        apple.setPosition(position);
        assertEquals(apple.getPosition(), position);
    }

    @Test
    public void appleIsPositionedRightWhenRandomized() {
        ArrayList<Vector2> goodPositions = new ArrayList<>();
        goodPositions.add(new Vector2(0, 0));
        goodPositions.add(new Vector2(1, 0));
        goodPositions.add(new Vector2(2, 0));
        goodPositions.add(new Vector2(3, 0));

        ArrayList<Vector2> badPositions = new ArrayList<>();
        goodPositions.add(new Vector2(0, 1));
        goodPositions.add(new Vector2(0, 1));
        goodPositions.add(new Vector2(0, 1));
        goodPositions.add(new Vector2(0, 1));

        for (int i = 0; i <= 10; i++) {
            apple.moveAppleToRandomPosition(goodPositions, badPositions);

            assertTrue(goodPositions.contains(apple.getPosition()));
            assertFalse(badPositions.contains(apple.getPosition()));
        }
    }
}
