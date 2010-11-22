package vooga.games.tronupdate.events;

import java.awt.event.KeyEvent;

import vooga.engine.core.Game;
import vooga.engine.event.IEventHandler;
import vooga.engine.state.GameStateManager;

public class MenuSelectEvent implements IEventHandler{
	private GameStateManager gm;
	private Game game;


	public MenuSelectEvent(Game game,GameStateManager gm){
		this.game=game;
		this.gm=gm;
	}
	
	@Override
	public void actionPerformed() {
		//gm.getGameState(0).deactivate();
		gm.switchTo(gm.getGameState(0));//index 0 means PlayState
		System.out.println("Game is being Played!");
	}

	@Override
	public boolean isTriggered() 
	{		
		return game.keyPressed(KeyEvent.VK_SPACE);
	}
}	
