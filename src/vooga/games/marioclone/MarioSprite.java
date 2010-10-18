package vooga.games.marioclone;

import java.awt.image.BufferedImage;

import vooga.engine.player.control.ItemSprite;
import vooga.games.marioclone.items.GravityItem;

@SuppressWarnings("serial")
public class MarioSprite extends CharacterSprite {

	private static final int myMaxHealth = 3;
	private double jumpSpeed = 1;
	private double speed = .5;
	private boolean onGround = false;
	private double myMaxX;

	public MarioSprite(String name, String stateName, BufferedImage left,
			BufferedImage right) {
		super(name, stateName, left, right);		
	}

	public void moveRight() {
		setHorizontalSpeed(speed);
		setNewImage(myRightImage);

	}

	public void moveLeft() {
		setHorizontalSpeed(-speed);
		setNewImage(myLeftImage);
	}

	public void jump(boolean force) {
		if (isOnScreen()) {
			if (onGround || force) {
				setVerticalSpeed(-jumpSpeed);
				onGround = false;
			}
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
		double x = getX(); 
		if(x > myMaxX){
			myMaxX = x;
		}
		if(getHealth() <= 0)
			setActive(false);
		super.update(elapsedTime);
		stop();
		if (getX() <= 0) {
			setX(0);
		}
		int halfScreen = getBackground().getWidth()/2; 
		if((myMaxX-halfScreen) >= getX()){
			setX(myMaxX-halfScreen);
		}
	}

	public double getMaxX() {
		return myMaxX;
	}

	@Override
	public Integer getMaxHealth() {
		return myMaxHealth;
	}

	public void actOnItem(ItemSprite item) {
		if (item.getClass().equals(GravityItem.class)) {
			setGravityCoef(((GravityItem) item).getGravity());
		}
	}
}
