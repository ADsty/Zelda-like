package com.mygdx.game.creatures;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

class Animation {

    private Array<Texture> frames;
    private float maxFrameTime;
    private float currentFrameTime;
    private int frameCount;
    private int frame;

    /**
     * Creates all animations in game
     *
     * @param texture1   first texture of animation
     * @param texture2   second texture of animation
     * @param frameCount counter for textures
     * @param cycleTime  time of aniamtion
     */
    Animation(Texture texture1, Texture texture2, int frameCount, float cycleTime) {
        frames = new Array<Texture>();
        frames.add(texture1);
        frames.add(texture2);
        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount;
        frame = 0;
    }

    /**
     * Method updates current texture of animation
     *
     * @param dt is delta time
     */
    void update(float dt) {
        currentFrameTime += dt;
        if (currentFrameTime > maxFrameTime) {
            frame++;
            currentFrameTime = 0;
        }
        if (frame >= frameCount) {
            frame = 0;
        }
    }

    /**
     * Method makes texture for player when he doesn't move
     *
     * @param direction is direction of player's movement
     * @return stay texture
     */
    static Texture stayTexture(int direction) {
        if (direction == 1) {
            return new Texture("link images/link7.png");
        } else if (direction == 2) {
            return new Texture("link images/link4.png");
        } else if (direction == 4) {
            return new Texture("link images/link3.png");
        } else {
            return new Texture("link images/link1.png");
        }
    }

    Texture getTexture() {
        return frames.get(frame);
    }

    void dispose() {
        frames.get(0).dispose();
        frames.get(1).dispose();
    }

}
