package vooga.engine.player.control.Event;

import vooga.engine.level.persist.Level;

public abstract class InGameEvent extends Event{

	Level myCurrentLevel;
	
	public InGameEvent(Level level)
	{
		setLevel(level);
	}
	
	public void setLevel(Level level)
	{
		myCurrentLevel = level;
	}

	public abstract void checkEvent();


	
	
}
