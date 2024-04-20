package com.project.undead.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.project.undead.Media;
import com.project.undead.TileMap;
import com.project.undead.collision.CollisionHelper;
import com.project.undead.entities.ammo.Ammo;

import java.util.ArrayList;
import java.util.Iterator;

public class Ranged extends Entity{
    float originXOffset;
    float originYOffset;
    float xPos;
    float xMaxPos;
    float xMinPos;
    float ammoCount;
    public ArrayList<Ammo> activeAmmo;

    public Ranged(float originXOffset, float xMinRight, float xMaxRight) {
        texture = Media.rangeWeapon;
        width = texture.getWidth();
        height = texture.getHeight();
        active = true;
        originYOffset = height / 2;
        this.originXOffset = originXOffset;
        this.xMaxPos = xMaxRight;
        this.xMinPos = xMinRight;
        activeAmmo = new ArrayList<Ammo>();
    }

    public void tick(float delta) {
        for (Ammo a : activeAmmo) {
            a.tick(delta);
        }
    }

    public void addActiveAmmo(Ammo ammo) {
        if (ammoCount > 0) {
            activeAmmo.add(ammo);
            ammoCount--;
        } else {
            System.out.println("Clink");
            // Todo Add a Window or a texture indicator that the there are no more ammo in the gun
        }
    }

    public void addAmmo(int count) {
        ammoCount += count;
    }


    public void clearShootedAmmo(World world) {
        Iterator<Ammo> iterator = activeAmmo.iterator();

        while(iterator.hasNext()) {
            Ammo a = iterator.next();
            if (a.remove) {
                a.removeBodies(world);

                iterator.remove();
            }
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
            System.out.println("Ranged texture is null");
        }

        // Bullet
        for (Ammo a : activeAmmo) {
            a.draw(batch);
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
