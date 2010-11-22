package vooga.games.mariogame.items;

import java.awt.image.BufferedImage;

import vooga.engine.core.BetterSprite;

@SuppressWarnings("serial")
public class Coin extends BetterSprite {

	private long mySpawnedTime;
	private boolean isSpawned;
	private double mySpeed = 1;
	
	public Coin() {
		this(null);
	}
	
	public Coin(BetterSprite sprite, double absX, double absY) {
		setX(sprite.getX()+absX);
		setY(sprite.getY()+absY);
		setVerticalSpeed(sprite.getVerticalSpeed());
		setHorizontalSpeed(sprite.getHorizontalSpeed());
		setImage(sprite.getImage());
		setActive(sprite.isActive());
	}

	public Coin(BufferedImage s) {
		super(s);
	}

	public void spawn() {
		isSpawned = true;
		setVerticalSpeed(-mySpeed);
	}

	@Override
	public void update(long elapsedTime) {
		System.out.println(isSpawned);
		if(isSpawned) {
			mySpawnedTime+=elapsedTime;
			if (mySpawnedTime > 100) {
				setActive(false);
			}
		}
		super.update(elapsedTime);
	}

}
