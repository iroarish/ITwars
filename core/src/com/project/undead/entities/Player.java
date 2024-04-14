package com.project.undead.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.project.undead.*;
import com.project.undead.collision.CollisionHelper;
import com.project.undead.collision.MaskHelper;
import com.project.undead.screens.GameOver;
import com.project.undead.entities.Melee;

import java.util.ArrayList;

public class Player extends Entity {
    GameClass game;
    MaskHelper maskHelper;
    Melee melee;
    public Vector3 cameraPos;

    // Player stats
    public int HITPOINTS = 6;
    int SPEED = 15;

    public Player(Vector3 pos) {

        maskHelper = new MaskHelper();

        // Player Stuffs
        type = Enums.ENTITYTYPE.PLAYER;
        cameraPos = new Vector3();
        width = 8;
        height = 8;
        this.pos.x = pos.x;
        this.pos.y = pos.y;
        texture = Media.player;
        speed = SPEED;
        body = CollisionHelper.createBody(TileMap.world, width / 2, height / 2, pos, BodyDef.BodyType.DynamicBody, maskHelper.MYPLAYER, maskHelper.PLAYER_MASK, this);
        melee  = new Melee(1, -1, 7);
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

        cameraPos.set(pos);
        cameraPos.x += width / 2;
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (texture != null) batch.draw(texture, pos.x, pos.y, width, height);
        melee.drawRotated(batch);
    }


    @Override
    public void onHit() {
        HITPOINTS--;
        System.out.println("Player is hit: " + HITPOINTS);
    }
}
