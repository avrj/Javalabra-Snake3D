package org.avrj.snake3d;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Snake3DGame {

    public static final int WINDOW_WIDTH = 1280;
    public static final int WINDOW_HEIGHT = 720;
    private static final String WINDOW_TITLE = "Snake3D";

    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        setConfiguration(config);

        LwjglApplication lwjglApplication = new LwjglApplication(new Snake3D(), config);
    }

    private static void setConfiguration(LwjglApplicationConfiguration config) {
        config.title = WINDOW_TITLE;
        config.samples = 8;
        config.width = WINDOW_WIDTH;
        config.height = WINDOW_HEIGHT;
    }
}
