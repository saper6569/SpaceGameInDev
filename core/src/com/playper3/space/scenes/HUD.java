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

public class HUD {

    public Stage stage;
    private Viewport viewport;

    private int hunger;
    private int hydration;
    private float health;
    private String room;
    public static int PLAYER_X = 0;
    public static int PLAYER_Y = 0;

    Label healthL;
    Label healthLabel;
    Label hungerL;
    Label hungerLabel;
    Label hydrationL;
    Label hydrationLabel;
    Label roomL;
    Label roomLabel;
    Label locationL;
    Label location;

    public HUD(SpriteBatch spriteBatch) {

        viewport = new ExtendViewport(GameScreen1.WIDTH, GameScreen1.HEIGHT, new OrthographicCamera());

        hunger = 10;
        hydration = 10;
        health = 20.0f;
        room = "!!!!!!!!";


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
        locationL = new Label ("LOCATION", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        location = new Label(String.format("%03d", PLAYER_X) + "," + String.format("%03d", PLAYER_Y), new Label.LabelStyle(new BitmapFont(), Color.BLACK));

        table.add(healthL).expandY().padTop(10);
        table.add(hungerL).expandY().padTop(10);
        table.add(hydrationL).expandY().padTop(10);
        table.add(roomL).expandY().padTop(10);
        table.add(location).expandY().padTop(10);

        table.row();
        table.add(healthLabel).expandY();
        table.add(hungerLabel).expandY();
        table.add(hydrationLabel).expandY();
        table.add(roomLabel).expandY();
        table.add(location).expandY();

        stage.addActor(table);
    }
}
