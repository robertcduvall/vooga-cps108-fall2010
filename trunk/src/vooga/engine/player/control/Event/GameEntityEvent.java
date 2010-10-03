package vooga.engine.player.control.Event;

import vooga.engine.level.persist.Level;
import vooga.engine.player.control.GameEntitySprite;

public abstract class GameEntityEvent extends InGameEvent{

	private GameEntitySprite myGameEntityOne;
	private GameEntitySprite myGameEntityTwo;
	
	public GameEntityEvent(Level level, GameEntitySprite one, GameEntitySprite two) {
		super(level);
		myGameEntityOne = one;
		myGameEntityTwo = two;
		
	}

	public abstract void checkEvent();
	
	

}
