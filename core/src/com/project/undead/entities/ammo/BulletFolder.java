package com.project.undead.entities.ammo;

import com.badlogic.gdx.physics.box2d.World;
import com.project.undead.Media;
import com.project.undead.entities.Ranged;

public class BulletFolder extends Ammo {
    Ranged ranged;

    public BulletFolder(Ranged ranged, World world) {
        super();
        this.ranged = ranged;
        texture = Media.folderBullet;
        range = 50;
        damage = 1;
        width = texture.getWidth();
        height = texture.getHeight();
        speed = 60;
        active = true;
//        setupBullet(world);
    }
}
