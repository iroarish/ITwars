package com.project.undead.entities;

import com.badlogic.gdx.math.Vector3;
import com.project.undead.Control;
import com.project.undead.collision.CollisionHelper;
import com.project.undead.collision.MaskHelper;
import com.project.undead.TileMap;

public class WeaponTest extends Entity{
    MaskHelper mask;
    boolean isAttack;

    public WeaponTest(Vector3 pos) {
        mask = new MaskHelper();

        width = 10;
        height = 10;
        this.pos.x = pos.x;
        this.pos.y = pos.y;

        body = CollisionHelper.weaponHitbox(TileMap.world, width, height, pos, mask.SCENE);
    }

    public void update(Player player, Control control) {
        pos.x = player.pos.x;
        pos.y = player.pos.y;

        body.setLinearVelocity(player.dirX * player.speed, player.dirY * player.speed);
        pos.x = body.getPosition().x;
        pos.y = body.getPosition().y;
    }

    @Override
    public void onHit() {

    }
}
