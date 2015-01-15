/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avrj.snake3d;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Snake3DGame {

    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        config.title = "Snake3D";
        config.samples = 8;
        config.width = 1280;
        config.height = 720;
        config.useGL30 = true;

        LwjglApplication lwjglApplication = new LwjglApplication(new Snake3D(), config);
    }
}
