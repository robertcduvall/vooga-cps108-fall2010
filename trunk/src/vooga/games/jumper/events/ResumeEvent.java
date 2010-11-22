package vooga.games.jumper.events;

import java.awt.event.KeyEvent;

import vooga.engine.core.Game;
import vooga.engine.event.IEventHandler;
import vooga.engine.state.GameStateManager;
import vooga.games.jumper.DropThis;
import vooga.games.jumper.states.PausedGameState;
import vooga.games.jumper.states.PlayGameState;

public class ResumeEvent implements IEventHandler{

	private DropThis myGame;
	private PausedGameState myState;
	private GameStateManager myGSM;
	
	public ResumeEvent(Game myGame2, PausedGameState pausedState){
		myGame = (DropThis) myGame2;
		myState = pausedState;
		myGSM = myGame.getGameStateManager();
	}
	
	@Override
	public boolean isTriggered() {
		return myGame.keyPressed(KeyEvent.VK_R);
	}

	@Override
	public void actionPerformed() {
		System.out.println("resuming");
		myGSM.switchTo(myGSM.getGameState(1));	
	}

}

