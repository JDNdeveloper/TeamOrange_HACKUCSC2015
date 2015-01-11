package com.TeamOrange.NewSquareGame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Transform;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by drake on 1/11/15.
 */
public class Square {
    Vector2 dimensions; // width and height
    Sprite sprite;
    PolygonShape shape;
    FixtureDef fixtureDef;
    Body body;

    public Square(Body _body) {
        body = _body;

        sprite = new Sprite(new Texture("square.png"));
        dimensions = new Vector2(sprite.getWidth(), sprite.getHeight());
        sprite.setPosition(-dimensions.x/2, -dimensions.y/2);

        shape = new PolygonShape();
        shape.setAsBox(dimensions.x/2 / Constants.PIXELS_TO_METERS,
                       dimensions.y/2 / Constants.PIXELS_TO_METERS);

        fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.05f;
        fixtureDef.restitution = 0f;

        body.createFixture(fixtureDef);
        shape.dispose();
    }

    public void draw(Batch batch) {
        batch.draw(sprite,
                sprite.getX(), sprite.getY(),
                sprite.getOriginX(), sprite.getOriginY(),
                sprite.getWidth(), sprite.getHeight(),
                sprite.getScaleX(), sprite.getScaleY(),
                sprite.getRotation());
    }

    public void setPosition(float x, float y) {
        sprite.setPosition(x, y);
    }

    public void setRotation(float angle) {
        sprite.setRotation(angle);
    }

    public void setTransform(Vector2 position, float angle) {
        body.setTransform(position, angle);
    }

    public float getWidth() {
        return dimensions.x;
    }

    public float getHeight() {
        return dimensions.y;
    }

    public void setAngularVelocity(float omega) {
        body.setAngularVelocity(omega);
    }

    public void setLinearVelocity(float vX, float vY) {
        body.setLinearVelocity(vX, vY);
    }

    public void applyTorque(float torque, boolean wake) {
        body.applyTorque(torque, wake);
    }

    public Vector2 getPosition() {
        return body.getPosition();
    }

    public float getAngle() {
        return body.getAngle();
    }

    public void applyForceToCenter(float forceX, float forceY, boolean wake) {
        body.applyForceToCenter(forceX, forceY, wake);
    }

    public Vector2 getLinearVelocity() {
        return body.getLinearVelocity();
    }

    public Transform getTransform() {
        return body.getTransform();
    }
}
