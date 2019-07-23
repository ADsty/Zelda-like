package com.mygdx.game.creatures;

import java.util.Random;

class CreatureAI {
    private Creature creature;
    private int direction = 3;
    private float lastPoint = 0;

    /**
     * Creates simplest ai
     *
     * @param creature is creatures for ai
     */
    CreatureAI(Creature creature) {
        this.creature = creature;
    }

    /**
     * Method randomizes creatures movement direction
     *
     * @param dt is delta time
     */
    void update(float dt) {
        lastPoint += dt;
        if (lastPoint >= 0.5f) {
            lastPoint = 0;
            changeDirection();
        }
        if (direction == 1) {
            creature.goUp(dt);
        }
        if (direction == 2) {
            creature.goRight(dt);
        }
        if (direction == 3) {
            creature.goDown(dt);
        }
        if (direction == 4) {
            creature.goLeft(dt);
        }
    }

    private void changeDirection() {
        Random random = new Random();
        direction = random.nextInt(4) + 1;
    }
}
