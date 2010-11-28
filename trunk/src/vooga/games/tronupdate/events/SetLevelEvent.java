package vooga.games.tronupdate.events;

import com.golden.gamedev.engine.input.AWTInput;

import vooga.engine.core.Game;
import vooga.engine.state.GameStateManager;
import vooga.engine.event.IEventHandler;
import vooga.games.tronupdate.util.GameStats;


public class SetLevelEvent implements IEventHandler{
	private GameStateManager gm;
	private Game game;
	private int Level;
	
	public SetLevelEvent(Game game,GameStateManager gm){
		this.game=game;
		this.gm=gm;
	}
	
	@Override
	public void actionPerformed(){
		GameStats.setLevel(Level);
		gm.switchTo(gm.getGameState(0));
	}
	
	public int getLevel(){
		if(game.checkPosMouse(0, 100, 100, 200))  return 1;
		if(game.checkPosMouse(0, 100, 200, 300)) return 2;
		if(game.checkPosMouse(0, 100, 300, 400)) return 3;
		if(game.checkPosMouse(0, 100, 400, 500)) return 4;
		if(game.checkPosMouse(0, 100, 500, 600)) return 5;
		else return -1;
	}
	
	@Override
	public boolean isTriggered(){
		Level = getLevel();
		return (game.bsInput.getMousePressed()>0 && getLevel()>0); 
	}
	
}
