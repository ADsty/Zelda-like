package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyGdxGame;

public class DesktopLauncher {
    public static void main(String[] arg) {
        System.setProperty("user.name", "Public");
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Zelda-Like Game";
        config.width = MyGdxGame.WIDTH;
        config.height = MyGdxGame.HEIGHT;
        new LwjglApplication(new MyGdxGame(), config);
    }
}
