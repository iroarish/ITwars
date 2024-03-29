package com.project.undead.collision;

public class MaskHelper {
    public final short MYPLAYER = 0x0001;
    public final short DUMMIES = 0x0002;
    public final short SCENE = 0x0004;

    public final short playerMask = SCENE;
    public final short dummyMask = DUMMIES | SCENE;
    public final short sceneMask = -1;
}
