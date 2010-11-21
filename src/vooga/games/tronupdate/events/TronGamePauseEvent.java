package vooga.games.tronupdate.events;

import java.awt.event.KeyEvent;

import vooga.engine.core.BetterSprite;
import vooga.engine.core.Game;
import vooga.engine.event.IEventHandler;
import vooga.engine.state.GameState;
import vooga.engine.state.GameStateManager;

public class TronGamePauseEvent implements IEventHandler {
	
	private GameState tronGamePauseState;
	private GameStateManager gm;
	private Game game;
	
	public TronGamePauseEvent(Game game,GameState tronGamePauseState,GameStateManager gm){
		this.game=game;
		this.tronGamePauseState=tronGamePauseState;
		this.gm=gm;
	}
	
	@Override
	public void actionPerformed() {
		gm.switchTo(tronGamePauseState);
	}

	@Override
	public boolean isTriggered() {
		return game.keyDown(KeyEvent.VK_SPACE);
	}
	

}
