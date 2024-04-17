package com.project.undead.entities.ammo;

import com.badlogic.gdx.physics.box2d.World;
import com.project.undead.Media;
import com.project.undead.entities.Ranged;

public class Bullet extends Ammo {
    Ranged ranged;

    public Bullet(Ranged ranged, World world) {
        super();
        this.ranged = ranged;
        texture = Media.folderBullet;
    }
}
