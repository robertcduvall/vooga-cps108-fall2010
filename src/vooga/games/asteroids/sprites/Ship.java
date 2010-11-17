package vooga.games.asteroids.sprites;

import vooga.engine.core.Sprite;

public class Ship extends Sprite {
	
	private static int ROTATION_AMOUNT;
	private static int VELOCITY_REDUCTION;
	
	private int myVelocity;
	private int myOrientation;
	
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
	
	}
	
	public void fire(){
	
	}
}
