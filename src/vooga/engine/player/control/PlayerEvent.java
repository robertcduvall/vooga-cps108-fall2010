package vooga.engine.player.control;


public class PlayerEvent extends Event{

	public PlayerEvent(PlayerSprite player)
	{
		super(player);
	}


	public void act() {
		//
		GameEntitySprite player = getReference();
		player = (PlayerSprite) player;
		
		
	}
	
	
	
	
}
