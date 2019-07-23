package com.mygdx.game.states;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Controller;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.creatures.Creature;
import com.mygdx.game.creatures.CreatureFactory;
import com.mygdx.game.creatures.Deku;
import com.mygdx.game.creatures.Link;
import com.mygdx.game.levels.LevelManager;
import com.mygdx.game.levels.MusicManager;
import com.mygdx.game.levels.TileType;
import com.mygdx.game.world.WorldCreator;

import java.util.Random;


public class PlayState extends State {
    private Controller controller;
    private Stage stage;
    private Link link;
    private CreatureFactory factory;
    private TileType tileType;
    private LevelManager manager;
    private World world;
    private WorldCreator worldCreator;
    private Hud hud;
    private MusicManager musicManager;
    private int levelX;
    private int levelY;
    private int dungeonX;
    private int dungeonY;
    private boolean inDungeon;
    private float linkX = 300;
    private float linkY = 200;
    private int linkHealth;

    /**
     * Creates new game field with all needed objects
     *
     * @param gsm        is GameStateManager object which controls game
     * @param levelX     is current level X coordinate
     * @param levelY     is current level Y coordinate
     * @param linkHealth is current link health
     * @param linkX      is current link position X coordinate
     * @param linkY      is current link position Y coordinate
     * @param inDungeon  will be true if the player is in the dungeon right now
     * @param dungeonX   current dungeon X coordinate
     * @param dungeonY   current dungeon Y coordinate
     */
    PlayState(GameStateManager gsm, int levelX, int levelY, int linkHealth, float linkX, float linkY, boolean inDungeon, int dungeonX, int dungeonY) {
        super(gsm);
        Music music;
        this.levelX = levelX;
        this.levelY = levelY;
        this.linkHealth = linkHealth;
        this.dungeonX = dungeonX;
        this.dungeonY = dungeonY;
        this.inDungeon = inDungeon;
        musicManager = new MusicManager();
        stage = createStage();
        controller = new Controller(stage);
        factory = new CreatureFactory();
        camera.setToOrtho(false, 256, 176);
        tileType = new TileType();
        manager = new LevelManager(camera, levelX, levelY, this.inDungeon, dungeonX, dungeonY);
        world = new World(new Vector2(0, 0), true);
        worldCreator = new WorldCreator(this);
        link = factory.createPlayer(this, linkX, linkY);
        Random random = new Random();
        for (int i = 1; i < random.nextInt(3) + 4; i++) {
            factory.createEnemie(this);
        }
        hud = new Hud(MyGdxGame.batch, this);
        if (manager.inDungeon()) {
            music = musicManager.getManager().get("audio/music/Legend of Zelda - NES - Dungeon Theme.mp3", Music.class);
        } else {
            music = musicManager.getManager().get("audio/music/The Legend of Zelda - NES - Overworld.mp3", Music.class);
        }
        music.setLooping(true);
        music.setVolume(0.1f);
        music.play();
    }

    public WorldCreator getWorldCreator() {
        return worldCreator;
    }

    /**
     * Method creates new stage for controller
     */
    private Stage createStage() {
        Viewport viewport = new FitViewport(MyGdxGame.WIDTH - 100, MyGdxGame.HEIGHT - 100, camera);
        return new Stage(viewport, MyGdxGame.batch);
    }

