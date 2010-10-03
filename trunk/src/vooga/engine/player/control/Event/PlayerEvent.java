package vooga.engine.player.control.Event;

import vooga.engine.level.persist.Level;
import vooga.engine.player.control.GameEntitySprite;
import vooga.engine.player.control.PlayerSprite;


public abstract class PlayerEvent extends GameEntityEvent{

	public PlayerEvent(Level level, PlayerSprite one, PlayerSprite two) {
		super(level, one, two);
		one = (PlayerSprite) one;
		two = (PlayerSprite) two;
		
	}

	
	public abstract void checkEvent();


	
	
}
