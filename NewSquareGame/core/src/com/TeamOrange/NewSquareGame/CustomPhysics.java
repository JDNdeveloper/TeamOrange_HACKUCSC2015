package com.TeamOrange.NewSquareGame;

import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by Brad on 1/10/2015.
 */
public class CustomPhysics {
    public void applyForceInDirection(Square square, float force, float direction){
        float y = (float) (force * Math.sin(direction));
        float x = (float) (force * Math.cos(direction));
        square.applyForceToCenter(x, y, true);
    }
}
