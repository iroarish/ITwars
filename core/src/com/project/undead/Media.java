package com.project.undead;

import com.badlogic.gdx.graphics.Texture;

public class Media {
    public static Texture player;
    public static Texture dummy;
    public static Texture weapon1, weapon2;

    public static void loadMedia() {

        // Player
        player = new Texture("Entities/Player.png");
        dummy = new Texture("Entities/Dummy.png");
        weapon1 = new Texture("Weapons/Weapon1.png");
        weapon2 = new Texture("Weapons/Weapon2.png");

    }

    private void dispose() {
        player.dispose();
        dummy.dispose();
    }

}
