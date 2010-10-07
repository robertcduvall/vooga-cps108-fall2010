package vooga.games.zombieland;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Sprite;

import vooga.engine.player.control.PlayerSprite;

public class Zombie1 extends PlayerSprite {

	private Shooter target;
	private double xTarget;
	private double yTarget;
	private String targetDirection;
	private double speed;
	private AnimatedSprite currentSprite;
	
	
	public Zombie1(String name, String stateName, AnimatedSprite up, 
			AnimatedSprite down, AnimatedSprite left, AnimatedSprite right, Shooter hero) {
		super(name, stateName, down);
		target = hero;
		this.mapNameToSprite("Up", up);
		this.mapNameToSprite("Left", left);
		this.mapNameToSprite("Right", right);
		this.mapNameToSprite("Down", down);
		currentSprite=down;
		targetDirection = "X";
		speed = -0.25;
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

	public void update(long elapsedTime) {
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
}
