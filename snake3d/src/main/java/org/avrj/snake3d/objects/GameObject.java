package org.avrj.snake3d.objects;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

/**
 * Every 3D model is a GameObject
 */
public class GameObject extends ModelInstance {

    public final Vector3 center = new Vector3();
    public final Vector3 dimensions = new Vector3();
    public final float radius;

    private final static BoundingBox bounds = new BoundingBox();

    /**
     * Initializes a GameObject based on Model
     *
     * @param model the model
     */
    public GameObject(Model model) {
        super(model);
        calculateBoundingBox(bounds);
        bounds.getCenter();
        bounds.getDimensions();
        radius = dimensions.len() / 2f;

    }
}
