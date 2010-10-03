package vooga.engine.player.control;

public abstract class Event {

	GameEntitySprite myGameEntity;
	
	
	public Event(GameEntitySprite ge)
	{
		myGameEntity = ge;
	}
	
	public abstract void act();
}
