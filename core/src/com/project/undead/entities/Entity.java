package com.project.undead.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.project.undead.Control;
import com.project.undead.Enums;

import java.util.ArrayList;

public abstract class Entity {

    // Variables for Weapons
    public float angle;
    public boolean flipX;
    public boolean flipY;
    public boolean active;
    public ArrayList weapons;

    // For Enemies and Player
    public Vector3 pos;
    public Texture texture;
    public float width;
    public float height;
    public Enums.ENTITYTYPE type;
    public float speed;
    public Body body;

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


    public abstract void onHit();
}