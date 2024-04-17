package com.project.undead.collision;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;
import com.project.undead.entities.Dummy;
import com.project.undead.entities.Entity;
import com.project.undead.entities.Melee;
import com.project.undead.entities.Player;

public class ContactChecker implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if (fixA == null || fixB == null) return;


        if (fixA.getUserData() instanceof Melee) {
            System.out.println("This is the melee attack");
        } else if (fixB.getUserData() instanceof Melee) {
            System.out.println("This is the melee attack");
        }

//        if (fixA.getUserData() instanceof Melee && fixB.getUserData() instanceof Dummy) {
//            ((Entity) fixB.getUserData()).onHit();
//        } else if (fixA.getUserData() instanceof Dummy && fixB.getUserData() instanceof Melee) {
//            ((Entity) fixA.getUserData()).onHit();
//        }

        if (fixA.getUserData() instanceof Player) {
            ((Entity) fixA.getUserData()).onHit();
        } else if (fixB.getUserData() instanceof Player) {
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
