package com.mygdx.game.levels;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.Array;

public class LevelManager {
    private Array<Level> manager;
    private OrthographicCamera camera;
    private TiledMap tiledMap;
    private int currentLevel;
    private int currentDungeon;
    private int pastDungeon = 0;
    private DungeonManager dungeons;
    public static final int MAP_WIDTH = 16;
    public static final int MAP_HEIGHT = 11;
    private OrthogonalTiledMapRenderer tiledMapRenderer;

    /**
     * Creates manager which contains all levels and dungeons of game
     *
     * @param camera    is game camera
     * @param levelX    is current level X coordinate
     * @param levelY    is current level Y coordinate
     * @param inDungeon true if player is in dungeon right now
     * @param dungeonX  is current dungeon X coordinate
     * @param dungeonY  is current dungeon Y coordinate
     */
    public LevelManager(OrthographicCamera camera, int levelX, int levelY, boolean inDungeon, int dungeonX, int dungeonY) {
        currentDungeon = dungeonY * 2 + dungeonX;
        currentLevel = levelY * 4 + levelX;
        manager = new Array<Level>();
        this.camera = camera;
        dungeons = new DungeonManager();
        dungeons.setDungeons();
        startGame();
        dungeons.setInDungeon(inDungeon);
        if (dungeons.inDungeon()) {
            tiledMap = new TmxMapLoader().load(dungeons.getCurrentDungeonLevel(currentDungeon).getMapURL());
        } else {
            tiledMap = new TmxMapLoader().load(manager.get(currentLevel).getMapURL());
        }
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
    }

    /**
     * Loads all level maps
     */
    private void startGame() {
        Level testLevel = new Level("maps/map1 1.tmx", 0, 0);
        Level testLevel1 = new Level("maps/map1 2.tmx", 1, 0);
        Level testLevel2 = new Level("maps/map1 3.tmx", 2, 0);
        Level testLevel3 = new Level("maps/map1 4.tmx", 3, 0);
        Level testLevel4 = new Level("maps/map2 1.tmx", 0, 1);
        Level testLevel5 = new Level("maps/map2 2.tmx", 1, 1);
        Level testLevel6 = new Level("maps/map2 3.tmx", 2, 1);
        Level testLevel7 = new Level("maps/map2 4.tmx", 3, 1);
        Level testLevel8 = new Level("maps/map3 1.tmx", 0, 2);
        Level testLevel9 = new Level("maps/map3 2.tmx", 1, 2);
        Level testLevel10 = new Level("maps/map3 3.tmx", 2, 2);
        Level testLevel11 = new Level("maps/map3 4.tmx", 3, 2);
        Level testLevel12 = new Level("maps/map4 1.tmx", 0, 3);
        Level testLevel13 = new Level("maps/map4 2.tmx", 1, 3);
        Level testLevel14 = new Level("maps/map4 3.tmx", 2, 3);
        Level testLevel15 = new Level("maps/map4 4.tmx", 3, 3);
        manager.add(testLevel);
        manager.add(testLevel1);
        manager.add(testLevel2);
        manager.add(testLevel3);
        manager.add(testLevel4);
        manager.add(testLevel5);
        manager.add(testLevel6);
        manager.add(testLevel7);
        manager.add(testLevel8);
        manager.add(testLevel9);
        manager.add(testLevel10);
        manager.add(testLevel11);
        manager.add(testLevel12);
        manager.add(testLevel13);
        manager.add(testLevel14);
        manager.add(testLevel15);
    }

    /**
     * Method draw current map
     */
    public void drawCreatedLevel() {
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
    }

    public void goInDungeon() {
        dungeons.goInDungeon();
    }

    public void exitFromDungeon() {
        dungeons.exitFromDungeon();
    }

    public boolean inDungeon() {
        return dungeons.inDungeon();
    }

    /**
     * Method changes current level
     *
     * @param dx is X difference coordinate of next level
     * @param dy is Y difference coordinate of next level
     */
    public void changeLevel(int dx, int dy) {
        if (inDungeon()) {
            pastDungeon = currentDungeon;
            currentDungeon += dy * 2 + dx;
        } else {
            currentLevel += dy * 4 + dx;
        }
        if (dungeonExit()) {
            currentLevel = 1;
        }
    }

    public TiledMap getTiledMap() {
        return tiledMap;
    }

    public void dispose() {
        tiledMap.dispose();
    }

    /**
     * Method checks if player just go in dungeon
     */
    public boolean dungeonStart() {
        return currentDungeon == 0 && pastDungeon == 0 && inDungeon();
    }

    /**
     * Method checks if player just find an exit from dungeon
     */
    public boolean dungeonExit() {
        if (currentDungeon == 0 && !inDungeon() && currentLevel == 1) {
            pastDungeon = 0;
            return true;
        }
        return false;
    }
}
