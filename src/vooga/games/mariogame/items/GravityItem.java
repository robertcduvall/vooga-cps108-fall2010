package vooga.games.mariogame.items;

import java.awt.image.BufferedImage;

import vooga.engine.core.BetterSprite;

@SuppressWarnings("serial")
public class GravityItem extends BetterSprite {

	private double myGravity;
	
	public GravityItem(BetterSprite sprite, double absX, double absY) {
		this();
		setX(sprite.getX()+absX);
		setY(sprite.getY()+absY);
		setVerticalSpeed(sprite.getVerticalSpeed());
		setHorizontalSpeed(sprite.getHorizontalSpeed());
		setImage(sprite.getImage());
		setActive(sprite.isActive());
	}
	
	public GravityItem() {
		this(null,.5);
	}

	public GravityItem(BufferedImage s, double gravity) {
		super(s);
		myGravity = gravity;
	}

	public double getGravity() {
		return myGravity;
	}

	public void setGravity(double myGravity) {
		this.myGravity = myGravity;
	}
	
}
