package com.project.undead.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.project.undead.Media;

public class Melee extends Entity{

    float originXOffset;
    float originYOffset;
    float xPos;
    float xMaxPos;
    float xMinPos;

    public Melee(float originXOffset, float xMinRight, float xMaxRight) {
        texture = Media.weapon1;
        width = texture.getWidth();
        height = texture.getHeight();
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
        }
    }

    @Override
    public void onHit() {

    }
}
