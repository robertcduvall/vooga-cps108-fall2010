package vooga.games.asteroids.sprites;

import vooga.engine.core.Sprite;
import vooga.engine.overlay.Stat;

public class Ship extends Sprite {
	
	private static double ROTATION_AMOUNT;
	private static double VELOCITY_REDUCTION;
	private static double THRUST_AMOUNT;
	
	private double myVelocity;
	private double myOrientation;
	
	public Ship(Stat<Integer> numLives)
	{
		myVelocity = 0;
		myOrientation = 0;
		ROTATION_AMOUNT = 1;
		VELOCITY_REDUCTION = .1;
		THRUST_AMOUNT = .5;
		setStat("lives", numLives);
	}
	
	public void rotateLeft(){
		myOrientation -= ROTATION_AMOUNT;
	}
	
	public void rotateRight(){
		myOrientation += ROTATION_AMOUNT;
	}
	
	public void thrust(){
		setSpeed(getHorizontalSpeed() + VELOCITY_REDUCTION, getVerticalSpeed() + VELOCITY_REDUCTION);
	}
	
	public void fire(){
	
	}
	
	@Override
	public void update(long elapsedTime){
		myVelocity -= VELOCITY_REDUCTION;
		setMovement(myVelocity, myOrientation);
		super.update(elapsedTime);
	}
}
