package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.levels.LevelManager;
import com.mygdx.game.levels.TileType;
import com.mygdx.game.states.GameStateManager;
import com.mygdx.game.states.MenuState;

public class MyGdxGame extends ApplicationAdapter {
    public static final int WIDTH = TileType.TILE_SIZE * LevelManager.MAP_WIDTH * 3;
    public static final int HEIGHT = TileType.TILE_SIZE * LevelManager.MAP_HEIGHT * 3;
    private GameStateManager gsm;
    public static SpriteBatch batch;

    /**
     * Start method of LibGDX app lifecycle which creates all main game classes
     */
    @Override
    public void create() {
        batch = new SpriteBatch();
        gsm = new GameStateManager();
        Gdx.gl.glClearColor(0, 0, 0, 0);
        gsm.push(new MenuState(gsm));
    }

    /**
     * Main method of LibGDX app which renders all textures at the screen
     */
    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.render(batch);
    }
}
