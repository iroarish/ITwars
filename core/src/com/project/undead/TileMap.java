package com.project.undead;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.physics.box2d.*;
import com.project.undead.collision.CollisionHelper;
import com.project.undead.collision.ContactChecker;
import com.project.undead.collision.MaskHelper;
import com.project.undead.entities.*;
import com.project.undead.entities.ammo.Ammo;
import com.project.undead.screens.GameScreen;

import java.util.ArrayList;
import java.util.Iterator;

public class TileMap {
    private TiledMap tiledMap;
    private TiledMapTileLayer tileLayer;
    public static World world;
    private final Box2DDebugRenderer debugRenderer;
    public MaskHelper maskHelper;

    // Enemy Thingies
    public ArrayList<Enemy> entities = new ArrayList<Enemy>();
    Dummy dummy;
    Trojan trojan;
    Worm worm;
    Virus virus;


    // Map
    public static int map;
    public static int ptc = 1;
    public static int mac = 2;

    // Time Elapsed
    float beginTime = Gdx.graphics.getDeltaTime();

    public TileMap() {

        setupMap();

        world = new World(new Vector2(.0f, .0f), true);
        TileMap.world.setContactListener(new ContactChecker());
        debugRenderer = new Box2DDebugRenderer();

        getCollision();
    }

    private void getCollision() {
        maskHelper = new MaskHelper();

        MapObjects collision = tiledMap.getLayers().get("Collision").getObjects();
        for (int i = 0; i < collision.getCount(); i++) {
            MapObject mapObject = collision.get(i);

            if (mapObject instanceof RectangleMapObject) {
                RectangleMapObject rectangleMapObject = (RectangleMapObject) mapObject;
                Rectangle rectangle = rectangleMapObject.getRectangle();

                CollisionHelper.createBody(world, rectangle.getWidth(), rectangle.getHeight(), new Vector3(rectangle.getX(), rectangle.getY(), 0), BodyDef.BodyType.StaticBody, maskHelper.SCENE, maskHelper.SCENE_MASK);

            }
        }
    }

    public OrthogonalTiledMapRenderer setupMap() {

        if (map == ptc) {
            tiledMap = new TmxMapLoader().load("Maps/ptc.tmx");
        } else if (map == mac) {
            tiledMap = new TmxMapLoader().load("Maps/maclab.tmx");
        } else {
            tiledMap = new TmxMapLoader().load("Maps/maclab.tmx");
        }
        return new OrthogonalTiledMapRenderer(tiledMap);
    }

    public Vector3 getCenterTile() {
        tileLayer  = (TiledMapTileLayer)tiledMap.getLayers().get(0);
        return new Vector3(((tileLayer.getWidth() * 16) / 2), ((tileLayer.getHeight() * 16) / 2), 0);
    }

    public void tick(OrthographicCamera camera, Control control){
        if (control.debug) debugRenderer.render(world, camera.combined);

        world.step(Gdx.app.getGraphics().getDeltaTime(), 6, 2);
        world.clearForces();
    }
    public void addEntities() {

        MapLayer mapLayer = tiledMap.getLayers().get("Playable");
        TiledMapTileLayer grass = (TiledMapTileLayer) mapLayer;

        float limit = 10 + (10 * GameScreen.currentTime);
        int counter = 0;


        for (int y = 0; y < grass.getHeight(); y++) {
            for (int x = 0; x < grass.getWidth(); x++) {

                TiledMapTileLayer.Cell cell = grass.getCell(x, y);
                Vector3 pos = new Vector3(x * ((TiledMapTileLayer) mapLayer).getTileWidth(), y * ((TiledMapTileLayer) mapLayer).getTileHeight(), 0);
                if (cell != null) {
                    if (MathUtils.random(100) > 90 && counter < limit) {
                        randomizeEnemy(pos);
                        counter++;
                    }
                }
            }


        }
    }

    public void randomizeEnemy(Vector3 pos) {
        int randomEnemy = MathUtils.random(4);

        if (randomEnemy == 1) {
            dummy = new Dummy(pos);
            entities.add(dummy);
        } else if (randomEnemy == 2) {
            trojan = new Trojan(pos);
            entities.add(trojan);
        } else if (randomEnemy == 3) {
            worm = new Worm(pos);
            entities.add(worm);
        } else if (randomEnemy == 4) {
            virus = new Virus(pos);
            entities.add(virus);
        }
    }

    public void clearDeadEnemy() {
        Iterator<Enemy> iterator = entities.iterator();

        while(iterator.hasNext()) {
            Enemy a = iterator.next();
            if (a.remove) {
                a.removeBodies(world);

                iterator.remove();
            }
        }
    }
}
