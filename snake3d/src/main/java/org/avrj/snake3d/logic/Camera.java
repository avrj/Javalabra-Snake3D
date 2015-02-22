package org.avrj.snake3d.logic;

/**
 * A helper class for the 3d-camera
 *
 * @author araiha
 */
public class Camera {

    private boolean isTurning = false;
    private float targetAngle = 0;
    private float defaultAngle = -90;
    private float angle = defaultAngle;
    private float angleStep = 5;
    private boolean shouldRotate = false;

    public Camera() {

    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
        
        shouldRotate = true;
    }
}
