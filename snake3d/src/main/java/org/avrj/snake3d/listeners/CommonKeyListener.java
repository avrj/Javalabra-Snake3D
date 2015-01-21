package org.avrj.snake3d.listeners;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class CommonKeyListener extends InputAdapter {

    public CommonKeyListener() {

    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.ENTER) {
            if (!Gdx.graphics.isFullscreen()) {
                Gdx.graphics.setDisplayMode(Gdx.graphics.getDisplayModes()[0]);
            } else {
                Gdx.graphics.setDisplayMode(1280, 720, false);
            }
            return true;
        }
        return false;
    }
}
