package com.playper3.space.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.playper3.space.Main;

public class MainMenu implements Screen {

    //game resources
    private Stage stage;
    private Music menuSong;
    private Texture playTexture;
    private TextureRegion playTextureRegion;
    private TextureRegionDrawable playDrawable;
    private ImageButton play;

    //frame counter
    private int count;
    private int countSec;

    //camera
    private OrthographicCamera camera;
    private ExtendViewport viewport;

    private Main game;

    @Override
    public void show() {
        //stage for game resources
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        //resource instances
        playTexture = new Texture("testButton.png");
        menuSong = Gdx.audio.newMusic(Gdx.files.internal("C418 - 0x10c.mp3"));

        camera = new OrthographicCamera();
        viewport = new ExtendViewport(SetupVars.WIDTH, SetupVars.HEIGHT, camera);

        int origin_x = (Gdx.graphics.getWidth() - playTexture.getWidth()) / 2;
        int origin_y = (Gdx.graphics.getHeight() - playTexture.getHeight()) / 2;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        playTextureRegion = new TextureRegion(playTexture);
        playDrawable = new TextureRegionDrawable(playTextureRegion);
        play = new ImageButton(playDrawable);
        play.setPosition(origin_x, origin_y);

        //play button listener switches screen to first game screen
        play.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

                ((Game)Gdx.app.getApplicationListener()).setScreen(new GameScreen1(game));
                if (menuSong.isPlaying()) {
                    menuSong.stop();
                }
            }
        });

        stage.addActor(play);

        count = 0;
        countSec = 0;
    }
    @Override
    public void render(float delta) {
        //screen render functions
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();

        //music
        if (count == 60) {
            countSec++;
            count = 0;
        }
        else if (countSec == 30) {
            menuSong.play();
        }
        else if (countSec % 520 == 0) {
            menuSong.play();
        }
        else if (countSec == 2147483645) {
            count = 0;
            countSec = 0;
        }

        count ++;
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        playTexture.dispose();
        menuSong.dispose();

    }
}
