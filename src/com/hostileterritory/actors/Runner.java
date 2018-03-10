package com.hostileterritory.actors;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.hostileterritory.Box2D.Constants;
import com.hostileterritory.Box2D.RunnerData;

	public class Runner extends GameActors {

		private boolean jumping;
		private boolean dodging;
		private boolean hit;
	    private Animation runningAnimation;
	    private TextureRegion jumpingTexture;
	    private TextureRegion dodgingTexture;
	    private TextureRegion hitTexture;
	    private float stateTime;
	    
		public Runner(Body body) {
	        super(body);
	        TextureAtlas textureAtlas = new TextureAtlas(Constants.RUNNING_ATLAS_PATH);
	        TextureRegion[] runningFrames = new TextureRegion[Constants.RUNNER_RUNNING_REGION_NAMES.length];
	        runningFrames = new TextureRegion[Constants.RUNNER_RUNNING_REGION_NAMES.length];
	        for (int i = 0; i < Constants.RUNNER_RUNNING_REGION_NAMES.length; i++) {
	            String path = Constants.RUNNER_RUNNING_REGION_NAMES[i];
	            runningFrames[i] = textureAtlas.findRegion(path);
	        }
	        runningAnimation = new Animation(0.1f, runningFrames);
	        stateTime = 0f;
	        jumpingTexture = textureAtlas.findRegion(Constants.JUMPING_ATLAS_PATH);
	        dodgingTexture = textureAtlas.findRegion(Constants.CROUNCHING_ATLAS_PATH);
	        hitTexture = textureAtlas.findRegion(Constants.DYING_ATLAS_PATH);
	    }

	    @Override
	    public void draw(Batch batch, float parentAlpha) {
	        super.draw(batch, parentAlpha);

	        if (dodging) {
	            batch.draw(dodgingTexture, screenRectangle.x, screenRectangle.y + screenRectangle.height / 4, screenRectangle.width,
	                    screenRectangle.height * 3 / 4);
	        } else if (hit) {
	            // When he's hit we also want to apply rotation if the body has been rotated
	            batch.draw(hitTexture, screenRectangle.x, screenRectangle.y, screenRectangle.width * 0.5f,
	                    screenRectangle.height * 0.5f, screenRectangle.width, screenRectangle.height, 1f, 1f,
	                    (float) Math.toDegrees(body.getAngle()));
	        } else if (jumping) {
	            batch.draw(jumpingTexture, screenRectangle.x, screenRectangle.y, screenRectangle.width,
	                    screenRectangle.height);
	        } else {
	            // Running
	            stateTime += Gdx.graphics.getDeltaTime();
	            // (jchung) screenRectangle.getWidth() & screenRectangle.getHeight() are both returning
	            //          0, so the runner was not being drawn.
	            // batch.draw(runningAnimation.getKeyFrame(stateTime, true), screenRectangle.x, screenRectangle.y,
	            //            screenRectangle.getWidth(), screenRectangle.getHeight());
	            // (jchung) Setting width and height to a value like 100 makes the runner show up.
	            batch.draw(runningAnimation.getKeyFrame(stateTime, true), screenRectangle.x, screenRectangle.y,
	                       100, 100);
	            
	        }
	    }
	    
	    //Character Controls
	    @Override
	    public RunnerData getUserData() {
	    	
	        return (RunnerData) userData;
	    }
	    
	    public void jump() {

	        if (!jumping) {
	            body.applyLinearImpulse(getUserData().getJumpingLinearImpulse(), body.getWorldCenter(), true);
	            jumping = true;
	        }
	    }
	    public void longjump(){
	    	if (!jumping) {
	    		body.applyForce(getUserData().getJumpingAngularImpulse(), body.getWorldCenter(), true );
	    		jumping = true;
	    	}
	    }
	    
	    public void landed() {
	        jumping = false;
	    }
	    
	    public void dodge() {
	        if (!jumping) {
	            body.setTransform(getUserData().getDodgePosition(), getUserData().getDodgeAngle());
	            dodging = true;
	        }
	    }

	    public void stopDodge() {
	        dodging = false;
	        body.setTransform(getUserData().getRunningPosition(), 0f);
	    }

	    public boolean isDodging() {
	        return dodging;
	    }
	    
	    public void hit() {
	        body.applyAngularImpulse(getUserData().getHitAngularImpulse(), true);
	        hit = true;
	    }

	    public boolean isHit() {
	        return hit;
	    }



	}

