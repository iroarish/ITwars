package com.project.undead.entities;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.project.undead.Enums;
import com.project.undead.Media;
import com.project.undead.collision.CollisionHelper;
import com.project.undead.collision.MaskHelper;
import com.project.undead.TileMap;

public class Dummy extends Enemy{
    MaskHelper maskHelper;

    public Dummy(Vector3 pos) {
        super();
        maskHelper = new MaskHelper();

        // Dummy Stats
        HITPOINTS = 5;
        speed = 9;

        // Dummy stuffswww
        type = Enums.ENTITYTYPE.DUMMY;
        width = 8;
        height = 8;
        this.pos.x = pos.x;
        this.pos.y = pos.y;
        texture = Media.dummy;
        remove = false;
        body = CollisionHelper.createBody(TileMap.world, width / 2, height / 2, pos, BodyDef.BodyType.DynamicBody, maskHelper.DUMMIES, maskHelper.DUMMY_MASK, this);
        body.setAwake(true);
        //body.setSleepingAllowed(false);
//        hitbox = HitboxHelper.createHitbox(TileMap.world, width / 2, height / 2, pos, BodyDef.BodyType.DynamicBody, "Dummy");

        if (!body.isAwake()) {
            System.out.println(body.getUserData() + " Dummy is sleeping.");
        }
    }

    public void update(Player player) {
        dirX = 0;
        dirY = 0;

        if (player.pos.x > this.pos.x) {
            dirX = 1;
        }
        if (player.pos.x  < this.pos.x) {
            dirX = -1;
        }
        if (player.pos.y  < this.pos.y) {
            dirY = -1;
        }
        if (player.pos.y  > this.pos.y) {
            dirY = 1;
        }

        pos.x += dirX * speed;
        pos.y += dirY * speed;

        float c = (float) Math.sqrt((pos.x * 2) + (pos.y * 2));

        pos.x = pos.x / c;
        pos.y = pos.y / c;

        body.setLinearVelocity(dirX * speed, dirY * speed);
        pos.x = body.getPosition().x - width / 2;
        pos.y = body.getPosition().y - height / 4;

        if (HITPOINTS < 1) {
            remove = true;
        }
    }

    @Override
    public void onHit() {
        System.out.println("Dummy hit!");
        HITPOINTS--;
        System.out.println("Current Dummy HP: " + HITPOINTS);
    }
}
