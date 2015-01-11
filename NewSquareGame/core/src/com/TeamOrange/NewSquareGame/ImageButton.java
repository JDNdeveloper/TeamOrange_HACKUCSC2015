package com.TeamOrange.NewSquareGame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Jayden Navarro on 1/10/2015.
 */
public class ImageButton {
    Texture image;
    Sprite imageSprite;

    int mouseX;
    int mouseY;

    float imageX;
    float imageY;

    public ImageButton(String imageName, float iX, float iY) {

        imageX = iX;
        imageY = iY;

        image = new Texture(imageName);
        imageSprite = new Sprite(image);

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

    public float getWidth() {
        return image.getWidth();
    }

    public float getHeight() {
        return image.getHeight();
    }

    public Sprite getSprite() {
        return imageSprite;
    }

    public float getX() {
        return imageX;
    }

    public float getY() {
        return imageY;
    }

}
