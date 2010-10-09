package vooga.games.marioclone;

import java.awt.image.BufferedImage;

import vooga.engine.player.control.PlayerSprite;

import com.golden.gamedev.object.Sprite;

/*
 * @author Andrew Brown
 */

@SuppressWarnings("serial")
public class MarioSprite extends PlayerSprite {

	double gravity = .0025 ;
	double friction = .05;
	double dX = 5;
	double jumpSpeed = 1;
	double speed=.5;

	boolean onGround;
	
	BufferedImage rightImage;
	BufferedImage leftImage;

	public MarioSprite(String name, String stateName, BufferedImage left, BufferedImage right) {
		super(name, stateName, new Sprite(right));
		leftImage = left;
		rightImage = right;
	}
	

	public void moveRight() {
		if (isOnScreen()) {
			setHorizontalSpeed(speed);
		} else {
			setX(0);
		}
		setNewImage(rightImage);
	}

	public void moveLeft() {
		if (isOnScreen()) {
			setHorizontalSpeed(-speed);
		} else {
			setX(0);
		}
		setNewImage(leftImage);
	}

	public void jump() {
		if (isOnScreen()) {
			if (onGround) {
				setVerticalSpeed(-jumpSpeed);
				onGround = false;
			}
		} else {
			setY(0);
		}
	}

	public void crouch() {
		if (isOnScreen()) {
			// //setVerticalSpeed(-0.5);
			// setY(getY() + 5);
		} else {
			setY(0);
		}
	}
	
	public void stop() {
		setHorizontalSpeed(0);
	}

	public void setOnGround(boolean b) {
		onGround = b;
	}

	@Override
	public void update(long elapsedTime) {
		super.update(elapsedTime);
		onGround = false;
		setVerticalSpeed(getVerticalSpeed() + gravity * elapsedTime);

		/*
		 * double yVelocity = getVerticalSpeed(); double newYVelocity =
		 * yVelocity + gravity*elapsedTime; setVerticalSpeed(newYVelocity);
		 */

	}

}
