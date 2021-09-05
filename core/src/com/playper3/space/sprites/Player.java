package com.playper3.space.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.playper3.space.screens.SetupVars;

public class Player extends Sprite {

    private final float PLAYER_SPEED = 0.3f;
    private final float PLAYER_SPEED_SPRINT = 0.5f;
    private float elapsed_time = 0.0f;
    private int origin_x, origin_y;
    private final static float FRAME_DURATION = 0.4f;
    private final static float SPRINT_FRAME_DURATION = 0.2f;

    private Animation<TextureRegion> def;
    private Animation<TextureRegion> forward;
    private Animation<TextureRegion> backward;
    private Animation<TextureRegion> left;
    private Animation<TextureRegion> right;

    private Animation<TextureRegion> forwardSprint;
    private Animation<TextureRegion> backwardSprint;
    private Animation<TextureRegion> leftSprint;
    private Animation<TextureRegion> rightSprint;
    private TextureAtlas textureAtlas;

    public World world;
    public Body b2dBody;


    public void create() {
        textureAtlas = new TextureAtlas("playerSprite.png.txt");
        Array<TextureAtlas.AtlasRegion> defaultFrames = textureAtlas.findRegions("default");
        Array<TextureAtlas.AtlasRegion> forwardFrames = textureAtlas.findRegions("backward");
        Array<TextureAtlas.AtlasRegion> backwardFrames = textureAtlas.findRegions("forward");
        Array<TextureAtlas.AtlasRegion> rightFrames = textureAtlas.findRegions("right");
        Array<TextureAtlas.AtlasRegion> leftFrames = textureAtlas.findRegions("left");

        def = new Animation<TextureRegion>(FRAME_DURATION, defaultFrames, Animation.PlayMode.LOOP);
        forward = new Animation<TextureRegion>(FRAME_DURATION, forwardFrames, Animation.PlayMode.LOOP);
        backward = new Animation<TextureRegion>(FRAME_DURATION, backwardFrames, Animation.PlayMode.LOOP);
        left = new Animation<TextureRegion>(FRAME_DURATION, rightFrames, Animation.PlayMode.LOOP);
        right = new Animation<TextureRegion>(FRAME_DURATION, leftFrames, Animation.PlayMode.LOOP);

        forwardSprint = new Animation<TextureRegion>(SPRINT_FRAME_DURATION, forwardFrames, Animation.PlayMode.LOOP);
        backwardSprint = new Animation<TextureRegion>(SPRINT_FRAME_DURATION, backwardFrames, Animation.PlayMode.LOOP);
        leftSprint = new Animation<TextureRegion>(SPRINT_FRAME_DURATION, rightFrames, Animation.PlayMode.LOOP);
        rightSprint = new Animation<TextureRegion>(SPRINT_FRAME_DURATION, leftFrames, Animation.PlayMode.LOOP);

        TextureRegion firstTexture = forwardFrames.first();
        origin_x = firstTexture.getRegionWidth()  / 2;
        origin_y = firstTexture.getRegionHeight() / 2;
    }

    public Player(World world) {
        this.world = world;
        definePlayer();
    }

    public void definePlayer() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(871 / SetupVars.PPM, 1415 / SetupVars.PPM);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        b2dBody = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(11 / SetupVars.PPM, 13 / SetupVars.PPM);

        fixtureDef.shape = polygonShape;
        b2dBody.createFixture(fixtureDef);
    }
