package vooga.games.marioclone;

import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
public class MarioSprite extends CharacterSprite {

	static final int DEFAULT_HEALTH = 100;
	private double jumpSpeed = 1;
	private double speed=.5;
	private boolean onGround = false;
	
	public MarioSprite(String name, String stateName, BufferedImage left,
			BufferedImage right) {
		super(name, stateName, left, right);
		setHealth(DEFAULT_HEALTH);
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

	public void jump(boolean force) {
		if (isOnScreen()) {
			if (onGround || force) {
				setVerticalSpeed(-jumpSpeed);
				onGround = false;
			}
		} else {
			setY(0);
		}
	}
	
	public void jumpCmd() {
		jump(false);
	}

	
	public void stop() {
		setHorizontalSpeed(0);
	}

	public void setOnGround(boolean b) {
		onGround = b;
	}
	
		
	@Override
	public void update(long elapsedTime) {
		if(getHealth() <= 0)
			setActive(false);
		
		super.update(elapsedTime);
		
		if(getX()<0) {
			setX(0);
		}
	}
}
