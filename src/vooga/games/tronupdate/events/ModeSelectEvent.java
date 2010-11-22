package vooga.games.tronupdate.events;

import java.awt.event.KeyEvent;

import vooga.engine.core.Game;
import vooga.engine.event.IEventHandler;
import vooga.engine.state.GameStateManager;
import vooga.engine.state.GameState;
import vooga.games.tronupdate.util.Mode;

public class ModeSelectEvent implements IEventHandler{
	private GameStateManager gm;
	private Game game;
	private boolean single,multiple;


	public ModeSelectEvent(Game game,GameStateManager gm){
		this.game=game;
		this.gm=gm;
	}
	
	@Override
	public void actionPerformed() {
		gm.getGameState(2).removeEverything();
		gm.switchTo(gm.getGameState(0));//index 0 means PlayState
		
		if(isSingle()) Mode.setSinglePlayer();
		else if(isMultiple()) Mode.setMultiplePlayer(); 
	}
	
	private boolean isSingle(){
		return game.keyPressed(KeyEvent.VK_A);
	}
	private boolean isMultiple(){
		return game.keyPressed(KeyEvent.VK_B);
	}
	
	@Override
	public boolean isTriggered() 
	{		
		return (isSingle() || isMultiple());
	}
}	
