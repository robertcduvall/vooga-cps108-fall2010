package vooga.engine.player.control.Event;
//will change this package when this package is finalized
import vooga.engine.level.reader.Level;
import vooga.engine.player.control.GameEntitySprite;
import vooga.engine.player.control.ItemSprite;
import vooga.engine.player.control.PlayerSprite;

/* This is currently used to refer to Devon Townsend's work
 * Timer, Level specific events, bonues
 */

public abstract class LevelEvent extends Event{

	private Level myLevel;
	
	public LevelEvent(PlayerSprite gs, Level level) {
		super(gs);
		setToLevel(level);
		gs = (PlayerSprite) gs;
	}
	
	public LevelEvent(ItemSprite is, Level level)
	{
		super(is);
		setToLevel(level);
		is = (ItemSprite) is;
	}
	
	public LevelEvent(GameEntitySprite ge, Level level)
	{
		super(ge);
		setToLevel(level);
	}

	public void setToLevel(Level level)
	{
		myLevel = level;
	}
	
	//Here you want to specify how the level system can affect a particular type
	//of GameEntitySprite
	public abstract void act();

	
	
	
}
