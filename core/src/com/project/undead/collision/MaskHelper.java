package com.project.undead.collision;

public class MaskHelper {
    public final short MYPLAYER = 0x0001;
    public final short DUMMIES = 0x0002;
    public final short DUMMY_COLLISION = 0x0016;
    public final short SCENE = 0x0004;


    public final short PLAYER_MASK = SCENE;
    public final short DUMMY_MASK = DUMMIES | SCENE;
    public final short SCENE_MASK = -1;
}
