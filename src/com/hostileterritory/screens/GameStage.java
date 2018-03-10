package com.hostileterritory.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hostileterritory.actors.Background;
import com.hostileterritory.actors.Ground;
import com.hostileterritory.actors.Runner;
import com.hostileterritory.Box2D.BodyUtils;
import com.hostileterritory.Box2D.Box2DCreate;
import com.hostileterritory.Box2D.Constants;
import com.hostileterritory.screens.*;
import com.hostileterritory.game.HostileTerritory;
import com.hostileterritory.actors.MaleZombie;
import com.hostileterritory.Box2D.BodyUtils;
import com.hostileterritory.Box2D.Box2DCreate;



public class GameStage extends Stage implements ContactListener, Screen, InputProcessor{

	private static final int VIEWPORT_WIDTH = Constants.APP_WIDTH;
	private static final int VIEWPORT_HEIGHT = Constants.APP_HEIGHT;
	    

	//World and Ground
	private World world;
	private Ground ground;
	private Runner runner;
	private HostileTerritory game;
	private Viewport viewport;
	// (jchung) GameStage is a Stage; it shouldn't have a Stage in it, so
	//          I'm commenting this out.
    // private Stage stage;


	public final short CATEGORY_WORLD = 0x0008;
	public final short CATEGORY_LIFE = 0x0032;
	public final short CATEGORY_RUNNER = 0x0001;


	//Time steps for game time
	private final float TIME_STEP = 1 / 300f;
	private float accumulator = 0f;

	//Game Camera and renderer
	private OrthographicCamera camera;
	//private Box2DDebugRenderer renderer;

	public GameStage(HostileTerritory game2) {
		//super(new ScalingViewport(Scaling.stretch, VIEWPORT_WIDTH, VIEWPORT_HEIGHT,
        // new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT)));
	        
	    this.viewport = new FitViewport(HostileTerritory.V_WIDTH, HostileTerritory.V_HEIGHT, new OrthographicCamera());
		// (jchung) GameStage is a Stage; it shouldn't have a Stage in it, so
	    //          I'm commenting this out.
	    // stage = new Stage(viewport, game2.batch);
	    this.game = game2;
	    
		setUpWorld();
		// setUpGround(); // commented because setUpWorld() calls this already (jchung)
		// setUpRunner(); // commented because setUpWorld() calls this already (jchung)

		//renderer = new Box2DDebugRenderer();
		setupCamera();
	}
	
	private void setupControlArea() {
		
        Gdx.input.setInputProcessor(this);
    }
	
	private void setUpWorld() {
		world = Box2DCreate.createWorld();
		world.setContactListener(this);
		setUpBackground();
		setUpGround();
		setUpRunner();
		setupControlArea();
		createEnemy();
	}
	
	 private void setUpBackground() {
	        addActor(new Background());
	    }
	 
	private void setUpGround() {
		ground = new Ground(Box2DCreate.createGround(world));
		addActor(ground);
	}

	private void setUpRunner() {
		runner = new Runner(Box2DCreate.createRunner(world));
		addActor(runner);
	}

	//Camera setup for game view
	private void setupCamera() {
		camera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
		camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);
		camera.update();
	}
	
	
	//Method to run through game time
	@Override
	public void act(float delta) {
		super.act(delta);

		 Array<Body> bodies = new Array<Body>(world.getBodyCount());
	        world.getBodies(bodies);
	        
	        for (Body body : bodies) {
	            update(body);
	        }
	        
		// Fixed time step
		accumulator += delta;

		while (accumulator >= delta) {
			world.step(TIME_STEP, 6, 2);
			accumulator -= TIME_STEP;
		}

	}

	private void update(Body body) {
        if (!BodyUtils.bodyInBounds(body)) {
            if (BodyUtils.bodyIsEnemy(body) && !runner.isHit()) {
                createEnemy();
            }
            world.destroyBody(body);
        }
    }
	
	 private void createEnemy() {
	        MaleZombie enemy = new MaleZombie(Box2DCreate.createEnemy(world));
	        addActor(enemy);
	    }


	

	public void setUpControls(){
		if(Gdx.input.isKeyJustPressed(Input.Keys.UP)){
			//smaller jump
			runner.jump();


			if(Gdx.input.isKeyPressed(Input.Keys.UP))
				//Float until limit reached
				runner.longjump();	
		}
		else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			runner.dodge();
		}
	}
	@Override
    public void beginContact (Contact contact) {

        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();

        if ((BodyUtils.bodyIsRunner(a) && BodyUtils.bodyIsEnemy(b)) ||
                (BodyUtils.bodyIsEnemy(a) && BodyUtils.bodyIsRunner(b))) {
            runner.hit();
        } else if ((BodyUtils.bodyIsRunner(a) && BodyUtils.bodyIsGround(b)) ||
                (BodyUtils.bodyIsGround(a) && BodyUtils.bodyIsRunner(b))) {
            runner.landed();
        }

    }
	
	@Override
	public void endContact(Contact contact) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
	}

	@Override
	public void render(float delta) {
		// jchung added code to clear screen and draw this stage;
		// this was the reason for the game screen not starting.
		Gdx.gl.glClearColor(255,255,255,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		draw();		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}
