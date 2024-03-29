package com.project.undead;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.project.undead.collision.TileMap;
import com.project.undead.entities.Dummy;
import com.project.undead.entities.Entity;
import com.project.undead.entities.Player;

public class gameclass extends ApplicationAdapter {
	private SpriteBatch batch;
	private OrthographicCamera camera;
	// Sprite sprite;
	private Texture texture;
	private World world;
	private Box2DDebugRenderer debugRenderer;
	private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;
	private TileMap tileMap;


	// Display Size
	private int displayW;
	private int displayH;
	Control control;
	Player player;

	
	@Override
	public void create () {
		Media.loadMedia();
		// Initialize our world without gravity thus .0f, .0f
		// Initialize our debug renderer
		world = new World(new Vector2(.0f, .0f), true);
		debugRenderer = new Box2DDebugRenderer();
		batch = new SpriteBatch();
		tileMap = new TileMap();
		orthogonalTiledMapRenderer = tileMap.setupMap();
		//img = new Texture("badlogic.jpg");




		// camera
		displayW= Gdx.graphics.getWidth();
		displayH = Gdx.graphics.getHeight();

		int h = displayH / 2; //266; //(int) (displayH/ Math.floor(displayH / 160));
		int w = displayW / 2; //200; //(int) (displayW / (displayH / Math.floor(displayH / 160)));

		camera = new OrthographicCamera(w, h);
		camera.zoom = .4f;

		// Keyboard input
		control = new Control(displayW, displayH, camera);
		Gdx.input.setInputProcessor(control);

		Media.loadMedia();

		// HUH? ANO TO?
		player = new Player(tileMap.getCenterTile());
		tileMap.addEntities();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


		player.update(control);
		for (Dummy e : tileMap.entities) {
			e.update(player);
		}
		camera.position.lerp(player.pos, .1f);

		// Di permanent???
		camera.update();

		// TiledMap Render
		orthogonalTiledMapRenderer.setView(camera);
		orthogonalTiledMapRenderer.render();

		// Draw Game Texture
		batch.setProjectionMatrix(camera.combined);
//		batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

		batch.begin();

		player.draw(batch);
		for (Dummy e : tileMap.entities) {
			e.draw(batch);
		}

		batch.end();

		tileMap.tick(camera, control);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
