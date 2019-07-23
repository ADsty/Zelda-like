package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;

public class GameOverState extends State {

    private Texture gameover;

    /**
     * It is black screen which creates after the game ends and which waites for some taps
     * to create new game
     */
    GameOverState(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        gameover = new Texture("gameover.jpg");
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            gsm.set(new MenuState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(gameover, camera.position.x - gameover.getWidth() / 2, camera.position.y - gameover.getHeight() / 2);
        sb.end();
    }

    @Override
    public void dispose() {
        gameover.dispose();
        System.out.println("GameOver Disposed");
    }
}
