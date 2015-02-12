package org.avrj.snake3d.logic;

import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.Random;

/**
 * The apple object (can be "eaten" by the snake)
 *
 * @author araiha
 */
public class Apple {

    private Vector2 applePosition;

    /**
     * Sets the position of the apple to (0, 0)
     */
    public Apple() {
        applePosition = new Vector2(0, 0);
    }

    public Vector2 getPosition() {
        return applePosition;
    }

    /**
     * A method to randomize the position of the apple.
     *
     * @param goodPositions List of proper positions
     * @param badPositions List of invalid positions
     */
    public void moveAppleToRandomPosition(ArrayList<Vector2> goodPositions, ArrayList<Vector2> badPositions) {
        Vector2 applePositionOld = applePosition;

        applePosition = goodPositions.get(new Random().nextInt(goodPositions.size()));

        while (applePosition == applePositionOld || badPositions.contains(applePosition)) {
            applePosition = goodPositions.get(new Random().nextInt(goodPositions.size()));
        }
    }

    public void setPosition(Vector2 position) {
        applePosition = position;
    }
}
