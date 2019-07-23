package com.mygdx.game.creatures;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.levels.TileType;
import com.mygdx.game.states.PlayState;

import java.util.Random;

public class CreatureFactory {
    private Array<Creature> enemies;

    /**
     * Creates factory to player and all his enemies
     */
    public CreatureFactory() {
        enemies = new Array<Creature>();
    }

    public void createEnemie(PlayState state) {
        Random random = new Random();
        int id = random.nextInt(3) + 1;
        if (id == 1) {
            createDeku(state);
        } else if (id == 2) {
            createMoblin(state);
        } else if (id == 3) {
            createLynel(state);
        } else {
            createDeku(state);
        }
    }

    private void createDeku(PlayState state) {
        Random random = new Random();
        Rectangle rectangle = new Rectangle();
        rectangle.x = random.nextInt(230) + 10;
        rectangle.y = random.nextInt(150) + 10;
        while (state.getWorldCreator().checkPhysicsBoxes(state, rectangle)) {
            rectangle.x = random.nextInt(230) + 10;
            rectangle.y = random.nextInt(150) + 10;
        }
        rectangle.width = TileType.TILE_SIZE;
        rectangle.height = TileType.TILE_SIZE;
        Animation goDown = new Animation(new Texture("enemie images/enemie1.png"), new Texture("enemie images/enemie2.png"), 2, 0.5f);
        Animation goUp = new Animation(new Texture("enemie images/enemie5.png"), new Texture("enemie images/enemie6.png"), 2, 0.5f);
        Animation goLeft = new Animation(new Texture("enemie images/enemie3.png"), new Texture("enemie images/enemie4.png"), 2, 0.5f);
        Animation goRight = new Animation(new Texture("enemie images/enemie7.png"), new Texture("enemie images/enemie8.png"), 2, 0.5f);
        Animation attackDown = new Animation(new Texture("enemie images/enemie1.png"), new Texture("enemie images/enemie2.png"), 2, 0.5f);
        Animation attackUp = new Animation(new Texture("enemie images/enemie1.png"), new Texture("enemie images/enemie2.png"), 2, 0.5f);
        Animation attackLeft = new Animation(new Texture("enemie images/enemie1.png"), new Texture("enemie images/enemie2.png"), 2, 0.5f);
        Animation attackRight = new Animation(new Texture("enemie images/enemie1.png"), new Texture("enemie images/enemie2.png"), 2, 0.5f);
        Deku enemy = new Deku((int) rectangle.x, (int) rectangle.y, "enemie images/enemie1.png", 1, 1, goLeft, goRight, goUp, goDown, attackLeft, attackRight, attackUp, attackDown, state);
        enemies.add(enemy);
    }

    private void createMoblin(PlayState state) {
        Random random = new Random();
        Rectangle rectangle = new Rectangle();
        rectangle.x = random.nextInt(230) + 10;
        rectangle.y = random.nextInt(150) + 10;
        while (state.getWorldCreator().checkPhysicsBoxes(state, rectangle)) {
            rectangle.x = random.nextInt(230) + 10;
            rectangle.y = random.nextInt(150) + 10;
        }
        rectangle.width = TileType.TILE_SIZE;
        rectangle.height = TileType.TILE_SIZE;
        Animation goDown = new Animation(new Texture("enemie images/Moblin1.png"), new Texture("enemie images/Moblin2.png"), 2, 0.5f);
        Animation goUp = new Animation(new Texture("enemie images/Moblin5.png"), new Texture("enemie images/Moblin6.png"), 2, 0.5f);
        Animation goLeft = new Animation(new Texture("enemie images/Moblin3.png"), new Texture("enemie images/Moblin4.png"), 2, 0.5f);
        Animation goRight = new Animation(new Texture("enemie images/Moblin7.png"), new Texture("enemie images/Moblin8.png"), 2, 0.5f);
        Animation attackDown = new Animation(new Texture("enemie images/Moblin1.png"), new Texture("enemie images/Moblin2.png"), 2, 0.5f);
        Animation attackUp = new Animation(new Texture("enemie images/Moblin1.png"), new Texture("enemie images/Moblin2.png"), 2, 0.5f);
        Animation attackLeft = new Animation(new Texture("enemie images/Moblin1.png"), new Texture("enemie images/Moblin2.png"), 2, 0.5f);
        Animation attackRight = new Animation(new Texture("enemie images/Moblin1.png"), new Texture("enemie images/Moblin2.png"), 2, 0.5f);
        Deku enemy = new Deku((int) rectangle.x, (int) rectangle.y, "enemie images/Moblin1.png", 1, 5, goLeft, goRight, goUp, goDown, attackLeft, attackRight, attackUp, attackDown, state);
        enemies.add(enemy);
    }

