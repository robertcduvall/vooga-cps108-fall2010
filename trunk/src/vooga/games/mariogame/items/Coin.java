package vooga.games.mariogame.items;

import java.awt.image.BufferedImage;

import vooga.engine.core.BetterSprite;

@SuppressWarnings("serial")
public class Coin extends Item {


	private long mySpawnedTime;
	private boolean isSpawned;
	private double mySpeed = 1;
	
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
