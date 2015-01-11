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

    public static Vector2 screenCenter() {
        return new Vector2((Gdx.graphics.getWidth()/2)/PIXELS_TO_METERS,
                (Gdx.graphics.getHeight()/2)/PIXELS_TO_METERS);
    }

    public static void checkBoundsReset(Body body){
        Transform bodyPosition = body.getTransform();
        Vector2 test = bodyPosition.getPosition();

        if (test.y < 0 || test.x < 1 ||
                test.x > Gdx.graphics.getWidth()/1 || test.y > Gdx.graphics.getHeight()/1) {
            body.setLinearVelocity(0f, 0f);
            body.setAngularVelocity(0f);
            body.setTransform(screenCenter(), 0f);
        }
    }
}
