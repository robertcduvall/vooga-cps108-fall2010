package vooga.games.digger.shooters;

import vooga.engine.core.BetterSprite;

public interface Shooter {
	
	public void shoot(long elapsedTime);
	
	public void setSprite(BetterSprite sprite);

}
