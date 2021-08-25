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

public class MainMenu implements Screen {

    private Stage stage;
    private Music menuSong;
    private Texture playTexture;
    private TextureRegion playTextureRegion;
    private TextureRegionDrawable playDrawable;
    private ImageButton play;

    private int count;
    private int countSec;

    private OrthographicCamera camera;
    private ExtendViewport viewport;

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        playTexture = new Texture("testButton.png");
        menuSong = Gdx.audio.newMusic(Gdx.files.internal("C418 - 0x10c.mp3"));

        int origin_x = (Gdx.graphics.getWidth() - playTexture.getWidth()) / 2;
        int origin_y = (Gdx.graphics.getHeight() - playTexture.getHeight()) / 2;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        camera = new OrthographicCamera();
        viewport = new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);

        playTextureRegion = new TextureRegion(playTexture);
        playDrawable = new TextureRegionDrawable(playTextureRegion);
        play = new ImageButton(playDrawable);
        play.setPosition(origin_x, origin_y);

        play.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new GameScreen1());
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
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
        if (count == 60) {
            countSec++;
        }
        else if (countSec == 30) {
            menuSong.play();
        }
        else if (countSec % 520 == 0) {
            menuSong.play();
        }

        count ++;
    }

    @Override
    public void resize(int width, int height) {

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
