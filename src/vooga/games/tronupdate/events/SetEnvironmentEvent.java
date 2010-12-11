package vooga.games.tronupdate.events;

import vooga.engine.core.Game;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameStateManager;
import vooga.engine.event.IEventHandler;
import vooga.games.tronupdate.util.Mode;

public class SetEnvironmentEvent implements IEventHandler{
	private GameStateManager gm;
	private Game game;
	private int Environment;
	
	public SetEnvironmentEvent(Game g,GameStateManager gm){
		this.gm=gm;
		game=g;
	}
	
	@Override
	public void actionPerformed(){
		Mode.setEnvironment(Environment);
		System.out.println("Environment Selected: "+Environment);
		gm.switchTo(gm.getGameState(Resources.getInt("PlayState")));
	}
	
	public int getEnvironment(){
		if(game.checkPosMouse(0, 100, 100, 200))  return 1;//classic
		if(game.checkPosMouse(0, 100, 100, 200))  return 2;//block environment
		if(game.checkPosMouse(0, 100, 100, 200))  return 3;//maze environment
		else return -1;
	}
	
	
	@Override
	public boolean isTriggered(){
		Environment = getEnvironment();
		return (game.bsInput.getMousePressed()>0 && getEnvironment()>0); 
	}
	
}
