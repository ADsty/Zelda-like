package com.mygdx.game.levels;

import com.badlogic.gdx.utils.Array;

class DungeonManager {
    private Array<Level> manager;
    private boolean inDungeon = false;

    /**
     * Creates manager which contains all dungeons
     */
    DungeonManager() {
        manager = new Array<Level>();
    }

    /**
     * Method loads all dungeon maps
     */
    void setDungeons() {
        Level testLevel = new Level("maps/dungeon1 1.tmx", 0, 0);
        Level testLevel1 = new Level("maps/dungeon1 2.tmx", 1, 0);
        Level testLevel2 = new Level("maps/dungeon2 1.tmx", 0, 1);
        Level testLevel3 = new Level("maps/dungeon2 2.tmx", 1, 1);
        manager.add(testLevel);
        manager.add(testLevel1);
        manager.add(testLevel2);
        manager.add(testLevel3);
    }

    boolean inDungeon() {
        return inDungeon;
    }

    void goInDungeon() {
        inDungeon = true;
    }

    void exitFromDungeon() {
        inDungeon = false;
    }

    Level getCurrentDungeonLevel(int id) {
        return manager.get(id);
    }

    void setInDungeon(boolean inDungeon) {
        this.inDungeon = inDungeon;
    }

}
