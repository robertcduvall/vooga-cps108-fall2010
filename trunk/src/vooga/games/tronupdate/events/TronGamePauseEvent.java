package vooga.games.tronupdate.events;

import java.awt.event.KeyEvent;

import vooga.engine.core.BetterSprite;
import vooga.engine.core.Game;
import vooga.engine.event.IEventHandler;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.engine.state.GameStateManager;

public class TronGamePauseEvent implements IEventHandler {
	
	private GameStateManager gm;
	private Game game;


	public TronGamePauseEvent(Game game,GameStateManager gm){
		this.game=game;
		this.gm=gm;
	}
	
	@Override
	public void actionPerformed() {

		gm.switchTo(gm.getGameState(Resources.getInt("PauseState")));//index 1 means pauseState
		System.out.println("Game is paused!");
		
	}

	@Override
	public boolean isTriggered() 
	{		
		return game.keyPressed(KeyEvent.VK_P);
	}
	

}