/*
    public void playerMovement (SpriteBatch spriteBatch, Camera camera) {
        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                elapsed_time += Gdx.graphics.getDeltaTime();
                camera.position.y -= Gdx.graphics.getDeltaTime() * PLAYER_SPEED_SPRINT;
                spriteBatch.draw(forwardSprint.getKeyFrame(elapsed_time, true), camera.position.x - origin_x, camera.position.y - origin_y);
            }
            else if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                elapsed_time += Gdx.graphics.getDeltaTime();
                camera.position.y += Gdx.graphics.getDeltaTime() * PLAYER_SPEED_SPRINT;
                spriteBatch.draw(backwardSprint.getKeyFrame(elapsed_time, true), camera.position.x - origin_x, camera.position.y - origin_y);
            }
            else if (Gdx.input.isKeyPressed((Input.Keys.A))) {
                elapsed_time += Gdx.graphics.getDeltaTime();
                camera.position.x -= Gdx.graphics.getDeltaTime() * PLAYER_SPEED_SPRINT;
                spriteBatch.draw(rightSprint.getKeyFrame(elapsed_time, true), camera.position.x - origin_x, camera.position.y - origin_y);
            }
            else if (Gdx.input.isKeyPressed((Input.Keys.D))) {
                elapsed_time += Gdx.graphics.getDeltaTime();
                camera.position.x += Gdx.graphics.getDeltaTime() * PLAYER_SPEED_SPRINT;
                spriteBatch.draw(leftSprint.getKeyFrame(elapsed_time, true), camera.position.x - origin_x, camera.position.y - origin_y);
            }
            else {
                elapsed_time += Gdx.graphics.getDeltaTime();
                spriteBatch.draw(def.getKeyFrame(elapsed_time), camera.position.x - origin_x, camera.position.y - origin_y);
            }
        }

        else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            elapsed_time += Gdx.graphics.getDeltaTime();
            spriteBatch.draw(forward.getKeyFrame(elapsed_time, true), camera.position.x - origin_x, camera.position.y - origin_y);
            camera.position.y -= Gdx.graphics.getDeltaTime() * PLAYER_SPEED;
        }

        else if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            elapsed_time += Gdx.graphics.getDeltaTime();
            spriteBatch.draw(backward.getKeyFrame(elapsed_time, true), camera.position.x - origin_x, camera.position.y - origin_y);
            camera.position.y += Gdx.graphics.getDeltaTime() * PLAYER_SPEED;
        }

        else if (Gdx.input.isKeyPressed((Input.Keys.A))) {
            elapsed_time += Gdx.graphics.getDeltaTime();
            spriteBatch.draw(right.getKeyFrame(elapsed_time, true), camera.position.x - origin_x, camera.position.y - origin_y);
            camera.position.x -= Gdx.graphics.getDeltaTime() * PLAYER_SPEED;
        }
        else if (Gdx.input.isKeyPressed((Input.Keys.D))) {
            elapsed_time += Gdx.graphics.getDeltaTime();
            spriteBatch.draw(left.getKeyFrame(elapsed_time, true), camera.position.x - origin_x, camera.position.y - origin_y);
            camera.position.x += Gdx.graphics.getDeltaTime() * PLAYER_SPEED;
        }
        else {
            elapsed_time += Gdx.graphics.getDeltaTime();
            spriteBatch.draw(def.getKeyFrame(elapsed_time), camera.position.x - origin_x, camera.position.y - origin_y);
        }
    }
    */
    public void playerMovement() {
        if (Gdx.input.isKeyPressed(Input.Keys.S) && b2dBody.getLinearVelocity().y >= -0.8) {
            b2dBody.setLinearVelocity(0,-PLAYER_SPEED);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.W) && b2dBody.getLinearVelocity().y <= 0.8) {
            b2dBody.setLinearVelocity(0,PLAYER_SPEED);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.A) && b2dBody.getLinearVelocity().x >= -0.8) {
            b2dBody.setLinearVelocity(-PLAYER_SPEED,0);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.D) && b2dBody.getLinearVelocity().x <= 0.8) {
            b2dBody.setLinearVelocity(PLAYER_SPEED,0);
        }
        else {
            b2dBody.setLinearVelocity(0,0);
        }
/*
        else if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
            if (Gdx.input.isKeyPressed(Input.Keys.S) && b2dBody.getLinearVelocity().y >= -1) {
                b2dBody.applyLinearImpulse(new Vector2(0, -0.5f), b2dBody.getWorldCenter(), true);
            }
            else if (Gdx.input.isKeyPressed(Input.Keys.W) && b2dBody.getLinearVelocity().y <= 1) {
                b2dBody.applyLinearImpulse(new Vector2(0f, 0.5f), b2dBody.getWorldCenter(), true);
            }
            else if (Gdx.input.isKeyPressed(Input.Keys.A) && b2dBody.getLinearVelocity().x >= -1) {
                b2dBody.applyLinearImpulse(new Vector2(-0.5f, 0), b2dBody.getWorldCenter(), true);
            }
            else if (Gdx.input.isKeyPressed(Input.Keys.D) && b2dBody.getLinearVelocity().x <= 1) {
                b2dBody.applyLinearImpulse(new Vector2(0.5f, 0), b2dBody.getWorldCenter(), true);
            }
        }
        else {
            //b2dBody.setLinearVelocity(new Vector2(0f, 0f));
        }

 */
    }
}
