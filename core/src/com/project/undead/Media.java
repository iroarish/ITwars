package com.project.undead;

import com.badlogic.gdx.graphics.Texture;

public class Media {
    public static Texture player;
    public static Texture dummy;
    public static Texture weapon1, weapon2;
    public static Texture rangeWeapon;
    public static Texture folderBullet;
    public static Texture weaponAnimation;

    // Ui Texture
    public static Texture activePtcSelect;
    public static Texture activeMacSelect;
    public static Texture inactivePtcSelect;
    public static Texture inactiveMacSelect;

    public static void loadMedia() {

        // Ui Texture
        activePtcSelect = new Texture("UI/PtcButtonActive.png");
        inactivePtcSelect = new Texture("UI/PtcButtonInactive.png");
        activeMacSelect = new Texture("UI/MacButtonActive.png");
        inactiveMacSelect = new Texture("UI/MacButtonInactive.png");


        player = new Texture("Entities/Player.png");
        dummy = new Texture("Entities/Dummy.png");
        weapon1 = new Texture("Weapons/Weapon1.png");
        weapon2 = new Texture("Weapons/Weapon2.png");
        rangeWeapon = new Texture("Weapons/RangeWeapon.png");
        folderBullet = new Texture("Weapons/FolderBullet.png");
        weaponAnimation = new Texture("Weapons/MeleeAttackAnimation.png");

    }

    private void dispose() {
        weapon1.dispose();
        weapon2.dispose();
        player.dispose();
        dummy.dispose();
        rangeWeapon.dispose();
        folderBullet.dispose();

        // Ui stuffs
        activePtcSelect.dispose();
        inactivePtcSelect.dispose();
        activeMacSelect.dispose();
        inactiveMacSelect.dispose();
    }

}
