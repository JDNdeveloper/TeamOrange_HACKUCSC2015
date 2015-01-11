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
    final float padding = 120;

    int currentLevel = 0;

    SpriteBatch batch;
    World world;

    ArrayList<Level> levels;

    private class Level {
        BlockFactory BF;
        Star star;
        //Square square;

        public Level() {
            BF = new BlockFactory();
        }


    }

    public Levels(World worldNew, SpriteBatch batchNew) {
        world = worldNew;
        batch = batchNew;

        levels = new ArrayList<Level>();

        //creating level one
        Level l1 = new Level();

        l1.BF.makeRectangle(false,"rect.png", new Vector2(70 + BlockWidth*0 + padding, 70 + padding), world);
        l1.BF.makeRectangle(false, "rect.png", new Vector2(70 + BlockWidth*1 + padding, 70 + padding), world);
        l1.BF.makeRectangle(false, "rect.png", new Vector2(70 + BlockWidth*2 + padding, 70 + padding), world);
        l1.BF.makeRectangle(false, "rect.png", new Vector2(70 + BlockWidth*3 + padding, 70 + padding), world);

        l1.BF.makeRectangle(true, "rect.png", new Vector2(70 + BlockWidth*2 + padding, 70 + BlockHeight + padding), world);
        l1.BF.makeRectangle(true, "rect.png", new Vector2(70 + BlockWidth*2 + padding, 70 + BlockHeight + BlockWidth/2 + padding), world);

        l1.BF.makeRectangle(false, "rect.png", new Vector2(70 + BlockWidth*1 + 30 + padding, 70 + BlockHeight + 3*BlockWidth/2 + padding), world);
        l1.BF.makeRectangle(false, "rect.png", new Vector2(70 + BlockWidth*2 + 30 + padding, 70 + BlockHeight + 3*BlockWidth/2 + padding), world);

        //l1.square = new Square(world, 70 + BlockWidth*2 - 50, 70 + BlockHeight + 50);

        l1.star = new Star(70 + BlockWidth*2 + 45 + padding, 70 + BlockHeight*1 + 20 + padding, world);



        levels.add(l1);

    }

    public void drawCurrentLevel() {
        Level cLevel = levels.get(currentLevel);


        cLevel.BF.drawRects(batch);
        cLevel.star.act();
        cLevel.star.draw(batch);
    }



}
