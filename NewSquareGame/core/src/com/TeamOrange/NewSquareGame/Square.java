package com.TeamOrange.NewSquareGame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.Transform;


/**
 * Created by drake on 1/10/15.
 */
public class Square extends Body {

    public float x;
    public float y;
    public float angle;
    public float width;
    public float height;
    Texture squareTexture;
    Sprite squareSprite;
    World world;
    Body body;
    SpriteBatch batch;

    public Square(World _world, float _x, float _y) {
        super(_world, 0);
        x = _x;
        y = _y;
        angle = 0f;
        world = _world;

        squareTexture = new Texture("square.png");
        squareSprite = new Sprite(squareTexture);
        width = squareSprite.getWidth();
        height = squareSprite.getHeight();
        squareSprite.setPosition(-width/2, -height/2);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(KeyClass.screenCenter());

        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(squareSprite.getWidth()/2 / Constants.PIXELS_TO_METERS,
                       squareSprite.getHeight()/2 / Constants.PIXELS_TO_METERS);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.05f;
        fixtureDef.restitution = 0f;

        body.createFixture(fixtureDef);
        shape.dispose();
    }

    public void setPosition(float _x, float _y) {
        x = _x;
        y = _y;
        angle = 0f;
        squareSprite.setPosition(_x, _y);
    }

    public void setPosition(Vector2 position) {
        setPosition(position.x, position.y);
    }

    public void applyTorque(float torque, boolean wake) {
        body.applyTorque(torque, wake);
    }

    public void setLinearVelocity(float v_x, float v_y) {
        body.setLinearVelocity(v_x, v_y);
    }

    public void setAngularVelocity(float v_a) {
        body.setAngularVelocity(v_a);
    }

    public void setTransform(Vector2 position, float _angle) {
        setPosition(position.x, position.y);
        angle = _angle;
    }

    public Transform getTransform() {
        return new Transform(new Vector2(x, y), angle);
    }

    public Vector2 getLinearVelocity() {
        return body.getLinearVelocity();
    }

}

