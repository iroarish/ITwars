package com.project.undead.collision;

public class MaskHelper {
    public final short MYPLAYER = 1;
    public final short DUMMIES = 2;
    public final short SCENE = 4;
    public final short MELEE = 8;
    public final short TROJAN = 16;
    public final short WORM = 32;
    public final short VIRUS = 64;


    public final short PLAYER_MASK = SCENE;
    public final short DUMMY_MASK = DUMMIES | MELEE;
    public final short TROJAN_MASK = TROJAN | MELEE;
    public final short WORM_MASK = WORM | MELEE;
    public final short VIRUS_MASK = VIRUS | MELEE;
    public final short SCENE_MASK = -1;
}
