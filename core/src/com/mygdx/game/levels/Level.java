package com.mygdx.game.levels;

public class Level {
    private String mapURL;
    private int levelPositionX;
    private int levelPositionY;

    /**
     * Creates new level
     *
     * @param mapURL         is path to level map
     * @param levelPositionX is X coordinate of level
     * @param levelPositionY is Y coordinate of level
     */
    Level(String mapURL, int levelPositionX, int levelPositionY) {
        this.mapURL = mapURL;
        this.levelPositionX = levelPositionX;
        this.levelPositionY = levelPositionY;
    }


    public int getLevelPositionX() {
        return levelPositionX;
    }

    public int getLevelPositionY() {
        return levelPositionY;
    }

    String getMapURL() {
        return mapURL;
    }
}
