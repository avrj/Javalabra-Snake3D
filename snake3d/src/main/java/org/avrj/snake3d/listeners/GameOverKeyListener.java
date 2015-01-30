package org.avrj.snake3d.listeners;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import org.avrj.snake3d.Snake3D;

public class GameOverKeyListener extends InputAdapter {

    private final Snake3D snake3d;

    public GameOverKeyListener(Snake3D snake3d) {
        this.snake3d = snake3d;
    }

    @Override
    public boolean keyDown(int keyCode) {
        if (keyCode == Input.Keys.ESCAPE) {
            Gdx.app.exit();
            snake3d.multiplexer.removeProcessor(this);

            return true;
        }

        if (keyCode == Input.Keys.SPACE) {
            snake3d.getScreen().setDone();
            snake3d.multiplexer.removeProcessor(this);

            return true;
        }
        return false;
    }
}
