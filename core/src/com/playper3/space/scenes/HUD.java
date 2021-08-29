package com.playper3.space.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.playper3.space.screens.GameScreen1;
import com.playper3.space.screens.SetupVars;

public class HUD {

    public Stage stage;
    private Viewport viewport;

    private int hunger;
    private int hydration;
    private float health;
    private String room;
    Label healthL;
    Label healthLabel;
    Label hungerL;
    Label hungerLabel;
    Label hydrationL;
    Label hydrationLabel;
    Label roomL;
    Label roomLabel;

    public HUD(SpriteBatch spriteBatch) {

        viewport = new ExtendViewport(SetupVars.WIDTH, SetupVars.HEIGHT, new OrthographicCamera());

        hunger = 10;
        hydration = 10;
        health = 20.0f;
        room = "Main";


        stage = new Stage(viewport, spriteBatch);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        healthL = new Label ("HEALTH", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        healthLabel = new Label(String.format("%03f", health), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        hungerL = new Label ("HUNGER", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        hungerLabel = new Label(String.format("%02d", hunger), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        hydrationL = new Label ("HYDRATION", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        hydrationLabel = new Label(String.format("%02d", hydration), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        roomL = new Label ("ROOM", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        roomLabel = new Label (room, new Label.LabelStyle(new BitmapFont(), Color.BLACK));

        table.add(healthL).expandX().padTop(10);
        table.add(hungerL).expandX().padTop(10);
        table.add(hydrationL).expandX().padTop(10);
        table.add(roomL).expandX().padTop(10);

        table.row();
        table.add(healthLabel).expandX();
        table.add(hungerLabel).expandX();
        table.add(hydrationLabel).expandX();
        table.add(roomLabel).expandX();

        stage.addActor(table);
    }
}
