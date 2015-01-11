package com.TeamOrange.NewSquareGame;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;

/**
 * Created by Jayden Navarro on 1/10/2015.
 */
public class Levels {
    final float BlockWidth = 200;
    final float BlockHeight = 40;

    int currentLevel = 0;

    SpriteBatch batch;
    World world;

    ArrayList<Level> levels;

    private class Level {
        BlockFactory BF;
        Star star;
        //Square square;



    }

    public Levels(World worldNew, SpriteBatch batchNew) {
        world = worldNew;
        batch = batchNew;

        //creating level one
        Level l1 = new Level();

        l1.BF.makeRectangle(false,"rect", new Vector2(70 + BlockWidth*0, 70), world);
        l1.BF.makeRectangle(false, "rect.png", new Vector2(70 + BlockWidth*1, 70), world);
        l1.BF.makeRectangle(false, "rect.png", new Vector2(70 + BlockWidth*2, 70), world);
        l1.BF.makeRectangle(false, "rect.png", new Vector2(70 + BlockWidth*3, 70), world);
        l1.BF.makeRectangle(false, "rect.png", new Vector2(70 + BlockWidth*4, 70), world);

        l1.BF.makeRectangle(false, "rect.png", new Vector2(70 + BlockWidth*3 + BlockWidth/2, 70 + BlockHeight*1), world);
        l1.BF.makeRectangle(false, "rect.png", new Vector2(70 + BlockWidth*3 + BlockWidth/2, 70 + BlockHeight*2), world);

        l1.BF.makeRectangle(false, "rect.png", new Vector2(70 + BlockWidth*3, 70 + BlockHeight*3), world);
        l1.BF.makeRectangle(false, "rect.png", new Vector2(70 + BlockWidth*4, 70 + BlockHeight*3), world);

        l1.star = new Star(70 + BlockWidth*3 + BlockWidth/2 + 20, 70 + BlockHeight*1 + 5, world);



        levels.add(l1);

    }

    public void drawCurrentLevel() {
        Level cLevel = levels.get(currentLevel);
        cLevel.star.act();
        cLevel.star.draw(batch);
        cLevel.BF.drawRects(batch);
    }



}
