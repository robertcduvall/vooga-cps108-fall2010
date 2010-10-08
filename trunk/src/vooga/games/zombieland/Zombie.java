package vooga.games.zombieland;

import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Timer;

import vooga.engine.player.control.PlayerSprite;

public class Zombie extends PlayerSprite {

	private Shooter target;
	private double xTarget;
	private double yTarget;
	private String targetDirection;
	private double speed;

	public Zombie(String name, String stateName, AnimatedSprite down, AnimatedSprite up,
			 AnimatedSprite left, AnimatedSprite right, Shooter hero) {
		super(name, stateName, down);
		mapNameToSprite("Up", up);
		mapNameToSprite("Left", left);
		mapNameToSprite("Right", right);
		mapNameToSprite("Down", down);
		
		target = hero;
		targetDirection = "X";
		speed = -0.25;
		setHealth(25);
	}

	private double getDirection() {
		
		
		xTarget = target.getX();
		yTarget = target.getY();

		if (Math.abs(getX() - xTarget) >= Math.abs(getY() - yTarget)) {
			targetDirection = "X";
			return xTarget - getX();

		} else {
			targetDirection = "Y";
			return yTarget - getY();
		}
	}

	public void dead(){
//		setActive(false);
		target.updateScore(1);
	}

	public void update(long elapsedTime) {
		
		if(healthIsZero()) return;
		
		double direction = getDirection();
		if (targetDirection.equals("X")) {
			if (direction < 0) {
				moveX(speed);
				setToCurrentSprite("Left");
			} else {
				moveX(Math.abs(speed));
				setToCurrentSprite("Right");
			}
		} else {
			if (direction < 0) {
				moveY(speed);
				setToCurrentSprite("Up");
			} else {
				moveY(Math.abs(speed));
				setToCurrentSprite("Down");
			}
		}
		
		super.update(elapsedTime);
	}
	
	public void attackFrom(String fromSide)
	{
		setToCurrentSprite(fromSide);
		
		//how do you add a timer here?
		
	}
	
	
	public void calculateDamage(double damage)
	{
		updateHealth((int) damage);
		
	
	}
	
	public boolean healthIsZero()
	{
		return (getHealth() <= 0);
	}


		
	
	
}
