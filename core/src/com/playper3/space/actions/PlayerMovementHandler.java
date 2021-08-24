package com.playper3.space.actions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class PlayerMovement implements Screen {

    private static float FRAME_DURATION = 0.4f;
    private float elapsed_time = 0.0f;
    private int origin_x1, origin_y1, origin_x, origin_y;
    private float PLAYER_SPEED = 200.0f;

    private TextureAtlas textureAtlas;
    private Texture texture;

    private Animation<TextureRegion> def;
    private Animation<TextureRegion> forward;
    private Animation<TextureRegion> backward;
    private Animation<TextureRegion> left;
    private Animation<TextureRegion> right;

    private SpriteBatch spriteBatch;

    @Override
    public void show() {
        spriteBatch = new SpriteBatch();

        texture = new Texture("back1.png");

        textureAtlas = new TextureAtlas("playerSprite.txt");
        Array<TextureAtlas.AtlasRegion> defaultFrames = textureAtlas.findRegions("default");
        Array<TextureAtlas.AtlasRegion> forwardFrames = textureAtlas.findRegions("backward");
        Array<TextureAtlas.AtlasRegion> backwardFrames = textureAtlas.findRegions("forward");
        Array<TextureAtlas.AtlasRegion> rightFrames = textureAtlas.findRegions("right");
        Array<TextureAtlas.AtlasRegion> leftFrames = textureAtlas.findRegions("left");

        def = new Animation<TextureRegion>(FRAME_DURATION, defaultFrames, Animation.PlayMode.LOOP);
        forward = new Animation<TextureRegion>(FRAME_DURATION, forwardFrames, Animation.PlayMode.LOOP);
        backward = new Animation<TextureRegion>(FRAME_DURATION, backwardFrames, Animation.PlayMode.LOOP);
        right = new Animation<TextureRegion>(FRAME_DURATION, rightFrames, Animation.PlayMode.LOOP);
        left = new Animation<TextureRegion>(FRAME_DURATION, leftFrames, Animation.PlayMode.LOOP);

        TextureRegion firstTexture = forwardFrames.first();
        origin_x = (Gdx.graphics.getWidth()  - firstTexture.getRegionWidth())  / 2;
        origin_y = (Gdx.graphics.getHeight() - firstTexture.getRegionHeight())/ 2;

        origin_x1 = (Gdx.graphics.getWidth() - texture.getWidth()) / 2;
        origin_y1 = (Gdx.graphics.getHeight() - texture.getHeight()) / 2;
    }

    @Override
    public void render(float delta) {
        spriteBatch.draw(texture, origin_x1, origin_y1);

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                elapsed_time += Gdx.graphics.getDeltaTime();
                spriteBatch.draw(forward.getKeyFrame(elapsed_time, true), origin_x, origin_y);
                origin_y1 -= Gdx.graphics.getDeltaTime() * PLAYER_SPEED;
            }
            elapsed_time += Gdx.graphics.getDeltaTime();
            spriteBatch.draw(forward.getKeyFrame(elapsed_time, true), origin_x, origin_y);
            origin_y1 -= Gdx.graphics.getDeltaTime() * PLAYER_SPEED;

        }
        else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                elapsed_time += Gdx.graphics.getDeltaTime();
                spriteBatch.draw(backward.getKeyFrame(elapsed_time, true), origin_x, origin_y);
                origin_y1 += Gdx.graphics.getDeltaTime() * PLAYER_SPEED;
            }
            elapsed_time += Gdx.graphics.getDeltaTime();
            spriteBatch.draw(backward.getKeyFrame(elapsed_time, true), origin_x, origin_y);
            origin_y1 += Gdx.graphics.getDeltaTime() * PLAYER_SPEED;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                elapsed_time += Gdx.graphics.getDeltaTime();
                spriteBatch.draw(right.getKeyFrame(elapsed_time, true), origin_x, origin_y);
                origin_x1 -= Gdx.graphics.getDeltaTime() * PLAYER_SPEED;
            }
            elapsed_time += Gdx.graphics.getDeltaTime();
            spriteBatch.draw(right.getKeyFrame(elapsed_time, true), origin_x, origin_y);
            origin_x1 -= Gdx.graphics.getDeltaTime() * PLAYER_SPEED;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                elapsed_time += Gdx.graphics.getDeltaTime();
                spriteBatch.draw(left.getKeyFrame(elapsed_time, true), origin_x, origin_y);
                origin_x1 += Gdx.graphics.getDeltaTime() * PLAYER_SPEED;
            }
            elapsed_time += Gdx.graphics.getDeltaTime();
            spriteBatch.draw(left.getKeyFrame(elapsed_time, true), origin_x, origin_y);
            origin_x1 += Gdx.graphics.getDeltaTime() * PLAYER_SPEED;
        }
        else {
            elapsed_time += Gdx.graphics.getDeltaTime();
            spriteBatch.draw(def.getKeyFrame(elapsed_time), origin_x, origin_y);
        }
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

    }
}
