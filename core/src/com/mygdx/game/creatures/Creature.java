package com.mygdx.game.creatures;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.levels.TileType;
import com.mygdx.game.states.PlayState;


public abstract class Creature {
    private Texture creature;
    private Rectangle creatureRectangle;
    private PlayState state;
    private int health;
    private int damage;
    private Animation goLeft;
    private Animation goRight;
    private Animation goUp;
    private Animation goDown;
    private Animation attackUp;
    private Animation attackDown;
    private Animation attackLeft;
    private Animation attackRight;
    private Sound deathSound;
    private World world;
    private Body body;
    private int direction = 3;
    private boolean countActive = true;
    private float lastDamage = 0;

    /**
     * Creates all creatures of game
     *
     * @param x           is X coordinate of creature
     * @param y           is Y coordinate of creature
     * @param texture     is start texture of creature
     * @param health      is start health of creature
     * @param damage      is damage of creature
     * @param goLeft      is animation of left side move
     * @param goRight     is animation of right side move
     * @param goUp        is animation of up side move
     * @param goDown      is animation of down side move
     * @param attackLeft  is animation of left side attack
     * @param attackRight is animation of right side attack
     * @param attackUp    is animation of up side attack
     * @param attackDown  is animation of down side attack
     * @param state       is current PlayState object
     */
    Creature(int x, int y, String texture, int health, int damage, Animation goLeft,
             Animation goRight, Animation goUp, Animation goDown, Animation attackLeft,
             Animation attackRight, Animation attackUp, Animation attackDown, PlayState state) {
        creature = new Texture(texture);
        creatureRectangle = new Rectangle();
        this.state = state;
        creatureRectangle.x = x;
        creatureRectangle.y = y;
        creatureRectangle.width = creature.getWidth();
        creatureRectangle.height = creature.getHeight();
        this.health = health;
        this.damage = damage;
        this.goLeft = goLeft;
        this.goRight = goRight;
        this.goDown = goDown;
        this.goUp = goUp;
        this.attackLeft = attackLeft;
        this.attackRight = attackRight;
        this.attackUp = attackUp;
        this.attackDown = attackDown;
        this.world = state.getWorld();
        this.deathSound = state.getMusicManager().getManager().get("audio/sounds/Sound Effect (3).wav", Sound.class);
        createBody();
        this.body.setActive(true);
    }

