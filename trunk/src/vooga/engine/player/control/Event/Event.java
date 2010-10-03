package vooga.engine.player.control.Event;

import vooga.engine.player.control.GameEntitySprite;

/*
 * 
 */

public abstract class Event {

	private GameEntitySprite myGameEntityReference;
	
	public Event(GameEntitySprite ge)
	{
		myGameEntityReference = ge;
	}
	
	public GameEntitySprite getReference()
	{
		return myGameEntityReference;
	}
	
	public abstract void act();
}
