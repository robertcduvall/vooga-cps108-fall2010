package vooga.engine.player.control;


public class PlayerEvent extends Event{

	public PlayerEvent(PlayerSprite player)
	{
		super(player);
	}


	/* Act specifies that if a particular event happens, then an event will 
	 * affect the player
	 * 
	 */
	
	public void act() {
		
		GameEntitySprite player = getReference();
		player = (PlayerSprite) player;
		
		//specify how this particular event will affect player's methods here. 
		//(i.e. player.setHealth(-3) or player.setScore(3);
	}
	
	
	
	
}
