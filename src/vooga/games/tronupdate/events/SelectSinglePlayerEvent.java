package vooga.games.tronupdate.events;

import java.awt.event.KeyEvent;

import vooga.engine.event.IEventHandler;
import vooga.engine.core.Game;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameStateManager;

public class SelectSinglePlayerEvent implements IEventHandler{
	private Game game;
	private GameStateManager gm;
	
	public SelectSinglePlayerEvent(Game g,GameStateManager manager){
		game = g;
		gm = manager;
	}
	
	
	public boolean isTriggered(){
		return game.keyPressed(KeyEvent.VK_S);
	}
	
	public void actionPerformed(){
		gm.switchTo(gm.getGameState(Resources.getInt("SetNumMatchesState")));
	}
}
