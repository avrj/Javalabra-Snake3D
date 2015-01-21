package org.avrj.snake3d.listeners;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import org.avrj.snake3d.Snake3D;
import org.avrj.snake3d.scenes.MainMenuScene;

public class MainMenuKeyListener extends InputAdapter {

    private final Snake3D snake3d;
    private MainMenuScene scene;

    public MainMenuKeyListener(Snake3D snake3d, MainMenuScene scene) {
        this.snake3d = snake3d;
        this.scene = scene;
    }

    @Override
    public boolean keyDown(int keyCode) {
        if (keyCode == Input.Keys.SPACE) {
            scene.isDone = true;

            System.out.println("debug");

            snake3d.multiplexer.removeProcessor(this);

            return true;
        }
        return false;
    }
}