    /**
     * Method creates body for creature
     */
    private void createBody() {
        BodyDef bodyDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(creatureRectangle.x, creatureRectangle.y);
        body = world.createBody(bodyDef);
        shape.setAsBox((float) TileType.TILE_SIZE / 2 - 4, (float) TileType.TILE_SIZE / 2 - 2);
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);
    }

    public int getDamage() {
        return damage;
    }

    public int getHealth() {
        return health;
    }

    void setHP(int health) {
        this.health = health;
    }

    public Texture getCreature() {
        return creature;
    }

    public Rectangle getCreatureRectangle() {
        return creatureRectangle;
    }

    /**
     * Method updates current texture for creature to next left side movement animation texture
     *
     * @param value is delta time
     */
    public void goLeft(float value) {
        body.applyLinearImpulse(new Vector2(-100f, 0), body.getWorldCenter(), true);
        goLeft.update(value);
        creature = goLeft.getTexture();
        direction = 4;
    }

    /**
     * Method updates current texture for creature to next right side movement animation texture
     *
     * @param value is delta time
     */
    public void goRight(float value) {
        body.applyLinearImpulse(new Vector2(100f, 0), body.getWorldCenter(), true);
        goRight.update(value);
        creature = goRight.getTexture();
        direction = 2;
    }

    /**
     * Method updates current texture for creature to next up side movement animation texture
     *
     * @param value is delta time
     */
    public void goUp(float value) {
        body.applyLinearImpulse(new Vector2(0, 100f), body.getWorldCenter(), true);
        goUp.update(value);
        creature = goUp.getTexture();
        direction = 1;
    }

    /**
     * Method updates current texture for creature to next down side movement animation texture
     *
     * @param value is delta time
     */
    public void goDown(float value) {
        body.applyLinearImpulse(new Vector2(0, -100f), body.getWorldCenter(), true);
        goDown.update(value);
        creature = goDown.getTexture();
        direction = 3;
    }

    /**
     * Method updates all creature's positions
     */
    public void update() {
        creatureRectangle.x = body.getPosition().x;
        creatureRectangle.y = body.getPosition().y;
        creatureRectangle.width = creature.getWidth();
        creatureRectangle.height = creature.getHeight();
        Vector2 vector = new Vector2();
        vector.x = 0f;
        vector.y = 0f;
        body.setLinearVelocity(vector);
    }

    public void setStayPosition() {
        creature = Animation.stayTexture(direction);
    }

    /**
     * Method changes current texture to attack texture
     */
    public void attack() {
        if (direction == 3) {
            creature = attackDown.getTexture();
        } else if (direction == 1) {
            creature = attackUp.getTexture();
        } else if (direction == 4) {
            creature = attackLeft.getTexture();
        } else if (direction == 2) {
            creature = attackRight.getTexture();
        }
    }

    /**
     * Method tries to make damage to other creature and if it does then creates a delay for next hit
     *
     * @param damage   is creatures damage
     * @param creature is damaged creature
     * @param dt       is delta time
     */
    public void makeDamage(int damage, Creature creature, float dt) {
        if (countActive) {
            lastDamage += dt;
        }
        if (lastDamage >= 0.25) {
            countActive = false;
        }
        if (checkBoxes(creature) && lastDamage >= 0.25) {
            creature.setHealth(damage);
            lastDamage = 0;
            countActive = true;
        }
    }

    public float getPositionX() {
        return body.getWorldCenter().x;
    }

    public float getPositionY() {
        return body.getWorldCenter().y;
    }

    public Body getBody() {
        return body;
    }

    private void setHealth(int damage) {
        health -= damage;
    }

    /**
     * Method checks if this creature's texture is in other creature's texture
     *
     * @param creature is other creature
     * @return
     */
    private boolean checkBoxes(Creature creature) {
        return creatureRectangle.x < creature.creatureRectangle.x + creature.creatureRectangle.width
                && creatureRectangle.x + creatureRectangle.width > creature.creatureRectangle.x
                && creatureRectangle.y < creature.creatureRectangle.y + creature.creatureRectangle.height
                && creatureRectangle.y + creatureRectangle.height > creature.creatureRectangle.y;
    }

    boolean checkBoxes(Rectangle rectangle) {
        return creatureRectangle.x < rectangle.x + rectangle.width
                && creatureRectangle.x + creatureRectangle.width > rectangle.x
                && creatureRectangle.y < rectangle.y + rectangle.height
                && creatureRectangle.y + creatureRectangle.height > rectangle.y;
    }

    public boolean isDead() {
        return health <= 0;
    }

    /**
     * Method sets position for creature when is comes to new level
     */
    public void setNewPosition() {
        if (state.getManager().dungeonStart()) {
            creatureRectangle.x = 20;
            creatureRectangle.y = 85;
        } else if (state.getManager().dungeonExit()) {
            creatureRectangle.x = 132;
            creatureRectangle.y = 80;
        } else if (direction == 1) {
            creatureRectangle.y = TileType.TILE_SIZE;
            System.out.println(creatureRectangle.x);
            System.out.println(creatureRectangle.y);
        } else if (direction == 2) {
            creatureRectangle.x = TileType.TILE_SIZE;
            System.out.println(creatureRectangle.x);
            System.out.println(creatureRectangle.y);
        } else if (direction == 3) {
            creatureRectangle.y = 176 - TileType.TILE_SIZE;
            System.out.println(creatureRectangle.x);
            System.out.println(creatureRectangle.y);
        } else if (direction == 4) {
            creatureRectangle.x = 256 - TileType.TILE_SIZE;
            System.out.println(creatureRectangle.x);
            System.out.println(creatureRectangle.y);
        }
    }

    public void playDeathSound() {
        deathSound.play(0.1f);
    }

    public void dispose() {
        creature.dispose();
        goLeft.dispose();
        goRight.dispose();
        goUp.dispose();
        goDown.dispose();
        attackLeft.dispose();
        attackRight.dispose();
        attackUp.dispose();
        attackDown.dispose();
    }
}
