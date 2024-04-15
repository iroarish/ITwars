package com.project.undead.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.project.undead.Control;
import com.project.undead.Media;
import com.project.undead.ScreenSize;
import com.project.undead.TileMap;
import com.project.undead.collision.CollisionHelper;
import com.project.undead.collision.MaskHelper;

public class Melee extends Entity{
    MaskHelper mask = new MaskHelper();

    float originXOffset;
    float originYOffset;
    float xPos;
    float xMaxPos;
    float xMinPos;

    public Melee(float originXOffset, float xMinRight, float xMaxRight) {
        texture = Media.weapon1;
        width = texture.getWidth();
        height = texture.getHeight();
        body = CollisionHelper.weaponHitbox(TileMap.world, texture.getWidth(), texture.getHeight(), pos, mask.DUMMIES);
        active = true;
        originYOffset = height / 2;
        this.originXOffset = originXOffset;
        this.xMaxPos = xMaxRight;
        this.xMinPos = xMinRight;
    }

    @Override
    public void drawRotated(SpriteBatch batch) {

        if (angle > 90 && angle < 270) {
            xPos = xMinPos; // To Left
            flipY = true;
        } else {
            xPos = xMaxPos; // To Right
            flipY = false;
        }

        if (texture != null) {
            batch.draw(texture, pos.x + xPos, pos.y, originXOffset, originYOffset, width, height, 1, 1, angle, 0, 0, (int)width, (int)height, flipX, flipY);
        } else {
            System.out.println("Melee texture is null");
        }
    }

    @Override
    public void updatePos(float x, float y) {
        if (pos != null) {
            pos.x = x;
            pos.y = y;
        }
    }

    @Override
    public void onHit() {

    }
}
