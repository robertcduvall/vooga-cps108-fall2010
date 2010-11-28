package vooga.games.tronupdate.events;

import java.awt.event.KeyEvent;

import vooga.engine.core.Game;
import vooga.engine.event.IEventHandler;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameStateManager;
import vooga.engine.state.GameState;
import vooga.games.tronupdate.util.Mode;

public class ModeSelectEvent implements IEventHandler{
	private GameStateManager gm;
	private Game game;
	//private boolean single,multiple;


	public ModeSelectEvent(Game game,GameStateManager gm){
		this.game=game;
		this.gm=gm;
	}
	
	@Override
	public void actionPerformed() {
		if(isSingle()){
			Mode.setSinglePlayer();	
			gm.switchTo(gm.getGameState(Resources.getInt("SetLevelState")));//index 4 means setNumMatchesState
		}
		else if(isMultiple()){
			Mode.setMultiplePlayer(); 
			gm.switchTo(gm.getGameState(Resources.getInt("PlayState")));//index 0 means PlayState
		}
		else{
			Mode.setAI();
			gm.switchTo(gm.getGameState(0));
		}
	}
	
	private boolean isSingle(){
		return game.keyPressed(KeyEvent.VK_S);
	}
	private boolean isMultiple(){
		return game.keyPressed(KeyEvent.VK_M);
	}
	private boolean isAI(){
		return game.keyPressed(KeyEvent.VK_D);
	}
	
	@Override
	public boolean isTriggered() 
	{		
		return (isSingle() || isMultiple() || isAI());
	}
}	