    /**
     * Method handles input that player gives
     */
    @Override
    protected void handleInput() {
        try {
            if (controller.isLeftPressed()) {
                link.goLeft(Gdx.graphics.getDeltaTime());
            } else if (controller.isRightPressed()) {
                link.goRight(Gdx.graphics.getDeltaTime());
            } else if (controller.isUpPressed()) {
                link.goUp(Gdx.graphics.getDeltaTime());
            } else if (controller.isDownPressed()) {
                link.goDown(Gdx.graphics.getDeltaTime());
            }
            if (controller.noKeyPressed()) {
                link.setStayPosition();
            }
            if ((Gdx.input.isTouched() && controller.noKeyPressed()) || Gdx.input.isKeyPressed(Input.Keys.Z)) {
                link.attack();
                for (Creature creature : factory.getEnemies()) {
                    link.makeDamage(link.getDamage(), creature, Gdx.graphics.getDeltaTime());
                }
            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method updates all objects on the game field
     *
     * @param dt is delta time between frames
     */
    @Override
    public void update(float dt) {
        if (isWon()) {
            gsm.set(new WinState(gsm));
        }
        handleInput();
        world.step(1 / 60f, 6, 2);
        for (Creature creature : factory.getEnemies()) {
            if (creature.isDead()) {
                creature.playDeathSound();
                world.destroyBody(creature.getBody());
                factory.delete(creature);
            }
            ((Deku) creature).update(dt);
            creature.makeDamage(creature.getDamage(), link, dt);
        }
        link.update();
        linkHealth = link.getHealth();
        hud.update();
    }

    /**
     * Method renders all needed objects on game field
     *
     * @param sb is main class SpriteBatch object
     */
    @Override
    public void render(SpriteBatch sb) {
        if (link.isDead()) {
            gsm.set(new GameOverState(gsm));
        }
        if (tileType.checkLevelEdge(link, manager.getTiledMap())) {
            int[] array = tileType.getEdgeDirection(link);
            manager.changeLevel(array[0], array[1]);
            link.setNewPosition();
            if (inDungeon) {
                dungeonX += array[0];
                dungeonY += array[1];
            } else {
                levelX += array[0];
                levelY += array[1];
            }
            System.out.println(array[0]);
            System.out.println(array[1]);
            linkX = link.getCreatureRectangle().x;
            linkY = link.getCreatureRectangle().y;
            gsm.set(new PlayState(gsm, levelX, levelY, linkHealth, linkX, linkY, inDungeon, dungeonX, dungeonY));
        } else if (tileType.isDoor(getMap(), link)) {
            if (manager.inDungeon()) {
                manager.exitFromDungeon();
                link.setNewPosition();
                inDungeon = false;
            } else {
                manager.goInDungeon();
                link.setNewPosition();
                inDungeon = true;
            }
            gsm.set(new PlayState(gsm, levelX, levelY, linkHealth, linkX, linkY, inDungeon, dungeonX, dungeonY));
            System.out.println(inDungeon);
            System.out.println(levelX);
            System.out.println(levelY);
        }
        manager.drawCreatedLevel();
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        if (dungeonY == 1 && dungeonX == 0) {
            sb.draw(new Texture("items/chestClose.png"), (float) 256 / 2, (float) 176 / 2 - 6);
        }
        sb.draw(link.getLink(), link.getPositionX() - (float) TileType.TILE_SIZE / 2, link.getPositionY() - (float) TileType.TILE_SIZE / 2);
        for (Creature creature : factory.getEnemies()) {
            if (!creature.isDead()) {
                sb.draw(creature.getCreature(), creature.getPositionX() - (float) TileType.TILE_SIZE / 2, creature.getPositionY() - (float) TileType.TILE_SIZE / 2);
            }
        }
        sb.end();
        if (Gdx.app.getType() == Application.ApplicationType.Android) {
            controller.draw();
        }
        hud.stage.draw();
    }

    public TiledMap getMap() {
        return manager.getTiledMap();
    }

    public World getWorld() {
        return world;
    }

    Link getLink() {
        return link;
    }

    public LevelManager getManager() {
        return manager;
    }

    public int getLinkHealth() {
        return linkHealth;
    }

    public MusicManager getMusicManager() {
        return musicManager;
    }

    /**
     * Methods checks if the player is near of endpoint
     *
     * @return true if the player is near of endpoint
     */
    private boolean isWon() {
        Rectangle rectangle = new Rectangle();
        rectangle.x = (float) 256 / 2;
        rectangle.y = (float) 176 / 2 - 6;
        rectangle.width = TileType.TILE_SIZE;
        rectangle.height = TileType.TILE_SIZE;
        return dungeonY == 1 && dungeonX == 0 && rectangle.overlaps(link.getCreatureRectangle());
    }

    /**
     * Method disposes all objects of game field
     */
    @Override
    public void dispose() {
        link.dispose();
        manager.dispose();
        factory.dispose();
        hud.dispose();
        musicManager.dispose();
        stage.dispose();
        System.out.println("Play state disposed");
    }
}
