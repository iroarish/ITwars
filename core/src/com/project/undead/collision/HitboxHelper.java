package com.project.undead.collision;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;

public class HitboxHelper {

    public static Body createHitbox(World world, float width, float height, Vector3 pos, BodyDef.BodyType type, String id) {
        Body body;
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(pos.x + width/2, pos.y + height/2);
        bodyDef.angle = 0;
        bodyDef.fixedRotation = true;
        bodyDef.type = type;
        body = world.createBody(bodyDef);

        FixtureDef hitboxFixture = new FixtureDef();
        PolygonShape hitbox = new PolygonShape();
        // Change to '/' later
        hitbox.setAsBox(width * 2, height * 2);

        hitboxFixture.shape = hitbox;
        hitboxFixture.isSensor = true;
//        fixtureDef.restitution = 0.4f;





        body.createFixture(hitboxFixture).setUserData(id);;
        hitbox.dispose();

        return body;
    }
}
