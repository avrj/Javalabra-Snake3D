package org.avrj.snake3d.logic;

/**
 * A helper class for the 3d-camera
 *
 * @author araiha
 */
public class Camera {

    private final float defaultAngle = -90;
    private float currentAngle = defaultAngle;

    public float getAngle() {
        return currentAngle;
    }

    public void addDegrees(float angle) {
        this.currentAngle += angle;
    }
}
