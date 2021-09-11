package com.playper3.space.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.playper3.space.screens.SetupVars;
import com.playper3.space.sprites.logic.PlayerLogic;

public class HUD implements Disposable {

    public Stage stage;
    private Viewport viewport;

    private BitmapFont font;
    private Label.LabelStyle labelStyle;

    private Skin skin;

    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;

    private PlayerLogic logic;

    private int hunger;
    private int hydration;
    private int health;
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
        hunger = 10;
        hydration = 10;
        health = 100;
        room = "Main";

        logic = new PlayerLogic();

        viewport = new ExtendViewport(SetupVars.WIDTH, SetupVars.HEIGHT, new OrthographicCamera());

        generator = new FreeTypeFontGenerator(Gdx.files.internal("upheaval/upheavtt.ttf"));
        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = 10;

        font = generator.generateFont(fontParameter);
        labelStyle = new Label.LabelStyle(font, Color.BLACK);

        skin = new Skin(Gdx.files.internal("Shade_UI_Skin/shadeui/uiskin.json"));

        stage = new Stage(viewport, spriteBatch);

        Table table = new Table();
        table.top().left();
        table.setFillParent(true);

        Table hudTable = new Table();
        hudTable.setSize(250,350);

        healthL = new Label (" HEALTH ", labelStyle);
        healthLabel = new Label(String.format("%03d", health), labelStyle);
        hungerL = new Label (" HUNGER ", labelStyle);
        hungerLabel = new Label(String.format("%02d", hunger), labelStyle);
        hydrationL = new Label (" HYDRATION ", labelStyle);
        hydrationLabel = new Label(String.format("%02d", hydration), labelStyle);
        roomL = new Label (" ROOM ", labelStyle);
        roomLabel = new Label (room, labelStyle);

        hudTable.setBackground(skin.getDrawable("dialogDim"));
        hudTable.add(healthL).padTop(10);
        hudTable.row();
        hudTable.add(healthLabel);
        hudTable.row();
        hudTable.add(hungerL).padTop(10);
        hudTable.row();
        hudTable.add(hungerLabel);
        hudTable.row();
        hudTable.add(hydrationL).padTop(10);
        hudTable.row();
        hudTable.add(hydrationLabel);
        hudTable.row();
        hudTable.add(roomL).padTop(10);
        hudTable.row();
        hudTable.add(roomLabel);

        table.add(hudTable);

        stage.addActor(table);
    }

    public void update(float dt){
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            hungerLabel.setText(String.format("%02d", logic.isMoving(true)));
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            hungerLabel.setText(String.format("%02d", logic.isMoving(true)));
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            hungerLabel.setText(String.format("%02d", logic.isMoving(true)));
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            hungerLabel.setText(String.format("%02d", logic.isMoving(true)));
        }
        else{
            hungerLabel.setText(String.format("%02d", logic.isMoving(false)));
        }

//        hydrationLabel.setText(String.format("%02d", hydration));
//        healthLabel.setText(String.format("%03d", health));
//        roomLabel.setText(room);
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
