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

    public Camera() {

    }

    public float getDefaultAngle() {
        return defaultAngle;
    }

    public float getAngle() {
        return angle;
    }
    
    public float getAngleStep() {
        return angleStep;
    }
    
    public void setAngle() {
        this.angle += angleStep;
    }

    public boolean isTurning() {
        return isTurning;
    }

    public void setTurningFalse() {
        isTurning = false;
    }

    public float getTargetAngle() {
        return targetAngle;
    }

    public void setTargetAngle(float camAngle) {
        targetAngle += camAngle;
        isTurning = true;
    }
}
