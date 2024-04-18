package com.project.undead.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
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
    int N = 1;
    int W = 2;
    int S = 3;
    int E = 4;
    int NW = 5;
    int NE = 6;
    int SW = 7;
    int SE = 8;
    int compass = 0;

    public Melee(float originXOffset, float xMinRight, float xMaxRight) {
        texture = Media.weapon1;
        width = texture.getWidth();
        height = texture.getHeight();
        body = CollisionHelper.weaponHitbox(TileMap.world, texture.getWidth() / 2f, texture.getHeight() / 2f, pos, mask.MELEE, mask.DUMMIES, this);
        body.setActive(false);
        active = true;
        originYOffset = height / 2;
        this.originXOffset = originXOffset;
        this.xMaxPos = xMaxRight;
        this.xMinPos = xMinRight;
    }

    public void updateAttack(Vector3 playerPos, Control control) {

        getCompass();  // Checking the angle of mouse Then Assign N, S, E, W and more per mouse angles
        updateMeleeCollision(playerPos); // Update the location of body collision based on the assigned compass

        if (control.LMB) {
            body.setActive(true);
        } else {
            body.setActive(false);
        }




        // Then if Player clicked attack button
        // Set the texture to the animation then set the body collision to Active
        // When the animation stopped set the body to inactive again.
    }

    public void updateMeleeCollision(Vector3 playerPos) {
        if (compass == NE) {
            body.setTransform(playerPos.x + 25, playerPos.y + 25, 90f);
        } else if (compass == N) {
            body.setTransform(playerPos.x, playerPos.y + 25, 0f);
        } else if (compass == NW) {
            body.setTransform(playerPos.x - 25, playerPos.y + 25, 180f);
        } else if (compass == W) {
            body.setTransform(playerPos.x - 25, playerPos.y, 67.55f);
        } else if (compass == SW) {
            body.setTransform(playerPos.x - 25, playerPos.y - 25, 90f);
        } else if (compass == S) {
            body.setTransform(playerPos.x, playerPos.y - 25, 0f);
        } else if (compass == SE) {
            body.setTransform(playerPos.x + 25, playerPos.y - 25, 180f);
        } else if (compass == E) {
            body.setTransform(playerPos.x + 25, playerPos.y, 67.55f);
        }
    }

    public void getCompass() {
        if (angle < 0) {
            angle += 360;
        }


        if (22.5 > angle && angle > 0 || angle > 337.5 && angle < 360) {
            compass = E; // East
        } else if (67.5 > angle && angle > 22.5) {
            compass = NE; // North-East
        } else if (112.4 > angle && angle > 67.5) {
            compass = N; // North
        } else if (157.5 > angle && angle > 112.5) {
            compass = NW; // North-West
        } else if (202.5 > angle && angle > 157.5) {
            compass = W; // West
        } else if (247.5 > angle && angle > 202.5) {
            compass = SW; // South-West
        } else if (292.5 > angle && angle > 247.5) {
            compass = S; // South
        } else if (337.5 > angle && angle > 292.5) {
            compass = SE;
        }

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

        // Debugging Purposes
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
