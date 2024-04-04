package com.project.undead.collision;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;
import com.project.undead.entities.Entity;

public class ContactChecker implements ContactListener {

    boolean isHit = false;
    Test test = new Test(isHit);
    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if (fixA == null || fixB == null) return;
        if (fixA.getUserData() == null || fixB.getUserData() == null) return;
        isHit = true;
    }

    @Override
    public void endContact(Contact contact) {
        isHit = false;
    }

    @Override
    public void preSolve(Contact contact, Manifold manifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {

    }
}
