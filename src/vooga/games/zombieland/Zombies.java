package vooga.games.zombieland;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Sprite;

import vooga.engine.player.control.PlayerSprite;

public class Zombies extends PlayerSprite {

	private Shooter target;
	private double xTarget;
	private double yTarget;
	private String targetDirection;
	private double speed;

	public Zombies(String name, String stateName, Sprite s, Shooter hero) {
		super(name, stateName, s);
		target = hero;
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
			} else {
				moveX(Math.abs(speed));
			}
		} else {
			if (direction < 0) {
				moveY(speed);
			} else {
				moveY(Math.abs(speed));
			}
		}
	}
	
	private AnimatedSprite getInitializedAnimatedSprite(BufferedImage[] images){
		AnimatedSprite sprite = new AnimatedSprite(images);
		sprite.getAnimationTimer().setDelay(100);
		sprite.setAnimationFrame(0, images.length-1);
		sprite.setAnimate(true);
		sprite.setLoopAnim(true);
		return sprite;
	}
}
