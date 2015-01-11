package com.TeamOrange.NewSquareGame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Brad on 1/10/2015.
 */
public class Star {

    Texture texture;
    Sprite sprite;
    Body body;
    float scale = 1f;
    boolean scaleFlip = false;
    float rotation = 0f;
  //  Particle particles[] = new Particle[10];
    float xPos;
    float yPos;
    World newWorld;
/*
    private class Particle {
        Texture texture;
        Sprite sprite;
        Body body;
        float scale = 1f;
        float move = 1 - (2 *(float) Math.random());

        public Particle(float x, float y, World world){
            xPos = x;
            yPos = y;
            newWorld = world;
            texture = new Texture("particle.png");
            sprite = new Sprite(texture);
            sprite.setPosition(x, y);
        }

        public float getScale(){
            return scale;
        }

        public Sprite getSprite(){
            return sprite;
        }

        public void setScale(float newScale){
            scale = newScale;
        }

        public float getX(){
            return xPos;
        }

        public float getY(){
            return yPos;
        }
        public void setPos(float newX, float newY){
            sprite.setPosition(newX, newY);
        }

        public float getMove(){
            return move;
        }
    }*/
    public Star(float x, float y, World world){

            texture = new Texture("star.png");
            sprite = new Sprite(texture);
            sprite.setPosition(x, y);
            /*BodyDef bodyDef = new BodyDef();
            bodyDef.type = BodyDef.BodyType.DynamicBody;
            bodyDef.position.set((x + sprite.getWidth() /2) / Constants.PIXELS_TO_METERS, (y + sprite.getHeight() / 2) / Constants.PIXELS_TO_METERS);

            body = world.createBody(bodyDef);

            CircleShape shape = new CircleShape();
            shape.setRadius(sprite.getWidth() / 2 / Constants.PIXELS_TO_METERS);

            FixtureDef fixtureDef = new FixtureDef();
            fixtureDef.shape = shape;
            fixtureDef.density = 0.1f;

            body.createFixture(fixtureDef);
            shape.dispose();

            for(int i =0;i<particles.length;i++) {
                particles[i] = new Particle((float) (x + Math.random() * sprite.getWidth()), y, world);
            }*/
    }

    public void act(){
        rotation++;
        if(scale<=1f&&!scaleFlip){
            scale -= 0.01f;
        }if(scale<0.6f){
            scaleFlip = true;
        }if(scaleFlip&&scale<1f){
            scale += 0.01f;
        }if(scale>=1f){
            scaleFlip = false;
        }/*
        for(int i=0;i<particles.length;i++){
            particles[i].setScale((float) (particles[i].getScale()-Math.random()/40));
            particles[i].setPos(particles[i].getX()+particles[i].getMove(),particles[i].getY()-0.5f);
            if(particles[i].getScale() < 0.1f){
                particles[i] = new Particle((float) (xPos + Math.random() * sprite.getWidth()), yPos, newWorld);
            }
        }*/
    }

    public void draw(SpriteBatch batch){
         batch.draw(sprite, sprite.getX(), sprite.getY(),sprite.getOriginX(),
                sprite.getOriginY(),
                sprite.getWidth(),sprite.getHeight(),scale,scale,rotation);/*
        for(int i =0;i<particles.length;i++) {
            batch.draw(particles[i].getSprite(),particles[i].getX(),particles[i].getY(),particles[i].getSprite().getOriginX(),particles[i].getSprite().getOriginX(),particles[i].getSprite().getWidth(),particles[i].getSprite().getHeight(),particles[i].getScale(),particles[i].getScale(),0);
        }*/
    }


}
