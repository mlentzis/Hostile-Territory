package com.hostileterritory.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.hostileterritory.Box2D.EnemyUserData;
import com.hostileterritory.Box2D.Constants;



public class MaleZombie extends GameActors{
	 
	 private Animation animation;
	    private float stateTime;

	
	    public MaleZombie(Body body) {
			super(body);
			TextureAtlas textureAtlas = new TextureAtlas(Constants.CHARACTERS_ATLAS_PATH);
			
			//Mike L. (Change)
			//TextureAtlas textureAtlas2 = new TextureAtlas(Constants.CHARACTERS_ATLAS_PATH2);
			
	        TextureRegion[] runningFrames = new TextureRegion[getUserData().getTextureRegions().length];
	        for (int i = 0; i < getUserData().getTextureRegions().length; i++) {
	            String path = getUserData().getTextureRegions()[i];
	            runningFrames[i] = textureAtlas.findRegion(path);
	            
	            //Mike L. (Change)
	           // runningFrames[i] = textureAtlas2.findRegion(path);
	        }
	        animation = new Animation(0.1f, runningFrames);
	        stateTime = 0f;
		}
	    
	    @Override
	    public void draw(Batch batch, float parentAlpha) {
	        super.draw(batch, parentAlpha);
	        stateTime += Gdx.graphics.getDeltaTime();
	        batch.draw(animation.getKeyFrame(stateTime, true), (screenRectangle.x - (screenRectangle.width * 0.1f)),
	                screenRectangle.y, screenRectangle.width * 1.2f, screenRectangle.height * 1.1f);
	    }
	 
	    @Override
	    public EnemyUserData getUserData() {
	        return (EnemyUserData) userData;
	    }
	    
	    @Override
	    public void act(float delta) {
	        super.act(delta);
	        body.setLinearVelocity(getUserData().getLinearVelocity());
	    }
	    
	    
	 
	
	public void pause() {
	 // TODO Auto-generated method stub
	 
	}

	public void resume() {
	 // TODO Auto-generated method stub
	 
	}


}
