package vooga.engine.event;

import com.golden.gamedev.object.SpriteGroup;

public interface IEventSpriteHandler {

	
	public boolean isTriggered(SpriteGroup...groups);
	
	/**
	 * User defines what to do after event has been triggered.
	 */
	public void actionPerformed(SpriteGroup...groups);
	
	
}
