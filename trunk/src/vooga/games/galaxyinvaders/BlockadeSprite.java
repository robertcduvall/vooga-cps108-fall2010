package vooga.games.galaxyinvaders;

import com.golden.gamedev.object.Sprite;
import vooga.engine.player.control.GameEntitySprite;

public class BlockadeSprite extends GameEntitySprite {

	private static final int DEFAULT_HP = 10;		
	private int hitPoints;

	public BlockadeSprite(String s, String name, Sprite spr) {
		super(s, name, spr);
		hitPoints = DEFAULT_HP;
	}

	public void update(long time) {
		super.update(time);
		if(hitPoints <= 0) {
			setActive(false);
		}
	}

	public void decrementHitPoints() {
		hitPoints--;
	}


}

