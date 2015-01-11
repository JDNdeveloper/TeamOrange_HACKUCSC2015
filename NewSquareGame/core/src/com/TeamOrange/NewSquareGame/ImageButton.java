package com.TeamOrange.NewSquareGame;

import com.badlogic.gdx.Gdx;
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

    public ImageButton(String imageName, float iX, float iY) {

        imageX = iX;
        imageY = iY;

        image = new Texture(imageName);
        imageSprite = new Sprite(image);
        imageSprite.setPosition(-imageSprite.getWidth()/2,-imageSprite.getHeight()/2);

    }

    public boolean mouseWithinRegion(float X, float Y) {
        mouseX = X;
        mouseY = Gdx.graphics.getHeight() - Y;

        float topRightX = image.getWidth() + imageX;
        float topRightY = image.getHeight() + imageY;

        //System.out.println("X "+ X + "Y " + mouseY);
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

    public Texture getTexture() {
        return image;
    }

    public float getX() {
        return imageX;
    }

    public float getY() {
        return imageY;
    }

}
