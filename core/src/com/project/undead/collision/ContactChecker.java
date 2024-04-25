package com.project.undead.collision;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;
import com.project.undead.entities.*;
import com.project.undead.entities.ammo.Ammo;

public class ContactChecker implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if (fixA == null || fixB == null) return;
        if (fixA.getUserData() == null || fixB.getUserData() == null) return;

        if (fixA.getUserData() instanceof Melee || fixA.getUserData() instanceof Ammo && fixB.getUserData() instanceof Enemy) {
            ((Entity) fixB.getUserData()).onHit();
        } else if (fixB.getUserData() instanceof Melee || fixB.getUserData() instanceof Ammo && fixA.getUserData() instanceof Enemy) {
            ((Entity) fixA.getUserData()).onHit();
        }



        if (fixA.getUserData() instanceof Player && fixB.getUserData() instanceof Enemy) {
            ((Entity) fixA.getUserData()).onHit();
        } else if (fixB.getUserData() instanceof Player && fixA.getUserData() instanceof Enemy) {
            ((Entity) fixB.getUserData()).onHit();
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
