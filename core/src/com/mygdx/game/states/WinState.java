package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.mygdx.game.levels.MusicManager;
import com.mygdx.game.levels.TileType;

public class WinState extends State {
    private OrthogonalTiledMapRenderer tiledMapRenderer;
    private float time;

    /**
     * It is the finish point of game which renders win screen and play some music
     * after which it will be changed to black screen
     */
    WinState(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, 256, 176);
        TiledMap tiledMap = new TmxMapLoader().load("maps/dungeon2 1.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        MusicManager musicManager = new MusicManager();
        Sound music = musicManager.getManager().get("audio/sounds/Sound Effect (20).wav");
        music.play(0.1f);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            gsm.set(new GameOverState(gsm));
        }
        time += Gdx.graphics.getDeltaTime();
        if (time >= 2f) {
            gsm.set(new GameOverState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        Texture chestOpen = new Texture("items/chestOpen.png");
        Texture linkWin = new Texture("link images/linkGetItem.png");
        Texture winItem = new Texture("items/rupee.png");
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(chestOpen, (float) 256 / 2, (float) 176 / 2 - 6);
        sb.draw(linkWin, (float) 256 / 2, (float) 176 / 2 - 6);
        sb.draw(winItem, (float) 256 / 2 + 4, (float) 176 / 2 + TileType.TILE_SIZE - 6);
        sb.end();
    }

    @Override
    public void dispose() {
        tiledMapRenderer.dispose();
    }
}
