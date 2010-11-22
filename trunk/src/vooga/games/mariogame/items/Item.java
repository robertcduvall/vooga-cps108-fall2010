package vooga.games.mariogame.items;

import vooga.engine.core.BetterSprite;

@SuppressWarnings("serial")
public class Item extends BetterSprite {
	protected String myType;
	protected long myLifetime;
	private long mySpawnedTime;
	private boolean isSpawned;
	
	public Item() {
		
	}
	
	public Item(Item sprite, double absX, double absY) {
		setX(sprite.getX()+absX);
		setY(sprite.getY()+absY);
		setVerticalSpeed(sprite.getVerticalSpeed());
		setHorizontalSpeed(sprite.getHorizontalSpeed());
		setImage(sprite.getImage());
		setActive(sprite.isActive());
		myType = sprite.getType();
		myLifetime = sprite.myLifetime;
	}
	
	public Item(String type) {
		myType = type;
	}

	public String getType() {
		return myType;
	}
	
	
	public void spawn() {
		isSpawned = true;
	}

	@Override
	public void update(long elapsedTime) {
		if(isSpawned && myLifetime>0) {
			mySpawnedTime+=elapsedTime;
			if (mySpawnedTime > myLifetime) {
				setActive(false);
			}
		}
		super.update(elapsedTime);
	}

	public void setLifetime(long lifetime) {
		myLifetime = lifetime;	
	}

	
}
