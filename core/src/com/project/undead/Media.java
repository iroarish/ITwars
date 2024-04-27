package com.project.undead;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class Media {
    public static Texture player;
    public static Texture dummy, trojan, virus, worm;
    public static Texture weapon1, weapon2;
    public static Texture rangeWeapon;
    public static Texture folderBullet;
    public static Texture weaponAnimation;

    // Ui Texture
    public static Texture activePtcSelect;
    public static Texture activeMacSelect;
    public static Texture inactivePtcSelect;
    public static Texture inactiveMacSelect;
    public static Texture activeMainMenu;
    public static Texture inactiveMainMenu;
    public static Texture menuBackground;
    public static Texture activeBackButton;
    public static Texture inactiveBackButton;
    public static Texture mapSelectBg;
    public static Texture gameOverBg;
    public static Texture howToBg;
    public static Texture activeHowTo;
    public static Texture inactiveHowTo;

    // Sounds
    public static Sound enemyDeath;
    public static Sound laptopSwing;
    public static Sound rangedShot;
    public static Music mainmenuMusic;


    public static void loadMedia() {

        // Sounds
        laptopSwing = Gdx.audio.newSound(Gdx.files.internal("Sound/sword.wav"));
        enemyDeath = Gdx.audio.newSound(Gdx.files.internal("Sound/enemydeath.wav"));
        rangedShot = Gdx.audio.newSound(Gdx.files.internal("Sound/shot.wav"));
        mainmenuMusic = Gdx.audio.newMusic(Gdx.files.internal("Sound/sasamahankita.mp3"));

        // Ui Texture
        activePtcSelect = new Texture("UI/PtcButtonActive.png");
        inactivePtcSelect = new Texture("UI/PtcButtonInactive.png");
        activeMacSelect = new Texture("UI/MacButtonActive.png");
        inactiveMacSelect = new Texture("UI/MacButtonInactive.png");
        activeMainMenu = new Texture("UI/ActiveMainMenu.png");
        inactiveMainMenu = new Texture("UI/InactiveMainMenu.png");
        menuBackground = new Texture("UI/bg.png");
        activeBackButton = new Texture("UI/ActiveBack.png");
        inactiveBackButton = new Texture("UI/InactiveBack.png");
        mapSelectBg = new Texture("UI/dawnbackground.png");
        gameOverBg = new Texture("UI/nightbackground.png");
        howToBg = new Texture("UI/fajrbackground.png");
        activeHowTo = new Texture("UI/ActiveHowTo.png");
        inactiveHowTo = new Texture("UI/InactiveHowTo.png");



        worm = new Texture("Entities/worm.png");
        virus = new Texture("Entities/virus.png");
        trojan = new Texture("Entities/trojan.png");
        player = new Texture("Entities/Player1.png");
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
        trojan.dispose();
        worm.dispose();
        virus.dispose();
        rangeWeapon.dispose();
        folderBullet.dispose();

        // Ui stuffs
        activePtcSelect.dispose();
        inactivePtcSelect.dispose();
        activeMacSelect.dispose();
        inactiveMacSelect.dispose();
        activeMainMenu.dispose();
        inactiveMainMenu.dispose();
        menuBackground.dispose();
        activeBackButton.dispose();
        inactiveBackButton.dispose();
        mapSelectBg.dispose();
        gameOverBg.dispose();
        howToBg.dispose();
        activeHowTo.dispose();
        inactiveHowTo.dispose();

        // Sound
        mainmenuMusic.dispose();
        enemyDeath.dispose();
        laptopSwing.dispose();
        rangedShot.dispose();
    }

}
