package vooga.engine.player.control;

public abstract class Event {

	private GameEntitySprite myGameEntity;
	
	public Event(GameEntitySprite ge)
	{
		myGameEntity = ge;
	}
	
	public GameEntitySprite getReference()
	{
		return myGameEntity;
	}
	
	public abstract void act();
}
