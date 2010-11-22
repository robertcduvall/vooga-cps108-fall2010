package vooga.games.tronupdate.events;

import java.awt.event.KeyEvent;

import vooga.engine.core.Game;
import vooga.engine.event.EventPool;
import vooga.engine.state.GameStateManager;
import vooga.engine.event.IEventHandler;
import vooga.engine.state.GameState;
import vooga.examples.state.PlayState;
import vooga.games.tronupdate.state.TronGamePlayState;

public class SwitchLevelEvent implements IEventHandler{
	private GameStateManager gm;
	private Game game;
	//private boolean trigger;
	private static int level=0;
	
	public SwitchLevelEvent(Game game, GameStateManager gm){
		this.game=game;
		this.gm=gm;
	}
	
	@Override
	public void actionPerformed() {
		GameState state = gm.getGameState(0);//playState
		TronGamePlayState playstate = (TronGamePlayState)state;
		playstate.setLevel(level%playstate.getTotalLevel());
		level++;
		state.removeEverything();
		state.initialize();
		
		gm.switchTo(state);
	}

	@Override
	public boolean isTriggered() {
		return game.keyPressed(KeyEvent.VK_SPACE); //press space to start the game or new level
	}
	
	

}
