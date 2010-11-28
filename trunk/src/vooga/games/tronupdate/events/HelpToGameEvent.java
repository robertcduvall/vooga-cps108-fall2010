package vooga.games.tronupdate.events;

import java.awt.event.KeyEvent;

import vooga.engine.core.Game;
import vooga.engine.state.GameStateManager;
import vooga.engine.event.IEventHandler;
import vooga.games.tronupdate.util.GameStats;

public class HelpToGameEvent implements IEventHandler{
	private GameStateManager gm;
	private Game game;
	
	public HelpToGameEvent(Game g, GameStateManager manager){
		game = g;
		gm = manager;
	}
	
	@Override
	public boolean isTriggered(){
		return game.keyPressed(KeyEvent.VK_SPACE);
	}
	
	@Override
	public void actionPerformed(){
		int original = GameStats.getOriginalState().getStat();
		gm.switchTo(gm.getGameState(original)); //switch to playstate
		System.out.println(original);
		System.out.println("Game Resumed!");
	}
	
}
