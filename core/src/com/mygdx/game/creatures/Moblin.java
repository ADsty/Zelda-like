package com.mygdx.game.creatures;

import com.mygdx.game.states.PlayState;

public class Moblin extends Creature {
    private CreatureAI ai;

    Moblin(int x, int y, String texture, int health, int damage, Animation goLeft, Animation goRight, Animation goUp, Animation goDown, Animation attackLeft, Animation attackRight, Animation attackUp, Animation attackDown, PlayState state) {
        super(x, y, texture, health, damage, goLeft, goRight, goUp, goDown, attackLeft, attackRight, attackUp, attackDown, state);
        this.ai = new CreatureAI(this);
    }

    public void update(float dt) {
        update();
        if (!isDead()) {
            ai.update(dt);
        }
    }
}
