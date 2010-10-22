package vooga.games.zombieland;

import vooga.engine.overlay.OverlayCreator;
import vooga.engine.overlay.OverlayTracker;
import vooga.engine.state.GameState;

public class ZombielandPauseState extends GameState{


	private OverlayTracker tracker;
	
	public ZombielandPauseState(Blah game)
	{
		super();
		OverlayCreator.setGame(game);
		tracker = OverlayCreator.createOverlays("src/vooga/games/zombieland/resources/overlays.xml");
		
	}
	
	
	@Override
	public void initialize()
	{
		
		
		
		
		
		
		
		
	}
	
	
}
