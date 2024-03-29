package com.project.undead.entities;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.project.undead.Enums;
import com.project.undead.Media;
import com.project.undead.collision.CollisionHelper;
import com.project.undead.collision.MaskHelper;
import com.project.undead.collision.TileMap;

import static com.project.undead.collision.CollisionHelper.createBody;

public class Dummy extends Entity{
    MaskHelper maskHelper;

    public Dummy(Vector3 pos) {

        super();
        maskHelper = new MaskHelper();
        type = Enums.ENTITYTYPE.DUMMY;
        width = 8;
        height = 8;
        speed = 15;
        this.pos.x = pos.x;
        this.pos.y = pos.y;
        texture = Media.dummy;
        body = CollisionHelper.createBody(TileMap.world, width / 2, height / 2, pos, BodyDef.BodyType.DynamicBody, maskHelper.DUMMIES, maskHelper.dummyMask);
    }


    public void update(Player player) {
        dirX = 0;
        dirY = 0;

        if (player.pos.x > this.pos.x) {
            dirX = 1;
        }
        if (player.pos.x < this.pos.x) {
            dirX = -1;
        }
        if (player.pos.y < this.pos.y) {
            dirY = -1;
        }
        if (player.pos.y > this.pos.y) {
            dirY = 1;
        }

        pos.x += dirX * speed;
        pos.y += dirY * speed;

        body.setLinearVelocity(dirX * speed, dirY * speed);
        pos.x = body.getPosition().x - width / 2;
        pos.y = body.getPosition().y - height / 4;
    }

}
