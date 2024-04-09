package com.project.undead.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.project.undead.Enums;

public abstract class Entity {
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

    public void draw(SpriteBatch batch) {
        batch.draw(texture, pos.x, pos.y, width, height);
    }

    public abstract void onHit();
    public abstract Rectangle getEntityRectangle();
    public boolean isIntersecting(Rectangle otherRect) {
        Rectangle thisRect = getEntityRectangle();
        return thisRect.overlaps(otherRect);
    }
}