package org.avrj.snake3d.listeners;

import com.badlogic.gdx.InputAdapter;
import org.avrj.snake3d.Snake3D;

public class KeyboardInputController extends InputAdapter {

    protected Snake3D snake3d;

    public KeyboardInputController(Snake3D snake3d) {
        this.snake3d = snake3d;
    }

    @Override
    public boolean keyDown(int i) {
        return true;
    }
}
