package vooga.games.tronupdate.events;

import java.awt.event.KeyEvent;

import vooga.engine.core.Game;
import vooga.engine.event.IEventHandler;
import vooga.engine.state.GameStateManager;

public class GameOverEvent implements IEventHandler {
	
	private GameStateManager gm;
	private Game game;
	
	public GameOverEvent(Game game, GameStateManager gm){
		this.game=game;
		this.gm=gm;
	}
	
	@Override
	public void actionPerformed() {
		gm.switchTo(gm.getGameState(0));//0 index is playstate 
		System.out.println("Game is restarted!");
	}

	@Override
	public boolean isTriggered() {
		return game.keyPressed(KeyEvent.VK_SPACE);
	}

}
