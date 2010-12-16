package vooga.games.digger.mod;

import vooga.engine.core.BetterSprite;
import vooga.games.digger.movers.Mover;

public class DownMover implements Mover{

	BetterSprite sprite;

	@Override
	public void move(long elapsedTime) {
		sprite.forceY(sprite.getY()+1);
		
	}

	@Override
	public void setSprite(BetterSprite sprite) {
		this.sprite = sprite;
	}
}
