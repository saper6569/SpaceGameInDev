package com.playper3.space.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.playper3.space.loggers.DebugLogger;
import com.playper3.space.screens.GameScreen1;
import com.playper3.space.screens.SetupVars;
import com.playper3.space.sprites.logic.PlayerLogic;

public class Player extends Sprite {

    private final float PLAYER_SPEED = 0.3f;
    private float elapsed_time = 0.0f;
    private final static float FRAME_DURATION = 0.55f;

    private final TextureRegion def;
    private final TextureAtlas textureAtlas;
    private final Animation<TextureRegion> forward;
    private final Animation<TextureRegion> backward;
    private final Animation<TextureRegion> left;
    private final Animation<TextureRegion> right;

    public World world;
    public Body b2dBody;
    private PlayerLogic logic;
    private DebugLogger logger;

    public Player(World world, GameScreen1 screen) {
        this.world = world;
        definePlayer();

        textureAtlas = new TextureAtlas("playerSprite.txt");

        def = new TextureRegion(textureAtlas.findRegion("default"));
        setBounds(0, 0, 32 / SetupVars.PPM, 32 / SetupVars.PPM);
        setRegion(def);

        Array<TextureAtlas.AtlasRegion> forwardFrames = textureAtlas.findRegions("backward");
        Array<TextureAtlas.AtlasRegion> backwardFrames = textureAtlas.findRegions("forward");
        Array<TextureAtlas.AtlasRegion> rightFrames = textureAtlas.findRegions("right");
        Array<TextureAtlas.AtlasRegion> leftFrames = textureAtlas.findRegions("left");

        forward = new Animation<TextureRegion>(FRAME_DURATION, forwardFrames, Animation.PlayMode.LOOP);
        backward = new Animation<TextureRegion>(FRAME_DURATION, backwardFrames, Animation.PlayMode.LOOP);
        left = new Animation<TextureRegion>(FRAME_DURATION, rightFrames, Animation.PlayMode.LOOP);
        right = new Animation<TextureRegion>(FRAME_DURATION, leftFrames, Animation.PlayMode.LOOP);

        logic = new PlayerLogic();
        logger = new DebugLogger();
    }

    public void definePlayer() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(871 / SetupVars.PPM, 1415 / SetupVars.PPM);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        b2dBody = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(8 / SetupVars.PPM, 12 / SetupVars.PPM);

        fixtureDef.shape = polygonShape;
        b2dBody.createFixture(fixtureDef);
    }

    public void updateSprite() {
        setPosition(b2dBody.getPosition().x - getWidth() / 2, b2dBody.getPosition().y - getHeight() / 2);
        TextureRegion frame = getFrame();
        if (frame != null) {
            setRegion(frame);
        }
    }

    public void playerMovement(float dt) {
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            b2dBody.setLinearVelocity(0,-PLAYER_SPEED);
            elapsed_time += Gdx.graphics.getDeltaTime();
            logic.isMoving(true, dt);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            b2dBody.setLinearVelocity(0,PLAYER_SPEED);
            elapsed_time += Gdx.graphics.getDeltaTime();
            logic.isMoving(true, elapsed_time);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            b2dBody.setLinearVelocity(-PLAYER_SPEED,0);
            elapsed_time += Gdx.graphics.getDeltaTime();
            logic.isMoving(true, elapsed_time);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            b2dBody.setLinearVelocity(PLAYER_SPEED,0);
            elapsed_time += Gdx.graphics.getDeltaTime();
            logic.isMoving(true, elapsed_time);
        }
        else{
            b2dBody.setLinearVelocity(0, 0);
            elapsed_time += Gdx.graphics.getDeltaTime();
            logic.isMoving(false, elapsed_time);
        }
    }

    public TextureRegion getFrame () {
        TextureRegion region = null;

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            elapsed_time += Gdx.graphics.getDeltaTime();
            region = forward.getKeyFrame(elapsed_time, true);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            elapsed_time += Gdx.graphics.getDeltaTime();
            region = backward.getKeyFrame(elapsed_time, true);
        }
        else if (Gdx.input.isKeyPressed((Input.Keys.A))) {
            elapsed_time += Gdx.graphics.getDeltaTime();
            region = right.getKeyFrame(elapsed_time, true);
        }
        else if (Gdx.input.isKeyPressed((Input.Keys.D))) {
            elapsed_time += Gdx.graphics.getDeltaTime();
            region = left.getKeyFrame(elapsed_time, true);
        }
        else {
            elapsed_time += Gdx.graphics.getDeltaTime();
            region = def;
        }
        return region;
    
    }
}
