package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;

public class MenuState extends State {

    private Texture background;

    /**
     * It is start screen of game which checks for some taps on screen and then changes to game screen
     */
    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("Zelda-Menu.jpg");
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            gsm.set(new PlayState(gsm, 3, 3, 999, 100, 100, false, 0, 0));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            gsm.set(new PlayState(gsm, 3, 3, 999, 100, 100, false, 0, 0));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        System.out.println("Menu disposed");
    }
}
