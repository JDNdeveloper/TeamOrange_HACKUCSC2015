package com.TeamOrange.NewSquareGame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Brad on 1/10/2015.
 */
public class Star {

    Texture texture;
    Sprite sprite;
    Body body;
    Vector2 bottomLeftCorner;

    public Star(float x, float y, World world){
            sprite = new Sprite(new Texture("star.png"));
            sprite.setPosition(x, y);
            BodyDef bodyDef = new BodyDef();
            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set((bottomLeftCorner.x + sprite.getWidth() /2) / Constants.PIXELS_TO_METERS, (bottomLeftCorner.y + sprite.getHeight() / 2) / Constants.PIXELS_TO_METERS);

            body = world.createBody(bodyDef);

            CircleShape shape = new CircleShape();
            shape.setRadius(sprite.getWidth() / 2 / Constants.PIXELS_TO_METERS);

            FixtureDef fixtureDef = new FixtureDef();
            fixtureDef.shape = shape;
            fixtureDef.density = 0.1f;

            body.createFixture(fixtureDef);
            shape.dispose();

    }

    public void act(){

    }

    public void draw(SpriteBatch batch){
        
    }
}
