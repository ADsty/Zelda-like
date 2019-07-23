package com.mygdx.game.world;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.states.PlayState;

public class WorldCreator {

    /**
     * Creates all physics object of box2d model
     *
     * @param state is current object of PlayState
     */
    public WorldCreator(PlayState state) {
        World world = state.getWorld();
        TiledMap map = state.getMap();
        BodyDef bodyDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();
        Body body;

        for (MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bodyDef.position.set((rect.getX() + rect.getWidth() / 2), (rect.getY() + rect.getHeight() / 2));
            shape.setAsBox(rect.getWidth() / 2, rect.getHeight() / 2);
            bodyDef.type = BodyDef.BodyType.StaticBody;
            body = world.createBody(bodyDef);
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);
        }
    }

    /**
     * Method checks if creature body is in other physics object of game field
     *
     * @param state     is current object of PlayState
     * @param rectangle is creatures body
     * @return true if creature body is in other physics object
     */
    public boolean checkPhysicsBoxes(PlayState state, Rectangle rectangle) {
        TiledMap map = state.getMap();
        for (MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            if (rect.overlaps(rectangle)) {
                return true;
            }
        }
        return false;
    }
}
