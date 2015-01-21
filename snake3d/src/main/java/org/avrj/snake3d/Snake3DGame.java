package org.avrj.snake3d;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Snake3DGame {

    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        setConfiguration(config);

        LwjglApplication lwjglApplication = new LwjglApplication(new Snake3D(), config);
    }

    private static void setConfiguration(LwjglApplicationConfiguration config) {
        config.title = "Snake3D";
        config.samples = 8;
        config.width = 1280;
        config.height = 720;
    }
}
