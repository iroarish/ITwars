package com.project.undead.entities;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.project.undead.GameClass;
import com.project.undead.Media;
import com.project.undead.screens.GameScreen;

public class Enemy extends Entity{
    public int HITPOINTS;

    @Override
    public void onHit() {
        HITPOINTS--;
        System.out.println(this + " HP: " + HITPOINTS);
    }

    public void update(Player player) {
        dirX = 0;
        dirY = 0;

        if (player.pos.x > this.pos.x) {
            dirX = 1;
        }
        if (player.pos.x  < this.pos.x) {
            dirX = -1;
        }
        if (player.pos.y  < this.pos.y) {
            dirY = -1;
        }
        if (player.pos.y  > this.pos.y) {
            dirY = 1;
        }

        pos.x += dirX * speed;
        pos.y += dirY * speed;

        float c = (float) Math.sqrt((pos.x * 2) + (pos.y * 2));

        pos.x = pos.x / c;
        pos.y = pos.y / c;

        body.setLinearVelocity(dirX * speed, dirY * speed);
        pos.x = body.getPosition().x - width / 2;
        pos.y = body.getPosition().y - height / 4;

        if (HITPOINTS < 1) {
            Media.enemyDeath.play();
            Media.enemyDeath.setVolume(1, 0.1f);
            player.score++;
            if (MathUtils.random(100) > 75 && player.currentAmmo < 10) {
                player.ranged.addAmmo(1);
                System.out.println("+1 ammo");
                player.currentAmmo++;
            }
            remove = true;
        }

        System.out.println(speed);
    }
}
