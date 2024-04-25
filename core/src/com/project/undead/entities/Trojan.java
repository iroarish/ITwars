package com.project.undead.entities;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.project.undead.Enums;
import com.project.undead.Media;
import com.project.undead.TileMap;
import com.project.undead.collision.CollisionHelper;
import com.project.undead.collision.MaskHelper;
import com.project.undead.screens.GameScreen;

public class Trojan extends Enemy{
    MaskHelper maskHelper;
    GameScreen screen;


    public Trojan(Vector3 pos) {
        super();
        maskHelper = new MaskHelper();
        screen = new GameScreen();

        // Dummy Stats
        HITPOINTS = 2;
        speed = 10 + (1 * GameScreen.currentTime);

        // Dummy stuffswww
        type = Enums.ENTITYTYPE.DUMMY;
        width = 8;
        height = 8;
        this.pos.x = pos.x;
        this.pos.y = pos.y;
        texture = Media.trojan;
        remove = false;
        body = CollisionHelper.createBody(TileMap.world, width / 2, height / 2, pos, BodyDef.BodyType.DynamicBody, maskHelper.TROJAN, maskHelper.TROJAN_MASK, this);
        body.setAwake(true);
    }
}
