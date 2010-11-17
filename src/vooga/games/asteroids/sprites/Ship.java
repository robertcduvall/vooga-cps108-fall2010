package vooga.games.asteroids.sprites;

import vooga.engine.core.Sprite;

public class Ship extends Sprite {
	
	private static int ROTATION_AMOUNT;
	private static int VELOCITY_REDUCTION;
	
	private double myVelocity;
	private double myOrientation;
	
	public Ship()
	{
		myVelocity = 0;
		myOrientation = 0;
	}
	
	public void rotateLeft(){
		myOrientation -= ROTATION_AMOUNT;
	}
	
	public void rotateRight(){
		myOrientation += ROTATION_AMOUNT;
	}
	
	public void thrust(){
		this.setMovement(myVelocity, myOrientation);
	}
	
	public void fire(){
	
	}
}
