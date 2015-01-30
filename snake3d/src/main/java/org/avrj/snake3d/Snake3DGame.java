package org.avrj.snake3d;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Snake3DGame {
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    public static final String TITLE = "Snake3D";

    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        setConfiguration(config);

        LwjglApplication lwjglApplication = new LwjglApplication(new Snake3D(), config);
    }

    private static void setConfiguration(LwjglApplicationConfiguration config) {
        config.title = TITLE;
        config.samples = 8;
        config.width = HEIGHT;
        config.height = WIDTH;
    }
}
