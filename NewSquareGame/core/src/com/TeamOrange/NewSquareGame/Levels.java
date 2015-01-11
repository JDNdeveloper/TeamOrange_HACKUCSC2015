package com.TeamOrange.NewSquareGame;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * Created by Jayden Navarro on 1/10/2015.
 */
public class Levels {
    ArrayList<Level> levels;

    private class Level {
        BlockFactory BF;
        Star star;
        //Square square;



    }

    public Levels() {
        //creating level one
        Level l1 = new Level();

        //l1.BF.makeRectangle("rect.png", new Vector2(40, 40));
        //l1.BF.makeRectangle("rectVert.png", new Vector2());

        levels.add(l1);

    }



}
