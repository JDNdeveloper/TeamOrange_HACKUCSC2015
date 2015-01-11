package com.TeamOrange.NewSquareGame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Transform;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.lang.Math;

public class NewSquareGame extends ApplicationAdapter implements InputProcessor {
    SpriteBatch batch;
    Sprite squareSprite;
    Texture squareTexture;
    Sprite rectSprite;
    Texture rectTexture;
    Levels levels;

    ImageButton orangeButton;
    ImageButton blueButton;
    ImageButton pinkButton;
    ImageButton greenButton;

    BlockFactory BF;

    ImageButton pauseButton;
    ImageButton resetButton;
    CustomPhysics customPhysics;

    World world;
    Body body;
    Transform bodyPosition;
    Body bodyEdgeScreen;
    Box2DDebugRenderer debugRenderer;
    Matrix4 debugMatrix;
    OrthographicCamera camera;
    float screenWidth;
    float screenHeight;
    boolean paused;
    Star star;
    Sprite overlay;

    boolean drawSprite = true;

	@Override
	public void create () {
        customPhysics = new CustomPhysics();
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();

        Texture pauseTexture = new Texture("pause.png");
        Texture resetTexture = new Texture("reset.png");

        orangeButton = new ImageButton("orangeButton.png", Constants.buttonPaddingX, Constants.buttonPaddingY + Constants.diagonalButtonOffset);
        greenButton = new ImageButton("greenButton.png", Constants.buttonPaddingX*2 + orangeButton.getWidth(), Constants.buttonPaddingY);
        blueButton = new ImageButton("blueButton.png", screenWidth - Constants.buttonPaddingX - greenButton.getWidth(), Constants.buttonPaddingY + Constants.diagonalButtonOffset); //bad...
        pinkButton = new ImageButton("pinkButton.png", screenWidth - Constants.buttonPaddingX * 2 - blueButton.getWidth()*2, Constants.buttonPaddingY);

        resetButton = new ImageButton("reset.png", resetTexture.getWidth() + pauseTexture.getWidth() + 15, screenHeight - 1.5f*pauseTexture.getHeight() - 3);
        pauseButton = new ImageButton("pause.png", pauseTexture.getWidth() - 10, screenHeight - 1.5f*pauseTexture.getHeight());

        overlay = new Sprite(new Texture("pauseGradient.png"), (int)screenWidth, (int)screenHeight);

        bodyPosition = new Transform();

        batch = new SpriteBatch();
        squareTexture = new Texture("square.png");
        squareSprite = new Sprite(squareTexture);

        squareSprite.setPosition(-squareSprite.getWidth()/2,-squareSprite.getHeight()/2);

        world = new World(new Vector2(0, Constants.GRAVITY),true);


        levels = new Levels(world, batch);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(KeyClass.screenCenter());

        body = world.createBody(bodyDef);
        paused = false;

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(squareSprite.getWidth()/2 / Constants.PIXELS_TO_METERS, squareSprite.getHeight()
                /2 / Constants.PIXELS_TO_METERS);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.05f;
        fixtureDef.restitution = 0f;

        body.createFixture(fixtureDef);
        shape.dispose();

        /*rectTexture = new Texture("rect.png");
        rectSprite = new Sprite(rectTexture);

        rectSprite.setPosition(Gdx.graphics.getWidth()/2-rectSprite.getWidth()/2,20);

        BodyDef bodyDef2 = new BodyDef();
        bodyDef2.type = BodyDef.BodyType.StaticBody;
        bodyDef2.position.set(Gdx.graphics.getWidth() / 2 / Constants.PIXELS_TO_METERS, 40 / Constants.PIXELS_TO_METERS);
        FixtureDef fixtureDef2 = new FixtureDef();

        PolygonShape rect = new PolygonShape();
        rect.setAsBox(100 / Constants.PIXELS_TO_METERS, 20 / Constants.PIXELS_TO_METERS);
        fixtureDef2.shape = rect;
        fixtureDef2.restitution =0 ;

        bodyEdgeScreen = world.createBody(bodyDef2);
        bodyEdgeScreen.createFixture(fixtureDef2);
        rect.dispose();
        */

        star = new Star(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/1.5f,world);

        Gdx.input.setInputProcessor(this);

        // Create a Box2DDebugRenderer, this allows us to see the physics
        //simulation controlling the scene
        //debugRenderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.
                getHeight());
        camera.translate(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
	}

