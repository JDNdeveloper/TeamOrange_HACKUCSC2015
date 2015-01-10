package com.TeamOrange.NewSquareGame;

/**
 * Created by Jayden Navarro on 1/10/2015.
 */
public class ImageButton {
    float mouseX;
    float mouseY;

    float topLeftX;
    float topLeftY;
    float bottomRightX;
    float bottomRightY;

    public ImageButton(int tlX, int tlY, int brX, int brY) {
        topLeftX = tlX;
        topLeftY = tlY;
        bottomRightX = brX;
        bottomRightY = brY;
    }

    public boolean mouseWithinRegion(int X, int Y) {
        mouseX = X;
        mouseY = Y;

        if (!(mouseX < bottomRightX && mouseX > topLeftX)) return false; //if x not in bounds
        if (!(mouseY < topLeftY && mouseY > bottomRightY)) return false;

        return true;
    }
}
