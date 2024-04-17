package com.project.undead.entities.ammo;

import com.badlogic.gdx.math.Vector2;
import com.project.undead.entities.Entity;

public class Ammo extends Entity {

    public float range;
    public float damage;
    public Vector2 vector;
    public float distMoved;

    public Ammo() {
        super();
        vector = new Vector2();
    }

    public void tick(float delta) {

    }

    @Override
    public void onHit() {

    }
}
