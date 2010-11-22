package vooga.games.tronupdate.events;

import java.awt.event.KeyEvent;

import vooga.engine.core.Game;
import vooga.engine.event.EventPool;
import vooga.engine.state.GameStateManager;
import vooga.engine.event.IEventHandler;
import vooga.engine.state.GameState;
import vooga.examples.state.PlayState;

public class SwitchLevelEvent implements IEventHandler{
	private GameStateManager gm;
	private Game game;
	//private boolean trigger;
	
	public SwitchLevelEvent(Game game, GameStateManager gm){
		this.game=game;
		this.gm=gm;
	}
	@Override
	public void actionPerformed() {
		GameState state = gm.getGameState(0);//playState
		state.removeEverything();
		state.initialize();
		gm.switchTo(state);
	}

	@Override
	public boolean isTriggered() {
		return true;
	}
	
	

}
