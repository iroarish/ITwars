package com.project.undead.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.project.undead.Control;
import com.project.undead.Enums;
import com.project.undead.entities.ammo.Ammo;

import java.util.ArrayList;

public abstract class Entity {

    // Variables for Weapons
    public float angle;
    public boolean flipX;
    public boolean flipY;
    public boolean active;
    public boolean remove;
    public ArrayList<Ammo> ammoArray;

    // For Enemies and Player and Everything else
    public Vector3 pos;
    public Texture texture;
    public float width;
    public float height;
    public Enums.ENTITYTYPE type;
    public float speed;
    public Body body;
    public Body sensor;

    float dirX = 0;
    float dirY = 0;


    public Entity() {
        pos = new Vector3();
    }

    // Reason why no entities spawn will fix later
    public void draw(SpriteBatch batch) {
        batch.draw(texture, pos.x, pos.y, width, height);
    }

    public void drawRotated(SpriteBatch batch) {
        batch.draw(texture, pos.x, pos.y, 0, 0, width, height, 1, 1, angle, 0, 0, (int) width, (int) height, flipX, flipY);
    }

    public void updatePos(float x, float y) {
        if (pos != null) {
            pos.x = x;
            pos.y = y;
        }
    }

    public void removeBodies(World world) {
        if (sensor != null) world.destroyBody(sensor);
        if (body != null) world.destroyBody(body);
    }


    public abstract void onHit();
}