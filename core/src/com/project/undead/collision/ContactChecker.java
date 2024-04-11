package com.project.undead.collision;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;
import com.project.undead.entities.Entity;
import com.project.undead.entities.Player;

public class ContactChecker implements ContactListener {

    int counter = 1;

    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if (fixA == null || fixB == null) return;

        if (fixA.getUserData() instanceof Player) {
            ((Entity) fixA.getUserData()).onHit();
        } else if (fixB.getUserData() instanceof Player) {
            ((Entity) fixA.getUserData()).onHit();
        }

    }

    @Override
    public void endContact(Contact contact) {
    }

    @Override
    public void preSolve(Contact contact, Manifold manifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {

    }
}
