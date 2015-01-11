package com.TeamOrange.NewSquareGame;

/**
 * Created by jgemig on 1/10/2015.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class KeyClass {

    static final float screenWidthMeters = Gdx.graphics.getWidth()/Constants.PIXELS_TO_METERS;
    static final float screenHeightMeters = Gdx.graphics.getHeight()/Constants.PIXELS_TO_METERS;

    public static Vector2 screenCenter() {
        return new Vector2(screenWidthMeters/2,screenHeightMeters/2);
    }

    public static void checkBoundsReset(Body body){
        Transform bodyPosition = body.getTransform();
        Vector2 test = bodyPosition.getPosition();

        if (test.y < 0 || test.x < 0 || test.x > screenWidthMeters) {
            reset(body);
        }
        if  (test.y > screenHeightMeters){
            body.applyForceToCenter(0f, -Constants.JUMPFORCE, true);
        }
    }

    public static void reset(Body body) {
        body.setLinearVelocity(0f, 0f);
        body.setAngularVelocity(Constants.ZERO_ANG_VELOCITY);
        body.setTransform(screenCenter(), 0f);
    }
}
