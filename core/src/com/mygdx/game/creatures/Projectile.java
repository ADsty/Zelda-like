package com.mygdx.game.creatures;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.levels.LevelManager;
import com.mygdx.game.levels.TileType;

public class Projectile {
    private int direction;
    private Rectangle position;
    private Texture texture;
    private int damage;
    private LevelManager manager;

    /**
     * Unused class for arrows and all other projectiles
     */
    Projectile(int direction, Rectangle startPosition, Texture texture, int damage, LevelManager manager) {
        this.direction = direction;
        this.position = startPosition;
        this.texture = texture;
        this.damage = damage;
        this.manager = manager;
    }

    private void setPosition(float x, float y) {
        position.x += 100 * x;
        position.y += 100 * y;
    }

    private void goLeft(float value) {
        setPosition(-value, 0);
    }

    private void goRight(float value) {
        setPosition(value, 0);
    }

    private void goUp(float value) {
        setPosition(0, value);
    }

    private void goDown(float value) {
        setPosition(0, -value);
    }

    public void update(float dt) {
        if (direction == 0) {
            dispose();
        }
        if (direction == 1) {
            goUp(dt);
        } else if (direction == 2) {
            goRight(dt);
        } else if (direction == 3) {
            goDown(dt);
        } else if (direction == 4) {
            goLeft(dt);
        }
        if (checkMapEdge()) {
            dispose();
        }
    }

    public Texture getTexture() {
        return texture;
    }

    private int getTileId(TiledMap tiledMap) {
        TiledMapTileLayer tileid = (TiledMapTileLayer) tiledMap.getLayers().get(0);
        int x = (int) position.x / TileType.TILE_SIZE;
        int y = (int) position.y / TileType.TILE_SIZE;
        return tileid.getCell(x, y).getTile().getId();
    }

    private boolean checkMapEdge() {
        try {
            return getTileId(manager.getTiledMap()) < 0;
        } catch (NullPointerException e) {
            return true;
        }
    }

    public boolean checkCreatureHit(Creature creature) {
        Rectangle rectangle = creature.getCreatureRectangle();
        if (creature.checkBoxes(position)) {
            //creature.setHealth(damage);
            dispose();
            this.direction = 0;
            return true;
        }
        return false;
    }

    public Rectangle getPosition() {
        return position;
    }

    private void dispose() {
        texture.dispose();
    }
}
