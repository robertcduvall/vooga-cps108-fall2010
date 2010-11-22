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
		
		if(isSingle()){
			Mode.setSinglePlayer();	
			gm.switchTo(gm.getGameState(4));//index 4 means setNumMatchesState
		}
		else if(isMultiple()){
			Mode.setMultiplePlayer(); 
			gm.switchTo(gm.getGameState(0));//index 0 means PlayState
		}
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
