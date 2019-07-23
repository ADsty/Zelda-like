package com.mygdx.game.states;

import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;

public class Hud implements Disposable {
    Stage stage;
    private Label health;
    private Label rupees;
    private PlayState state;

    /**
     * It is hud for game screen which displays player's health and money
     */
    Hud(SpriteBatch sb, PlayState state) {
        this.state = state;
        Viewport viewport = new FitViewport(MyGdxGame.WIDTH, MyGdxGame.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);
        Table table = new Table();
        table.top();
        table.setFillParent(true);
        Label healthLabel = new Label("Health", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        Label rupeesLabel = new Label("Rupees", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        health = new Label(String.format("%02d", state.getLink().getHealth()), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        rupees = new Label(String.format("%03d", state.getLink().getRupees()), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        table.add(healthLabel).expandX().padTop(10);
        table.add(rupeesLabel).expandX().padTop(10);
        table.row();
        table.add(health).expandX();
        table.add(rupees).expandX();
        stage.addActor(table);

    }

    /**
     * Method updates hud values of health and money of player
     */
    void update() {
        health.setText(state.getLink().getHealth());
        rupees.setText(state.getLink().getRupees());
    }

    /**
     * Method disposes stage used by hud
     */
    @Override
    public void dispose() {
        stage.dispose();
    }

}
