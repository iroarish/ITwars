package com.project.undead.collision;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;

public class HitboxHelper {

    public Rectangle getHitbox(float xPos, float yPos, float width, float height, float angle) {
        return new Rectangle(xPos, yPos, width, height);
    }
}
