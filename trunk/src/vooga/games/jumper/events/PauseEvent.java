package vooga.games.jumper.events;

import java.awt.event.KeyEvent;

import vooga.engine.event.IEventHandler;
import vooga.games.jumper.DropThis;
import vooga.games.jumper.states.PlayGameState;

public class PauseEvent implements IEventHandler{

	private DropThis myGame;
	private PlayGameState myState;
	
	public PauseEvent(DropThis jumper, PlayGameState playState){
		myGame = jumper;
		myState = playState;
	}
	
	@Override
	public boolean isTriggered() {
		return myGame.keyPressed(KeyEvent.VK_P);

	}

	@Override
	public void actionPerformed() {
		System.out.println("here");
		// TODO Auto-generated method stub
		
	}

}
