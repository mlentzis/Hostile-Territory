package com.hostileterritory.Box2D;

import com.badlogic.gdx.math.Vector2;
import com.hostileterritory.enums.UserDataType;

public class RunnerData extends UserData{
	
	private Vector2 jumpingLinearImpulse;
	private Vector2 jumpingAngularImpulse;
	private final Vector2 runningPosition = new Vector2(Constants.RUNNER_X, Constants.RUNNER_Y);
    private final Vector2 dodgePosition = new Vector2(Constants.RUNNER_DODGE_X, Constants.RUNNER_DODGE_Y);

    public RunnerData(float width, float height) {
        super(width, height);
        jumpingLinearImpulse = Constants.RUNNER_JUMPING_LINEAR_IMPULSE;
        jumpingAngularImpulse = Constants.RUNNER_JUMPING_ANGULAR_IMPULSE;
        userDataType = UserDataType.RUNNER;
    }

    public Vector2 getJumpingLinearImpulse() {
        return jumpingLinearImpulse;
    }
    
    public Vector2 getJumpingAngularImpulse(){
    	return jumpingAngularImpulse;
    }

    public void setJumpingLinearImpulse(Vector2 jumpingLinearImpulse) {
        this.jumpingLinearImpulse = jumpingLinearImpulse;
    }
    
    public void setJumpingAngularImpulse(Vector2 jumpingAngularImpulse){
    	this.jumpingAngularImpulse = jumpingAngularImpulse;
    }
    
    public float getDodgeAngle() {
        // In radians
        return (float) (-90f * (Math.PI / 180f));
    }

    public Vector2 getRunningPosition() {
        return runningPosition;
    }

    public Vector2 getDodgePosition() {
        return dodgePosition;
    }
    
    public float getHitAngularImpulse() {
        return Constants.RUNNER_HIT_ANGULAR_IMPULSE;
    }

}
