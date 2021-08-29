package com.playper3.space.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.math.Vector2;
import com.playper3.space.Main;
import com.playper3.space.actions.PlayerMovementHandler;
import com.playper3.space.scenes.HUD;

public class GameScreen1 implements Screen {

    private Main game;
    //Screen variables
    public static final int WIDTH = 1920;
    public static final int HEIGHT = 1080;

    //player variables
    private static float FRAME_DURATION = 0.4f;
    private float elapsed_time = 0.0f;
    private int origin_x1, origin_y1, origin_x, origin_y;
    private float PLAYER_SPEED = 200.0f;

    //game resources
    private TextureAtlas textureAtlas;
    private Texture texture;

    private Animation<TextureRegion> def;
    private Animation<TextureRegion> forward;
    private Animation<TextureRegion> backward;
    private Animation<TextureRegion> left;
    private Animation<TextureRegion> right;

    private OrthographicCamera camera;
    private ExtendViewport viewport;

    private HUD hud;

    //B2D setup
    private World world;
    private Box2DDebugRenderer debugRenderer;

    private static final float STEP_TIME = 1f / 60f;
    private static final int VELOCITY_ITERATIONS = 6;
    private static final int POSITION_ITERATIONS = 2;

    private float accumulator = 0;

    private BodyDef bodyDef;

    public GameScreen1(Main game) {
        this.game = game;

        //camera
        camera = new OrthographicCamera();
        viewport = new ExtendViewport(WIDTH, HEIGHT, camera);

        hud = new HUD(game.spriteBatch);


    }

    @Override
    public void show() {
        //game resources
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
        origin_x = (WIDTH  - firstTexture.getRegionWidth())  / 2;
        origin_y = (HEIGHT - firstTexture.getRegionHeight())/ 2;

        origin_x1 = (WIDTH - texture.getWidth()) / 2;
        origin_y1 = (HEIGHT - texture.getHeight()) / 2;

        //B2D setup
        //Box2D.init();
        world = new World(new Vector2(0, 0), true);
        debugRenderer = new Box2DDebugRenderer();

        bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(0, 10));
    }

    private void stepWorld() {
        //B2D physics
        float delta = Gdx.graphics.getDeltaTime();

        accumulator += Math.min(delta, 0.25f);

        if (accumulator >= STEP_TIME) {
            accumulator -= STEP_TIME;

            world.step(STEP_TIME, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
        }
    }

    @Override
    public void render(float delta) {
        game.spriteBatch.setProjectionMatrix(camera.combined);
        game.spriteBatch.begin();
        game.spriteBatch.draw(texture, origin_x1, origin_y1);

        //player movement
        PlayerMovementHandler.movement();

        if (PlayerMovementHandler.movement() == "forward") {
            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                elapsed_time += Gdx.graphics.getDeltaTime();
                game.spriteBatch.draw(forward.getKeyFrame(elapsed_time, true), origin_x, origin_y);
                origin_y1 -= Gdx.graphics.getDeltaTime() * PLAYER_SPEED;
            }
            elapsed_time += Gdx.graphics.getDeltaTime();
            game.spriteBatch.draw(forward.getKeyFrame(elapsed_time, true), origin_x, origin_y);
            origin_y1 -= Gdx.graphics.getDeltaTime() * PLAYER_SPEED;

        }
        else if (PlayerMovementHandler.movement() == "backward") {
            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                elapsed_time += Gdx.graphics.getDeltaTime();
                game.spriteBatch.draw(backward.getKeyFrame(elapsed_time, true), origin_x, origin_y);
                origin_y1 += Gdx.graphics.getDeltaTime() * PLAYER_SPEED;
            }
            elapsed_time += Gdx.graphics.getDeltaTime();
            game.spriteBatch.draw(backward.getKeyFrame(elapsed_time, true), origin_x, origin_y);
            origin_y1 += Gdx.graphics.getDeltaTime() * PLAYER_SPEED;
        }
        else if (PlayerMovementHandler.movement() == "right") {
            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                elapsed_time += Gdx.graphics.getDeltaTime();
                game.spriteBatch.draw(right.getKeyFrame(elapsed_time, true), origin_x, origin_y);
                origin_x1 -= Gdx.graphics.getDeltaTime() * PLAYER_SPEED;
            }
            elapsed_time += Gdx.graphics.getDeltaTime();
            game.spriteBatch.draw(right.getKeyFrame(elapsed_time, true), origin_x, origin_y);
            origin_x1 -= Gdx.graphics.getDeltaTime() * PLAYER_SPEED;
        }
        else if (PlayerMovementHandler.movement() == "left") {
            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                elapsed_time += Gdx.graphics.getDeltaTime();
                game.spriteBatch.draw(left.getKeyFrame(elapsed_time, true), origin_x, origin_y);
                origin_x1 += Gdx.graphics.getDeltaTime() * PLAYER_SPEED;
            }
            elapsed_time += Gdx.graphics.getDeltaTime();
            game.spriteBatch.draw(left.getKeyFrame(elapsed_time, true), origin_x, origin_y);
            origin_x1 += Gdx.graphics.getDeltaTime() * PLAYER_SPEED;
        }
        else {
            elapsed_time += Gdx.graphics.getDeltaTime();
            game.spriteBatch.draw(def.getKeyFrame(elapsed_time), origin_x, origin_y);
        }

        game.spriteBatch.end();

        //collisions/ physics
        //stepWorld();
        /*
        debugRenderer.render(world, camera.combined);
        Body groundBody = world.createBody(bodyDef);
        PolygonShape groundBox = new PolygonShape();
        groundBox.setAsBox(camera.viewportWidth, 10.0f);
        groundBody.createFixture(groundBox, 0.0f);
*/
        game.spriteBatch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
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
        textureAtlas.dispose();
        texture.dispose();
        world.dispose();
    }
}
