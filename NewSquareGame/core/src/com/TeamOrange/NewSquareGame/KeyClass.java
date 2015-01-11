package com.TeamOrange.NewSquareGame;

/**
 * Created by jgemig on 1/10/2015.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

public class KeyClass {

    static final float PIXELS_TO_METERS = 100f;

    public static Vector2 screenCenter() {
        return new Vector2((Gdx.graphics.getWidth()/2)/PIXELS_TO_METERS,
                (Gdx.graphics.getHeight()/2)/PIXELS_TO_METERS);
    }
}
