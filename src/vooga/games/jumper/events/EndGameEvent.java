package vooga.games.jumper.events;

import java.awt.event.KeyEvent;

import vooga.engine.core.Game;
import vooga.engine.event.IEventHandler;
import vooga.engine.state.BasicTextGameState;
import vooga.engine.state.GameStateManager;

public class EndGameEvent implements IEventHandler{

	private Game myGame;
	private GameStateManager myGSM;
	
	public EndGameEvent(Game game){
		myGame = game;
		myGSM = game.getGameStateManager();
	}
	
	
	@Override
	public boolean isTriggered() {
		return myGame.keyPressed(KeyEvent.VK_Q);
	}

	@Override
	public void actionPerformed() {
		System.out.println("game end");
		myGSM.activateOnly(myGSM.getGameState(0));
	}

}
