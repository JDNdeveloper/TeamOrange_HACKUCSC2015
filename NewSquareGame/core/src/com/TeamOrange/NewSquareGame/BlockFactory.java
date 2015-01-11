package com.TeamOrange.NewSquareGame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;

/**
 * Created by Brad on 1/10/2015.
 */
public class BlockFactory {

    final float PIXELS_TO_METERS = 100f;
    ArrayList<Block> blocks = new ArrayList<Block>();

    private class Block {
        Texture texture;
        Sprite sprite;
        Body body;


        public Block(Texture tex, World world, Vector2 bottomLeftCorner) {
            texture = tex;
            sprite = new Sprite(texture);
            sprite.setPosition(bottomLeftCorner.x, bottomLeftCorner.y);
            BodyDef bodyDef = new BodyDef();
            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set(bottomLeftCorner.x, bottomLeftCorner.y);

            body = world.createBody(bodyDef);

            PolygonShape shape = new PolygonShape();
            shape.setAsBox(sprite.getWidth() / 2 / PIXELS_TO_METERS, sprite.getHeight()
                    / 2 / PIXELS_TO_METERS);

            FixtureDef fixtureDef = new FixtureDef();
            fixtureDef.shape = shape;
            fixtureDef.density = 0.1f;

            body.createFixture(fixtureDef);
            shape.dispose();
        }

    }

    public BlockFactory(){

    }

    public void makeRectangle(boolean vertical, String image, Vector2 bottomLeftCorner, World world) {
        Block block = new Block(new Texture("rect.png"), world, bottomLeftCorner);
        blocks.add(block);
    }

    public void drawRects(){
        
    }
}
