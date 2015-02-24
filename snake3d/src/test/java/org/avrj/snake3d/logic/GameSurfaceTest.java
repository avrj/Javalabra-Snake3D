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
public class GameSurfaceTest {

    private final GameSurface gameSurface;

    public GameSurfaceTest() {
        gameSurface = new GameSurface();
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
    public void vectorsArePositionedRight() {
        ArrayList<Vector2> vectors = new ArrayList<>();
        for (float x = -25f; x <= 25f; x += 6f) {
            for (float z = -25f; z <= 25f; z += 6f) {
                vectors.add(new Vector2(x, z));
            }
        }
        assertEquals(gameSurface.getVectors(), vectors);
    }
}
