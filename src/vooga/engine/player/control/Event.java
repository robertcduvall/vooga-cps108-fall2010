package vooga.engine.player.control;

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
