package com.playper3.space.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.math.Vector2;
import com.playper3.space.Main;
import com.playper3.space.scenes.HUD;
import com.playper3.space.sprites.Player;

public class GameScreen1 implements Screen {

    private Main game;

    private Player player;

    //game resources
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;



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
        viewport = new ExtendViewport(SetupVars.WIDTH, SetupVars.HEIGHT, camera);

        hud = new HUD(game.spriteBatch);

        //game resources
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("spaceShip.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map);

        camera.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);

        //B2D setup
        world = new World(new Vector2(0, 0), true);
        debugRenderer = new Box2DDebugRenderer();

        bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(0, 10));
        PolygonShape shape = new PolygonShape();
        FixtureDef fDef = new FixtureDef();
        Body body;

        //walls
        for (MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set(rectangle.getX() + rectangle.getWidth() / 2, rectangle.getY() + rectangle.getHeight() /  2);

            body = world.createBody(bodyDef);
            shape.setAsBox(rectangle.getWidth() / 2, rectangle.getHeight() / 2);
            fDef.shape = shape;
            body.createFixture(fDef);
        }

        player = new Player(world, camera);

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
    public void show() {
    }

    public void update(float dt){
        camera.update();

        playerMovement(dt);

        camera.position.x = player.b2dBody.getPosition().x;
        camera.position.y = player.b2dBody.getPosition().y;

        stepWorld();
    }

    public void playerMovement(float dt) {
        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
            if (Gdx.input.isKeyPressed(Input.Keys.S) && player.b2dBody.getLinearVelocity().y <= 2 * SetupVars.PPM) {
                player.b2dBody.applyLinearImpulse(new Vector2(0, -4f * SetupVars.PPM), player.b2dBody.getWorldCenter(), true);
            }
            else if (Gdx.input.isKeyPressed(Input.Keys.W) && player.b2dBody.getLinearVelocity().y <= 2 * SetupVars.PPM) {
                player.b2dBody.applyLinearImpulse(new Vector2(0f, 4f * SetupVars.PPM), player.b2dBody.getWorldCenter(), true);
            }
            else if (Gdx.input.isKeyPressed(Input.Keys.A) && player.b2dBody.getLinearVelocity().x <= 2 * SetupVars.PPM) {
                player.b2dBody.applyLinearImpulse(new Vector2(-4f * SetupVars.PPM, 0), player.b2dBody.getWorldCenter(), true);
            }
            else if (Gdx.input.isKeyPressed(Input.Keys.D) && player.b2dBody.getLinearVelocity().x <= 2 * SetupVars.PPM) {
                player.b2dBody.applyLinearImpulse(new Vector2(4f * SetupVars.PPM, 0), player.b2dBody.getWorldCenter(), true);
            }
            else {

            }
        }

        else if (Gdx.input.isKeyPressed(Input.Keys.S)) {

        }

        else if (Gdx.input.isKeyPressed(Input.Keys.W)) {

        }

        else if (Gdx.input.isKeyPressed((Input.Keys.A))) {

        }
        else if (Gdx.input.isKeyPressed((Input.Keys.D))) {

        }
        else {

        }
    }


    @Override
    public void render(float delta) {
        update(delta);

        //Clear the game screen with Black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mapRenderer.setView(camera);
        mapRenderer.render();
        debugRenderer.render(world, camera.combined);

        game.spriteBatch.setProjectionMatrix(camera.combined);
        game.spriteBatch.begin();

        //player movement


        game.spriteBatch.end();

        game.spriteBatch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

        //collisions/ physics
        stepWorld();
        /*

        Body groundBody = world.createBody(bodyDef);
        PolygonShape groundBox = new PolygonShape();
        groundBox.setAsBox(camera.viewportWidth, 10.0f);
        groundBody.createFixture(groundBox, 0.0f);
*/

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
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
        world.dispose();
        map.dispose();
        mapRenderer.dispose();
        world.dispose();
        hud.dispose();
    }
}
