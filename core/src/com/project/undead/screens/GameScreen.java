package com.project.undead.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.TimeUtils;
import com.project.undead.*;
import com.project.undead.entities.Dummy;
import com.project.undead.entities.Enemy;
import com.project.undead.entities.Melee;
import com.project.undead.entities.Player;

public class GameScreen implements Screen {

    private OrthographicCamera camera;
    private OrthographicCamera hudCamera;
    // Sprite sprite;
    private Texture texture;
    private Box2DDebugRenderer debugRenderer;
    private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;
    private TileMap tileMap;
    ScreenSize screen = new ScreenSize();


    // Display Size
    private int displayW;
    private int displayH;
    Control control;
    Melee melee;
    Player player;

    GameClass game;

    // Elapsed time
    long startTime = TimeUtils.millis();

    // Public Variables
    public GameScreen(GameClass game) {
        this.game = game;
    }


    @Override
    public void show() {

        Media.loadMedia();
        // Initialize our world without gravity thus .0f, .0f
        // Initialize our debug renderer
        debugRenderer = new Box2DDebugRenderer();
        game.batch = new SpriteBatch();
        tileMap = new TileMap();
        orthogonalTiledMapRenderer = tileMap.setupMap();
        //img = new Texture("badlogic.jpg");




        // camera
        displayW= Gdx.graphics.getWidth();
        displayH = Gdx.graphics.getHeight();

        int h = displayH / 2; //266; //(int) (displayH/ Math.floor(displayH / 160));
        int w = displayW / 2; //200; //(int) (displayW / (displayH / Math.floor(displayH / 160)));

        // Camera stuffs
        camera = new OrthographicCamera(w, h);
        camera.zoom = .3f;

        hudCamera = new OrthographicCamera(w, h);
        hudCamera.zoom = 2f;

        // Keyboard input
        control = new Control(displayW, displayH, camera);
        Gdx.input.setInputProcessor(control);

        Media.loadMedia();

        // HUH? ANO TO?
        melee  = new Melee(1, -1, 7);
        player = new Player(tileMap.getCenterTile());

        tileMap.addEntities();
    }

    @Override
    public void render(float v) {

        // Use to reset graphics and fix some graphical errors...
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        TileMap.world.step(Gdx.graphics.getDeltaTime(), 8, 3);

        float endTime = TimeUtils.timeSinceMillis(startTime) / 1000;

        if (endTime > 5) {
            tileMap.addEntities();
            startTime = TimeUtils.millis();
        }


        player.update(control, TileMap.world);
        for (Enemy e : tileMap.entities) {
            e.update(player);
        }
        camera.position.lerp(player.pos, .1f);

        // Di permanent???
        camera.update();

        // TiledMap Render
        orthogonalTiledMapRenderer.setView(camera);
        orthogonalTiledMapRenderer.render();

        // Draw Game Texture
        game.batch.setProjectionMatrix(camera.combined);
//		batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        game.batch.begin();

        if (player.HITPOINTS < 1) {
            this.dispose();
            hudCamera.position.x = screen.SCREENWIDTH / 2f;
            hudCamera.position.y = screen.SCREENHEIGHT / 2f;
            hudCamera.update();
            game.batch.setProjectionMatrix(hudCamera.combined);
            game.setScreen(new GameOver(game));
        }

        player.draw(game.batch);
        for (Enemy e : tileMap.entities) {
            e.draw(game.batch);
        }

        game.batch.end();

        // Clears entity or bullet in world
        player.clearAmmo(TileMap.world);
        tileMap.clearDeadEnemy();

        tileMap.tick(camera, control);

        // Debugging Purposes

    }

    @Override
    public void resize(int i, int i1) {

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