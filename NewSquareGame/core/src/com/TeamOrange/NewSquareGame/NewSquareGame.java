package com.TeamOrange.NewSquareGame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

public class NewSquareGame extends ApplicationAdapter implements InputProcessor {
    SpriteBatch batch;
    Sprite squareSprite;
    Texture squareTexture;

    ImageButton orangeButton;
    ImageButton blueButton;
    ImageButton pinkButton;
    ImageButton greenButton;

    World world;
    Body body;
    Transform bodyPosition;
    Body bodyEdgeScreen;
    Box2DDebugRenderer debugRenderer;
    Matrix4 debugMatrix;
    OrthographicCamera camera;
    float screenWidth;
    float screenHeight;
    Vector2 jumpDir;

    final float GRAVITY = -6.0f;
    final float JUMPFORCE = 10.0f;

    final float buttonPaddingX = 32;
    final float buttonPaddingY = 32;
    final float diagonalButtonOffset = 96;

    float torque = 0.0f;
    boolean drawSprite = true;

    final float PIXELS_TO_METERS = 100f;

	@Override
	public void create () {
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();

        orangeButton = new ImageButton("orangeButton.png", buttonPaddingX, buttonPaddingY + diagonalButtonOffset);
        greenButton = new ImageButton("greenButton.png", buttonPaddingX*2 + orangeButton.getWidth(), buttonPaddingY);
        blueButton = new ImageButton("blueButton.png", screenWidth - buttonPaddingX - greenButton.getWidth(), buttonPaddingY + diagonalButtonOffset); //bad...
        pinkButton = new ImageButton("pinkButton.png", screenWidth - buttonPaddingX * 2 - blueButton.getWidth()*2, buttonPaddingY);

        jumpDir = new Vector2();
        bodyPosition = new Transform();

        batch = new SpriteBatch();
        squareTexture = new Texture("square.png");
        squareSprite = new Sprite(squareTexture);

        squareSprite.setPosition(-squareSprite.getWidth()/2,-squareSprite.getHeight()/2);

        world = new World(new Vector2(0, GRAVITY),true);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set((Gdx.graphics.getWidth()/2)/PIXELS_TO_METERS,(Gdx.graphics.getHeight()/2)/PIXELS_TO_METERS);

        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(squareSprite.getWidth()/2 / PIXELS_TO_METERS, squareSprite.getHeight()
                /2 / PIXELS_TO_METERS);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.1f;

        body.createFixture(fixtureDef);
        shape.dispose();

        BodyDef bodyDef2 = new BodyDef();
        bodyDef2.type = BodyDef.BodyType.StaticBody;

        float w = Gdx.graphics.getWidth()/PIXELS_TO_METERS;
        float h = Gdx.graphics.getHeight()/PIXELS_TO_METERS- 50/PIXELS_TO_METERS;

        bodyDef2.position.set(240/PIXELS_TO_METERS,0);
        FixtureDef fixtureDef2 = new FixtureDef();


        PolygonShape rect = new PolygonShape();
        rect.setAsBox(480 / PIXELS_TO_METERS, 20 / PIXELS_TO_METERS);
        fixtureDef2.shape = rect;

        bodyEdgeScreen = world.createBody(bodyDef2);
        bodyEdgeScreen.createFixture(fixtureDef2);
        rect.dispose();

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
		//Gdx.gl.glClearColor(0.9f, 0.9f, 0.9f, 1);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //batch.begin();
        //batch.draw(img, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        //batch.end();

        camera.update();
        // Step the physics simulation forward at a rate of 60hz
        world.step(1f/60f, 6, 2);

        // Apply torque to the physics body.  At start this is 0 and will do
       // nothing.  Controlled with [] keys
        // Torque is applied per frame instead of just once
        body.applyTorque(torque,true);

        // Set the sprite's position from the updated physics body location
        squareSprite.setPosition((body.getPosition().x * PIXELS_TO_METERS) - squareSprite.
                        getWidth()/2 ,
                (body.getPosition().y * PIXELS_TO_METERS) -squareSprite.getHeight()/2 )
        ;
        // Ditto for rotation
        squareSprite.setRotation((float)Math.toDegrees(body.getAngle()));

        Gdx.gl.glClearColor(0.9f, 0.9f, 0.9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);

        // Scale down the sprite batches projection matrix to box2D size
       // debugMatrix = batch.getProjectionMatrix().cpy().scale(PIXELS_TO_METERS,
        //        PIXELS_TO_METERS, 0);

        batch.begin();

        if(drawSprite)
            batch.draw(squareSprite, squareSprite.getX(), squareSprite.getY(),squareSprite.getOriginX(),
                    squareSprite.getOriginY(),
                    squareSprite.getWidth(),squareSprite.getHeight(),squareSprite.getScaleX(),squareSprite.
                            getScaleY(),squareSprite.getRotation());

        //System.out.println("square x: " + squareSprite.getX());
        //System.out.println("square y: " + squareSprite.getY());

        batch.draw(orangeButton.getTexture(), orangeButton.getX(), orangeButton.getY());
        batch.draw(blueButton.getTexture(), blueButton.getX(), blueButton.getY());
        batch.draw(pinkButton.getTexture(), pinkButton.getX(), pinkButton.getY());
        batch.draw(greenButton.getTexture(), greenButton.getX(), greenButton.getY());

        batch.end();

        // Now render the physics world using our scaled down matrix
        // Note, this is strictly optional and is, as the name suggests, just
        //for debugging purposes
        //debugRenderer.render(world, debugMatrix);
        checkBoundsReset();
	}

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.SPACE){
            body.setLinearVelocity(0f, 0f);
            body.setAngularVelocity(0f);
            squareSprite.setPosition(0f, 0f);
            body.setTransform(KeyClass.screenCenter(), 0f);
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
        jumpDir = new Vector2(10,0);
        if(screenX<screenWidth/4) {//left
            jumpDir.setAngle(body.getAngle());
            body.applyForceToCenter(jumpDir,true);
            System.out.println("1");
        }else if(screenX<screenWidth/2){//left middle
            jumpDir.setAngle(body.getAngle() + 90);
            body.applyForceToCenter(jumpDir,true);
            System.out.println("2");
        }else if(screenX<3*screenWidth/4){//right middle
            jumpDir.setAngle(body.getAngle() + 180);
            body.applyForceToCenter(jumpDir,true);
            System.out.println("3");
        }else{//right
            jumpDir.setAngle(body.getAngle() + 270);
            body.applyForceToCenter(jumpDir,true);
            System.out.println("4");
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

    public void checkBoundsReset(){
        bodyPosition = body.getTransform();
        Vector2 test = bodyPosition.getPosition();

        if (test.y < 0 || test.x < 0 || test.x/PIXELS_TO_METERS > screenWidth) {
            body.setLinearVelocity(0f, 0f);
            body.setAngularVelocity(0f);
            body.setTransform(KeyClass.screenCenter(), 0f);
        }
    }
}
