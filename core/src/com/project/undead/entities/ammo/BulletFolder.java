package com.project.undead.entities.ammo;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.project.undead.Media;
import com.project.undead.TileMap;
import com.project.undead.collision.CollisionHelper;
import com.project.undead.entities.Ranged;

public class BulletFolder extends Ammo {
    Ranged ranged;

    public BulletFolder(Ranged ranged, World world) {
        super();
        this.ranged = ranged;
        texture = Media.folderBullet;
        range = 100;
        damage = 1;
        width = texture.getWidth();
        height = texture.getHeight();
        speed = 30;
        active = true;
        setupBullet(world);
    }

    public void tick(float delta) {
        if (active) {
            float dx = (delta * vector.x) * speed;
            float dy = (delta * vector.y) * speed;
            float dx2 = pos.x + dx;
            float dy2 = pos.y + dy;


            // Calculating current to previous distance
            distMoved += Vector2.dst(pos.x, pos.y, dx2, dy2);
            pos.set(dx2, dy2, 0);
            sensor.setTransform(pos.x + width/2, pos.y + height/2, 0);

            if (distMoved > range) {
                remove = true;
                active = false;
            }
        }
    }

    public void setupBullet(World world) {
        // Position of the bullet
        float angleRadians = MathUtils.degreesToRadians * ranged.angle;
        vector.x = MathUtils.cos(angleRadians);
        vector.y = MathUtils.sin(angleRadians);

        pos.x = ranged.pos.x + (vector.x * 10);
        pos.y = ranged.pos.y + (vector.y * 10);

        sensor = CollisionHelper.createSensor(world, width, height * .85f, width / 2, height / 3, pos, BodyDef.BodyType.DynamicBody);
    }
}
