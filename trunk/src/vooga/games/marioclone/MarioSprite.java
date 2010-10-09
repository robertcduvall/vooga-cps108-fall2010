package vooga.games.marioclone;

import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
public class MarioSprite extends CharacterSprite {

	double friction = .05;
	double dX = 5;
	double jumpSpeed = 1;
	double speed=.5;
	boolean onGround = false;
	
	public MarioSprite(String name, String stateName, BufferedImage left,
			BufferedImage right) {
		super(name, stateName, left, right);
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

	
	public void stop() {
		setHorizontalSpeed(0);
	}

	public void setOnGround(boolean b) {
		onGround = b;
	}


}
