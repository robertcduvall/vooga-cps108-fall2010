package vooga.games.tronupdate.events;

import java.awt.event.KeyEvent;

import vooga.engine.core.Game;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameStateManager;
import vooga.engine.event.IEventHandler;
import vooga.games.tronupdate.util.GameStats;


public class InvokeHelpEvent implements IEventHandler{
	private GameStateManager gm;
	private Game game;
	private int original;
	
	public InvokeHelpEvent(Game g,GameStateManager manager,int originalState){
		game = g;
		gm = manager;
		original = originalState;
	}
	
	@Override
	public boolean isTriggered(){
		return game.keyPressed(KeyEvent.VK_H);
	}
	
	@Override
	public void actionPerformed(){ 
		gm.switchTo(gm.getGameState(Resources.getInt("HelpState")));
		GameStats.setOriginalState(original);
		System.out.println("Going to the help page!");
	}
	
	
}


