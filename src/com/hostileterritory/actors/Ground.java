package com.hostileterritory.actors;

	import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.hostileterritory.Box2D.Constants;
import com.hostileterritory.Box2D.GroundData;

	public class Ground extends GameActors {
		
		 private final TextureRegion textureRegion;

		    private Rectangle textureRegionBounds1;
		    private Rectangle textureRegionBounds2;
		    private int speed = 10;

	    public Ground(Body body) {
	        super(body);
	        
	        textureRegion = new TextureRegion(new Texture(Gdx.files.internal(Constants.GROUND_IMAGE_PATH)));

	        textureRegionBounds1 = new Rectangle(0 - getUserData().getWidth() / 2, 0, getUserData().getWidth(),
	                getUserData().getHeight());
	        textureRegionBounds2 = new Rectangle(getUserData().getWidth() / 2, 0, getUserData().getWidth(),
	                getUserData().getHeight());	    
	    }
	    
	    @Override
	    public GroundData getUserData() {
	        return (GroundData) userData;
	    }
	    
	    @Override
	    public void act(float delta) {
	        super.act(delta);
	        if (leftBoundsReached(delta)) {
	            resetBounds();
	        } else {
	            updateXBounds(-delta);
	        }
	    }

	    @Override
	    public void draw(Batch batch, float parentAlpha) {
	        super.draw(batch, parentAlpha);
	        
	        // (jchung) There were no statements to draw the ground, so I copied them here from
	        //          Background.java. Also, scaled height of ground down by 8.
	        batch.draw(textureRegion, textureRegionBounds1.x, textureRegionBounds1.y, Constants.APP_WIDTH,
	                Constants.APP_HEIGHT/8);
	        batch.draw(textureRegion, textureRegionBounds2.x, textureRegionBounds2.y, Constants.APP_WIDTH,
	                Constants.APP_HEIGHT/8);
	    }

	    private boolean leftBoundsReached(float delta) {
	        return (textureRegionBounds2.x - transformToScreen(delta * speed)) <= 0;
	    }

	    private void updateXBounds(float delta) {
	        textureRegionBounds1.x += transformToScreen(delta * speed);
	        textureRegionBounds2.x += transformToScreen(delta * speed);
	    }

	    private void resetBounds() {
	        textureRegionBounds1 = textureRegionBounds2;
	        textureRegionBounds2 = new Rectangle(textureRegionBounds1.x + screenRectangle.width, 0, screenRectangle.width,
	                screenRectangle.height);
	    }
	}
