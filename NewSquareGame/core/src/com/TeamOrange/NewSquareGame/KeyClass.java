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

    public static void checkBoundsReset(Square square){
        Transform bodyPosition = square.getTransform();
        Vector2 test = bodyPosition.getPosition();
//        Vector2 linearVelocity = square.getLinearVelocity();

        if (test.y < 0 || test.x < 0 || test.x > screenWidthMeters) {
           reset(square);
        }
        if  (test.y > screenHeightMeters){
            square.applyForceToCenter(0f, -Constants.REFLECTION, true);
        }
        /*
        if  (test.y < 0){
            body.applyForceToCenter(0f, Constants.REFLECTION, true);
        }
        if  (test.x > screenWidthMeters){
            body.applyForceToCenter(-Constants.REFLECTION, 0f, true);
        }
        if  (test.x < 0){
            body.applyForceToCenter(Constants.REFLECTION, 0f, true);
        }*/
    }

    public static void reset(Square square) {
        square.setLinearVelocity(0f, 0f);
        square.setAngularVelocity(Constants.ZERO_ANG_VELOCITY);
        square.setTransform(new Vector2((square.x + square.getWidth() /2) / Constants.PIXELS_TO_METERS, (square.y + square.getHeight() /2) / Constants.PIXELS_TO_METERS), 0f);
    }
}
