package vooga.engine.player.control.Event;

import vooga.engine.level.persist.Level;
import vooga.engine.player.control.GameEntitySprite;
import vooga.engine.player.control.ItemSprite;

public class ItemEvent extends GameEntityEvent{

	public ItemEvent(Level level, ItemSprite one, ItemSprite two) {
		super(level, one, two);
		one = (ItemSprite) one;
		two = (ItemSprite) two;
	}

	@Override
	public void checkEvent() {
	
		
	}

}
