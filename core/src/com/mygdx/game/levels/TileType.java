package com.mygdx.game.levels;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.creatures.Link;

public class TileType {
    public static final int TILE_SIZE = 16;

    public TileType() {

    }

    /**
     * Method check for overlaps between player and door marks of current map
     *
     * @param tiledMap is current map of game field
     * @param link     is main hero of game
     * @return
     */
    public boolean isDoor(TiledMap tiledMap, Link link) {
        Rectangle rectangle = new Rectangle();
        rectangle.x = link.getCreatureRectangle().x;
        rectangle.y = link.getCreatureRectangle().y;
        for (MapObject object : tiledMap.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            if (rect.overlaps(rectangle)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method finds a tile from current player's tile
     *
     * @param dx       is difference of X coordinate between tiles
     * @param dy       is difference of Y coordinate between tiles
     * @param link     is the main hero
     * @param tiledMap is current map
     * @return id of needed cell
     */
    private int getTileId(int dx, int dy, Link link, TiledMap tiledMap) {
        TiledMapTileLayer tileid = (TiledMapTileLayer) tiledMap.getLayers().get(0);
        int x = (int) link.getPositionX() / TileType.TILE_SIZE;
        int y = (int) link.getPositionY() / TileType.TILE_SIZE;
        return tileid.getCell(x + dx, y + dy).getTile().getId();
    }

    /**
     * Method checks if the player go away from the map
     *
     * @param link     is the main hero
     * @param tiledMap is current map
     * @return true if the player go away from the map
     */
    public boolean checkLevelEdge(Link link, TiledMap tiledMap) {
        try {
            return getTileId(0, 0, link, tiledMap) <= 0;
        } catch (NullPointerException e) {
            return true;
        }
    }

    /**
     * Method finds direction of next level
     *
     * @param link is the main hero
     * @return X and Y coordinates of next level
     */
    public int[] getEdgeDirection(Link link) {
        Rectangle rectangle = link.getCreatureRectangle();
        int[] array = new int[2];
        if (rectangle.x < 0 && rectangle.y >= 0 && rectangle.y < TILE_SIZE * LevelManager.MAP_HEIGHT) {
            array[0] = -1;
        } else if (rectangle.x >= 0 && rectangle.y > TILE_SIZE * LevelManager.MAP_HEIGHT &&
                rectangle.x < TILE_SIZE * LevelManager.MAP_WIDTH) {
            array[1] = -1;
        } else if (rectangle.x >= 0 && rectangle.y < 0 && rectangle.x < TILE_SIZE * LevelManager.MAP_WIDTH) {
            array[1] = 1;
        } else if (rectangle.x > TILE_SIZE * LevelManager.MAP_WIDTH && rectangle.y >= 0
                && rectangle.y < TILE_SIZE * LevelManager.MAP_HEIGHT) {
            array[0] = 1;
        }
        return array;
    }

}
