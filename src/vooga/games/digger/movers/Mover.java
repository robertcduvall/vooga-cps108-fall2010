package vooga.games.digger.movers;

import vooga.engine.core.BetterSprite;

public interface Mover {
		
	public void move(long elapsedTime);
	
	public void setSprite(BetterSprite sprite);

}