    private void createLynel(PlayState state) {
        Random random = new Random();
        Rectangle rectangle = new Rectangle();
        rectangle.x = random.nextInt(230) + 10;
        rectangle.y = random.nextInt(150) + 10;
        while (state.getWorldCreator().checkPhysicsBoxes(state, rectangle)) {
            rectangle.x = random.nextInt(230) + 10;
            rectangle.y = random.nextInt(150) + 10;
        }
        rectangle.width = TileType.TILE_SIZE;
        rectangle.height = TileType.TILE_SIZE;
        Animation goDown = new Animation(new Texture("enemie images/Lynel1.png"), new Texture("enemie images/Lynel2.png"), 2, 0.5f);
        Animation goUp = new Animation(new Texture("enemie images/Lynel5.png"), new Texture("enemie images/Lynel6.png"), 2, 0.5f);
        Animation goLeft = new Animation(new Texture("enemie images/Lynel3.png"), new Texture("enemie images/Lynel4.png"), 2, 0.5f);
        Animation goRight = new Animation(new Texture("enemie images/Lynel7.png"), new Texture("enemie images/Lynel8.png"), 2, 0.5f);
        Animation attackDown = new Animation(new Texture("enemie images/Lynel1.png"), new Texture("enemie images/Lynel2.png"), 2, 0.5f);
        Animation attackUp = new Animation(new Texture("enemie images/Lynel1.png"), new Texture("enemie images/Lynel2.png"), 2, 0.5f);
        Animation attackLeft = new Animation(new Texture("enemie images/Lynel1.png"), new Texture("enemie images/Lynel2.png"), 2, 0.5f);
        Animation attackRight = new Animation(new Texture("enemie images/Lynel1.png"), new Texture("enemie images/Lynel2.png"), 2, 0.5f);
        Deku enemy = new Deku((int) rectangle.x, (int) rectangle.y, "enemie images/Lynel1.png", 1, 4, goLeft, goRight, goUp, goDown, attackLeft, attackRight, attackUp, attackDown, state);
        enemies.add(enemy);
    }

    public Link createPlayer(PlayState state, float LinkX, float LinkY) {
        Animation goLeft = new Animation(new Texture("link images/link2.png"), new Texture("link images/link3.png"), 2, 0.3f);
        Animation goRight = new Animation(new Texture("link images/link4.png"), new Texture("link images/link5.png"), 2, 0.3f);
        Animation goUp = new Animation(new Texture("link images/link6.png"), new Texture("link images/link7.png"), 2, 0.3f);
        Animation goDown = new Animation(new Texture("link images/link8.png"), new Texture("link images/link9.png"), 2, 0.3f);
        Animation attackLeft = new Animation(new Texture("link images/link10.png"), new Texture("link images/link3.png"), 2, 0.3f);
        Animation attackRight = new Animation(new Texture("link images/link11.png"), new Texture("link images/link4.png"), 2, 0.3f);
        Animation attackUp = new Animation(new Texture("link images/link12.png"), new Texture("link images/link6.png"), 2, 0.3f);
        Animation attackDown = new Animation(new Texture("link images/link13.png"), new Texture("link images/link8.png"), 2, 0.3f);
        return new Link((int) LinkX, (int) LinkY, "link images/link1.png", 200, 2, goLeft, goRight, goUp, goDown, attackLeft, attackRight, attackUp, attackDown, state);
    }

    public Array<Creature> getEnemies() {
        return enemies;
    }

    public void delete(Creature creature) {
        enemies.removeValue(creature, true);
    }

    public void dispose() {
        for (Creature enemie : enemies) {
            enemie.dispose();
        }
    }
}
