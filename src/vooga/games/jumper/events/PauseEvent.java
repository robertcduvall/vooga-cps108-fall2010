package vooga.games.jumper.events;

import java.awt.event.KeyEvent;

import vooga.engine.core.Game;
import vooga.engine.event.IEventHandler;
import vooga.engine.state.GameStateManager;
import vooga.games.jumper.DropThis;
import vooga.games.jumper.states.PlayGameState;

public class PauseEvent implements IEventHandler{

	private DropThis myGame;
	private GameStateManager myGSM;
	
	public PauseEvent(Game game, PlayGameState playState){
		myGame = (DropThis) game;
		myGSM = myGame.getGameStateManager();
	}
	
	@Override
	public boolean isTriggered() {
		return myGame.keyPressed(KeyEvent.VK_P);
		
	}

	@Override
	public void actionPerformed() {
		myGSM.activateOnly(myGSM.getGameState(0));//switch to the pause state
	}

}
