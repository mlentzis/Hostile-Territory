package com.hostileterritory.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ScrollingBackground {
	public static final int DEFAULT_SPEED = 80;
	public static final int ACCELERATION = 50;
	public static final int GOAL_REACH_ACCELERATION = 200;
	
	Texture image;
	float x1, x2;
	int speed; // In pixels / second
	int goalSpeed;
	float imageScale;
	boolean speedFixed;
	
	public ScrollingBackground () {
		image = new Texture("HostileTerritoryBackground.jpg");
		
		x1 = 0;
		x2 = image.getWidth();
		speed = 0;
		goalSpeed = DEFAULT_SPEED;
		imageScale = 0;
		speedFixed = true;
		
	}
	
	public void updateAndRender (float deltaTime, SpriteBatch batch) {
		//Speed adjustment to reach goal
		if (speed < goalSpeed) {
			speed += GOAL_REACH_ACCELERATION * deltaTime;
			if (speed > goalSpeed)
				speed = goalSpeed;
		} else if (speed > goalSpeed) {
			speed -= GOAL_REACH_ACCELERATION * deltaTime;
			if (speed < goalSpeed)
				speed = goalSpeed;
		}
		
		if (!speedFixed)
			speed += ACCELERATION  * deltaTime;
		
		x1 -= speed * deltaTime;
		x2 -= speed * deltaTime;
		
		//if image reaches end of screen and is not visible, put it back
		if (x1 + image.getWidth() * imageScale <= 0)
			x1 = x2 + image.getWidth() * imageScale;
		
		if (x2 + image.getWidth()* imageScale <= 0)
			x2 = x1 + image.getWidth() * imageScale;
		
		//Render
		batch.draw(image, x1, Gdx.graphics.getHeight(), image.getWidth() * imageScale, 0);
		batch.draw(image, x2, Gdx.graphics.getHeight(), image.getWidth() * imageScale, 0);
	}
	
	public void resize (int width, int height) {
		imageScale = height / image.getHeight();
	}
	
	public void setSpeed (int goalSpeed) {
		this.goalSpeed = goalSpeed;
	}
	
	public void setSpeedFixed (boolean speedFixed) {
		this.speedFixed = speedFixed;
	}
}
