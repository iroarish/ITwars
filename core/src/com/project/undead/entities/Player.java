package com.project.undead.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.project.undead.Control;
import com.project.undead.Enums;
import com.project.undead.Media;
import com.project.undead.collision.CollisionHelper;
import com.project.undead.collision.MaskHelper;
import com.project.undead.screens.TileMap;

public class Player extends Entity {
    MaskHelper maskHelper;
    ENTITYSTAT STAT;
    public Player(Vector3 pos) {
        STAT = new ENTITYSTAT();
        maskHelper = new MaskHelper();

        // Player Stuffs
        type = Enums.ENTITYTYPE.PLAYER;
        width = 8;
        height = 8;
        this.pos.x = pos.x;
        this.pos.y = pos.y;
        texture = Media.player;
        speed = STAT.PLAYER_SPEED;
        body = CollisionHelper.createBody(TileMap.world, width / 2, height / 2, pos, BodyDef.BodyType.DynamicBody, maskHelper.MYPLAYER, maskHelper.PLAYER_MASK, "Player");
    }

    public void update(Control control) {
        dirX = 0;
        dirY = 0;

        if (control.up) {
            dirY = 1;
        }
        if (control.down) {
            dirY = -1;
        }
        if (control.left) {
            dirX = -1;
        }
        if (control.right) {
            dirX = 1;
        }

        pos.x += dirX * speed;
        pos.y += dirY * speed;

        body.setLinearVelocity(dirX * speed, dirY * speed);
        pos.x = body.getPosition().x - width / 2;
        pos.y = body.getPosition().y - height / 4;
    }

    @Override
    public void onHit() {
        System.out.println("Player is hit: " + Gdx.graphics.getDeltaTime());
    }
}
