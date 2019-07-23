package com.mygdx.game.levels;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class MusicManager {
    private AssetManager manager;

    /**
     * Creates all music of game
     */
    public MusicManager() {
        manager = new AssetManager();
        manager.load("audio/music/Legend of Zelda - NES - Dungeon Theme.mp3", Music.class);
        manager.load("audio/music/The Legend of Zelda - NES - Overworld.mp3", Music.class);
        manager.load("audio/sounds/Sound Effect (3).wav", Sound.class);
        manager.load("audio/sounds/Sound Effect (20).wav", Sound.class);
        manager.load("audio/sounds/Sound Effect (21).wav", Sound.class);
        manager.finishLoading();
    }

    public AssetManager getManager() {
        return manager;
    }

    public void dispose() {
        manager.dispose();
    }
}
