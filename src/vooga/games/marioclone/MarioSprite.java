package vooga.games.marioclone;

import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
public class MarioSprite extends CharacterSprite {

	private static final int myMaxHealth = 100;
	private double jumpSpeed = 1;
	private double speed=.5;
	private boolean onGround = false;
	
	public MarioSprite(String name, String stateName, BufferedImage left,
			BufferedImage right) {
		super(name, stateName, left, right);
	}
	
	public void moveRight() {
		if (!isOnScreen()) {
			System.out.println("off screen");
			setHorizontalSpeed(0);
		} else {
			setHorizontalSpeed(speed);
		}
		setNewImage(rightImage);
		
	}

	public void moveLeft() {
		if (!isOnScreen()) {
			System.out.println("off screen");
			setHorizontalSpeed(0);
			setX(0);
		} else {
			setHorizontalSpeed(-speed);
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
		
		if(getX()<=0) {
			setX(0);
		}
	}

	@Override
	public Integer getMaxHealth() {
		return myMaxHealth;
	}
}
