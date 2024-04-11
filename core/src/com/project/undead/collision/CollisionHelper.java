package com.project.undead.collision;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class CollisionHelper {


    public static Body createBody(World world, float width, float height, Vector3 pos, BodyDef.BodyType type, short maskCategory, short collideWith, String id) {
        Body body;
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(pos.x + width/2, pos.y + height/2);
        bodyDef.angle = 0;
        bodyDef.fixedRotation = true;
        bodyDef.type = type;
        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape boxShape = new PolygonShape();
        boxShape.setAsBox(width / 2, height / 2);

        fixtureDef.shape = boxShape;
        fixtureDef.density = 20.0f;
        fixtureDef.friction = 1.0f;
        fixtureDef.restitution = 0.0f;
        fixtureDef.filter.categoryBits = maskCategory;
        fixtureDef.filter.maskBits = collideWith;

        body.createFixture(fixtureDef);
        boxShape.dispose();

        FixtureDef hitboxFixture = new FixtureDef();
        PolygonShape hitbox = new PolygonShape();

        hitbox.setAsBox(width / 2f, height / 2f);

        hitboxFixture.shape = hitbox;
        hitboxFixture.isSensor = true;
//        fixtureDef.restitution = 0.4f;

        body.createFixture(hitboxFixture).setUserData(id);;
        hitbox.dispose();

        return body;
    }

    public static Body createBody(World world, float width, float height, Vector3 pos, BodyDef.BodyType type, short maskCategory, short collideWith) {
        Body body;
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(pos.x + width/2, pos.y + height/2);
        bodyDef.angle = 0;
        bodyDef.fixedRotation = true;
        bodyDef.type = type;
        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape boxShape = new PolygonShape();
        boxShape.setAsBox(width / 2, height / 2);

        fixtureDef.shape = boxShape;
        fixtureDef.restitution = 0.4f;
        fixtureDef.filter.categoryBits = maskCategory;
        fixtureDef.filter.maskBits = collideWith;

        body.createFixture(fixtureDef);
        boxShape.dispose();

        return body;
    }

    public static Body weaponHitbox(World world, float width, float height, Vector3 pos, short mask) {
        Body body;
        BodyDef bdef = new BodyDef();
        bdef.position.set(pos.x, pos.y);
        bdef.angle = 0;
        bdef.fixedRotation = true;
        bdef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bdef);

        FixtureDef hitboxDef = new FixtureDef();
        PolygonShape hitbox = new PolygonShape();
        hitbox.setAsBox(width, height);

        hitboxDef.shape = hitbox;
        hitboxDef.isSensor = true;

        FixtureDef physicalDef = new FixtureDef();

        physicalDef.shape = hitbox;
        physicalDef.filter.maskBits = mask;

        body.createFixture(hitboxDef);
        body.createFixture(physicalDef);
        hitbox.dispose();

        return body;
    }
}
