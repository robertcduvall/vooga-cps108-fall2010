package vooga.games.asteroids.sprites;

import vooga.engine.core.Sprite;

public class Ship extends Sprite {
	
	private static double ROTATION_AMOUNT;
	private static double VELOCITY_REDUCTION;
	
	private double myVelocity;
	private double myOrientation;
	
	public Ship()
	{
		myVelocity = 0;
		myOrientation = 0;
		ROTATION_AMOUNT = 1;
		VELOCITY_REDUCTION = .1;
	}
	
	public void rotateLeft(){
		myOrientation -= ROTATION_AMOUNT;
	}
	
	public void rotateRight(){
		myOrientation += ROTATION_AMOUNT;
	}
	
	public void thrust(){
		myVelocity++;
	}
	
	public void fire(){
	
	}
	
	@Override
	public void update(long elapsedTime){
		super.update(elapsedTime);
		myVelocity -= VELOCITY_REDUCTION;
		setMovement(myVelocity, myOrientation);
	}
}
