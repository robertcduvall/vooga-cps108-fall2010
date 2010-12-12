package vooga.games.digger.movers;

import vooga.engine.core.BetterSprite;

public class DefaultMover implements Mover{
	
	BetterSprite sprite;

	@Override
	public void move(long elapsedTime) {
		sprite.forceX(sprite.getX()+1);
		
	}

	@Override
	public void setSprite(BetterSprite sprite) {
		this.sprite = sprite;
	}

}