    private float elapsed = 0;
	@Override
	public void render () {

        if (!paused) {
            camera.update();
            // Step the physics simulation forward at a rate of 60hz
            world.step(Constants.TIME_STEP, Constants.VELOCITY_INTERATIONS, Constants.POSITION_ITERATIONS);
        }
        // Apply torque to the physics body.  At start this is 0 and will do
       // nothing.  Controlled with [] keys
        // Torque is applied per frame instead of just once
        body.applyTorque(Constants.torque,true);

        // Set the sprite's position from the updated physics body location
        squareSprite.setPosition((body.getPosition().x * Constants.PIXELS_TO_METERS) - squareSprite.
                        getWidth()/2 ,
                (body.getPosition().y * Constants.PIXELS_TO_METERS) -squareSprite.getHeight()/2 )
        ;
        // Ditto for rotation
        squareSprite.setRotation((float)Math.toDegrees(body.getAngle()));

        Gdx.gl.glClearColor(Constants.BRED, Constants.BGREEN, Constants.BBLUE, Constants.BALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        //debugMatrix = batch.getProjectionMatrix().cpy().scale(Constants.PIXELS_TO_METERS, Constants.PIXELS_TO_METERS, 0);

        //star.act();

        batch.begin();

//      batch.draw(new Texture(Constants.BACKGROUND),0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        levels.drawCurrentLevel();

        if(drawSprite)
            batch.draw(squareSprite, squareSprite.getX(), squareSprite.getY(),squareSprite.getOriginX(),
                    squareSprite.getOriginY(),
                    squareSprite.getWidth(),squareSprite.getHeight(),squareSprite.getScaleX(),squareSprite.
                            getScaleY(),squareSprite.getRotation());

        //star.draw(batch);

        batch.draw(orangeButton.getTexture(), orangeButton.getX(), orangeButton.getY());
        batch.draw(blueButton.getTexture(), blueButton.getX(), blueButton.getY());
        batch.draw(pinkButton.getTexture(), pinkButton.getX(), pinkButton.getY());
        batch.draw(greenButton.getTexture(), greenButton.getX(), greenButton.getY());

        batch.draw(resetButton.getTexture(), resetButton.getX(), resetButton.getY());
        batch.draw(pauseButton.getTexture(), pauseButton.getX(), pauseButton.getY());


        if (paused && overlay != null && batch != null) {
            batch.draw(overlay, 0f, 0f);
        }

        batch.end();

        // Now render the physics world using our scaled down matrix
        // Note, this is strictly optional and is, as the name suggests, just
        //for debugging purposes
        //debugRenderer.render(world, debugMatrix);
        KeyClass.checkBoundsReset(body);
	}

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.SPACE){
            body.setLinearVelocity(0f, 0f);
            body.setAngularVelocity(0f);
            squareSprite.setPosition(0f, 0f);
            body.setTransform(KeyClass.screenCenter(), 0f);
        }

        if (keycode == Input.Keys.UP){
            body.setAngularVelocity(1f);
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (pauseButton.mouseWithinRegion(screenX, screenY)) {
            paused = !paused;
        }
        if (resetButton.mouseWithinRegion(screenX, screenY)) {
            KeyClass.reset(body);
        }
        if (!paused) {
            if (orangeButton.mouseWithinRegion(screenX, screenY)) {//left
                customPhysics.applyForceInDirection(body, Constants.JUMPFORCE, (float) (body.getAngle() + Math.PI));
            } else if (greenButton.mouseWithinRegion(screenX, screenY)) {//left middle
                customPhysics.applyForceInDirection(body, Constants.JUMPFORCE, (float) (body.getAngle() + Math.PI / 2));
            } else if (pinkButton.mouseWithinRegion(screenX, screenY)) {//right middle
                customPhysics.applyForceInDirection(body, Constants.JUMPFORCE, (float) (body.getAngle() + 3 * Math.PI / 2));
            } else if (blueButton.mouseWithinRegion(screenX, screenY)) {//right
                customPhysics.applyForceInDirection(body, Constants.JUMPFORCE, body.getAngle());
            }
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
