package com.project.undead.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.TimeUtils;
import com.project.undead.*;
import com.project.undead.collision.CollisionHelper;
import com.project.undead.collision.MaskHelper;
import com.project.undead.entities.ammo.Ammo;
import com.project.undead.entities.ammo.BulletFolder;
import com.project.undead.screens.GameOver;
import com.project.undead.entities.Melee;

import java.util.ArrayList;

public class Player extends Entity {
    TileMap tileMap;
    GameClass game;
    MaskHelper maskHelper;
    Melee melee;
    public Ranged ranged;
    int meleeWeapon = 0;
    int rangedWeapon = 1;
    int switchWeapon = meleeWeapon;
    public Vector3 cameraPos;

    // Player stats
    public int HITPOINTS = 5;
    int SPEED = 15;

    // Time tracker
    long startTime = TimeUtils.millis();

    public static int score;
    public int currentAmmo;

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
        ammoArray = new ArrayList<Ammo>();
        melee  = new Melee(1, -1, 7);
        ranged = new Ranged(1, -1, 7);
        ranged.addAmmo(10);
        score = 0;
        currentAmmo = 10;
    }

    public void update(Control control, World world) {
        // Time Tracker for Shooting the gun
        float endTime = (float) TimeUtils.timeSinceMillis(startTime) / 1000;


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

        if (!control.weaponSwitch) {
            switchWeapon = meleeWeapon;
        } else if (control.weaponSwitch) {
            switchWeapon = rangedWeapon;
        }

        if (switchWeapon == rangedWeapon) {
            ranged.updatePos(pos.x, pos.y);
            ranged.angle = control.angle - 90;
            if (control.LMB && ranged.ammoCount > 0 && endTime > 0.5 && currentAmmo > 0) {

                Media.rangedShot.play();
                BulletFolder folder = new BulletFolder(ranged, world);
                ranged.addActiveAmmo(folder);
                control.LMB = false;
                currentAmmo--;

                startTime = TimeUtils.millis();
            }

//            for (Ammo a : ranged.activeAmmo) {
//                if (a.active) {
//                    a.updatePos(pos.x, pos.y);
//                }
//            }

        } else if (switchWeapon == meleeWeapon) {
            melee.updatePos(pos.x, pos.y);
            melee.angle = control.angle - 90;
            melee.updateAttack(pos, control);
        }



        cameraPos.set(pos);
        cameraPos.x += width / 2;
    }

    public void clearAmmo(World world) {
        ranged.clearShootedAmmo(world);
    }

    @Override
    public void draw(SpriteBatch batch) {



        if (texture != null) batch.draw(texture, pos.x, pos.y, width, height);
        if (switchWeapon == rangedWeapon) {
            ranged.drawRotated(batch);
        } else if (switchWeapon == meleeWeapon) {
            melee.drawRotated(batch);
        }

        for (Ammo b : ranged.activeAmmo) {
            if (b.active) {
                b.tick(Gdx.graphics.getDeltaTime());
                b.draw(batch);
            }
        }
//        for (Ammo a : ranged.activeAmmo) {
//            a.draw(batch);
//        }
    }

    @Override
    public void onHit() {
        HITPOINTS--;
        System.out.println("Player is hit: " + HITPOINTS);
    }
}
