package com.project.undead;

import com.badlogic.gdx.graphics.Texture;

public class Media {
    public static Texture player;
    public static Texture dummy;

    public static void loadMedia() {

        // Player
        player = new Texture("Entities/Player.png");
        dummy = new Texture("Entities/Dummy.png");

    }

    private void dispose() {
        player.dispose();
        dummy.dispose();
    }

}
