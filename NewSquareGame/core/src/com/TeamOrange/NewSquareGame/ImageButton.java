package com.TeamOrange.NewSquareGame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Jayden Navarro on 1/10/2015.
 */
public class ImageButton {
    Texture image;
    Sprite imageSprite;

    float mouseX;
    float mouseY;

    float imageX;
    float imageY;

    public ImageButton(String imageName, int iX, int iY) {

        imageX = iX;
        imageY = iY;

        image = new Texture(imageName);
        imageSprite = new Sprite(image);
        imageSprite.setPosition(imageX, imageY);
    }

    public boolean mouseWithinRegion(int X, int Y) {
        mouseX = X;
        mouseY = Y;

        float topRightX = image.getWidth() + imageX;
        float topRightY = image.getHeight() + imageY;


        if (!(mouseX < topRightX && mouseX > imageX)) return false; //if x not in bounds
        if (!(mouseY < topRightY && mouseY > imageY)) return false; //if y not in bounds

        return true;
    }
}
