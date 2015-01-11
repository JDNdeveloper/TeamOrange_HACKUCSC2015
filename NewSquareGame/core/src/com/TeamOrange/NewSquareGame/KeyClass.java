package com.TeamOrange.NewSquareGame;

/**
 * Created by jgemig on 1/10/2015.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.Graphics;

public class KeyClass {

    static final float PIXELS_TO_METERS = 100f;
    static final float screenWidthMeters = Gdx.graphics.getWidth()/PIXELS_TO_METERS;
    static final float screenHeightMeters = Gdx.graphics.getHeight()/PIXELS_TO_METERS;

    public static Vector2 screenCenter() {
        return new Vector2(screenWidthMeters/2,screenHeightMeters/2);
    }

    public static void checkBoundsReset(Body body){
        Transform bodyPosition = body.getTransform();
        Vector2 test = bodyPosition.getPosition();

        if (test.y < 0 || test.x < 0 || test.x > screenWidthMeters) {
            body.setLinearVelocity(0f, 0f);
            body.setAngularVelocity(0f);
            body.setTransform(screenCenter(), 0f);

        }
    }
}
