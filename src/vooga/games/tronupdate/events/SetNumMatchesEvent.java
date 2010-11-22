package vooga.games.tronupdate.events;

import com.golden.gamedev.engine.input.AWTInput;

import vooga.engine.core.Game;
import vooga.engine.state.GameStateManager;
import vooga.engine.event.IEventHandler;


public class SetNumMatchesEvent implements IEventHandler{
	private GameStateManager gm;
	private Game game;
	
	public SetNumMatchesEvent(Game game,GameStateManager gm){
		this.game=game;
		this.gm=gm;
	}
	
	@Override
	public void actionPerformed(){
		gm.switchTo(gm.getGameState(0));
	}
	
	public int getLevel(){
		int MouseX = game.bsInput.getMouseX();
		int MouseY = game.bsInput.getMouseY();
		return 1;
	}
	
	@Override
	public boolean isTriggered(){
		return (game.bsInput.getMousePressed()>0 && getLevel()>0); 
	}
	
}
