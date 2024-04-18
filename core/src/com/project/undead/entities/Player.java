package com.project.undead.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;
import com.project.undead.*;
import com.project.undead.collision.CollisionHelper;
import com.project.undead.collision.MaskHelper;
import com.project.undead.screens.GameOver;
import com.project.undead.entities.Melee;

import java.util.ArrayList;

public class Player extends Entity {
    TileMap tileMap;
    GameClass game;
    MaskHelper maskHelper;
    Melee melee;
    Ranged ranged;
    int meleeWeapon = 0;
    int rangedWeapon = 1;
    int switchWeapon = meleeWeapon;
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
        ranged = new Ranged(1, -1, 7);
    }

    public void update(Control control, World world) {
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

        float c = (float) Math.sqrt((pos.x * 2) + (pos.y * 2));

        pos.x = pos.x / c;
        pos.y = pos.y / c;

        body.setLinearVelocity(dirX * speed, dirY * speed);
        pos.x = body.getPosition().x - width / 2;
        pos.y = body.getPosition().y - height / 4;

        if (switchWeapon == rangedWeapon) {
            ranged.updatePos(pos.x, pos.y);
            ranged.angle = control.angle - 90;
        } else if (switchWeapon == meleeWeapon) {
            melee.updatePos(pos.x, pos.y);
            melee.angle = control.angle - 90;
            melee.updateAttack(pos, control);
        }

        cameraPos.set(pos);
        cameraPos.x += width / 2;
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (texture != null) batch.draw(texture, pos.x, pos.y, width, height);
        if (switchWeapon == rangedWeapon) {
            ranged.drawRotated(batch);
        } else if (switchWeapon == meleeWeapon) {
            melee.drawRotated(batch);
        }
    }

    public void clearBullets(World world) {

    }


    @Override
    public void onHit() {
        HITPOINTS--;
        System.out.println("Player is hit: " + HITPOINTS);
    }
}
