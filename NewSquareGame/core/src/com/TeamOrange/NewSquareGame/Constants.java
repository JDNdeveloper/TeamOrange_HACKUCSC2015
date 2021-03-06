package com.TeamOrange.NewSquareGame;

/**
 * Created by jgemig on 1/10/2015.
 */
public class Constants {

    static final float GRAVITY = -6.0f;
    static final float JUMPFORCE = 10.0f;
    static final float REFLECTION = 2.5f;
    static final float ZERO_ANG_VELOCITY = 0f;
    static final float DEFAULT_ANG_VELOCITY = 5f;

    static final float TIME_STEP = 1f/60f;
    static final int   VELOCITY_INTERATIONS = 10;
    static final int   POSITION_ITERATIONS = 1;
    
    static final float buttonPaddingX = 32;
    static final float buttonPaddingY = 32;
    static final float diagonalButtonOffset = 96;

    static float torque = 0.0f;

    static final float PIXELS_TO_METERS = 100f;
    static final float HITBOX_IN_METERS = 1.3f;

    static final String BACKGROUND = "bg.png";
    static final float BRED = 192/255f; //227
    static final float BGREEN = 191/255f; //240
    static final float BBLUE = 121/255f; //155
    static final float BALPHA = 1f;
}
