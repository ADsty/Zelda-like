package com.mygdx.game.creatures;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.states.PlayState;


public class Link extends Creature {

    private int rupees;

    Link(int x, int y, String texture, int health, int damage, Animation goLeft,
         Animation goRight, Animation goUp, Animation goDown, Animation attackLeft,
         Animation attackRight, Animation attackUp, Animation attackDown, PlayState state) {
        super(x, y, texture, health, damage, goLeft, goRight, goUp, goDown, attackLeft,
                attackRight, attackUp, attackDown, state);
        this.rupees = 0;
        setHP(state.getLinkHealth());
    }


    public Texture getLink() {
        return getCreature();
    }


    public int getRupees() {
        return rupees;
    }

}
