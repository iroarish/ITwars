package com.project.undead.collision;

public class Test {
    public Test(boolean test) {
        int HP = 100;
        while (test) {
            HP -= 1;
        }

        System.out.println("Current HP: " + HP);
    }
}
