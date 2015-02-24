package org.avrj.snake3d.logic;

import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;

/**
 * The surface of the game (consists of blocks)
 *
 * @author araiha
 */
public class GameSurface {

    private final ArrayList<Vector2> gameSurface;

    /**
     * Creates the surface
     */
    public GameSurface() {
        gameSurface = new ArrayList<>();

        for (float x = -25f; x <= 25f; x += 6f) {
            for (float z = -25f; z <= 25f; z += 6f) {
                gameSurface.add(new Vector2(x, z));
            }
        }
    }

    public ArrayList<Vector2> getVectors() {
        return gameSurface;
    }
}
