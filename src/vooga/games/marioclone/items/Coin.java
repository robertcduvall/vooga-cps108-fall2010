package vooga.games.marioclone.items;

import vooga.engine.player.control.ItemSprite;

import com.golden.gamedev.object.Sprite;

@SuppressWarnings("serial")
public class Coin extends ItemSprite {

	private long mySpawnedTime;
	private boolean isSpawned;
	private double mySpeed = 1;

	public Coin(Sprite s) {
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
