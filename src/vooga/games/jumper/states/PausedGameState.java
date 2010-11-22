package vooga.games.jumper.states;

import java.awt.Color;

import vooga.engine.core.Game;
import vooga.engine.event.EventPool;
import vooga.engine.state.GameState;
import vooga.engine.state.PauseGameState;
import vooga.games.jumper.events.ResumeEvent;

public class PausedGameState extends PauseGameState{

	private Game myGame;
	private EventPool myEventPool;
	
	public PausedGameState(GameState previousGameState, String pauseMessage,
			Color color, Game game) {
		super(previousGameState, pauseMessage, color);
		myGame = game;
	}
	
	@Override
	public void initialize(){
		myEventPool = new EventPool();
		
		myEventPool.addEvent(new ResumeEvent(myGame, this));
	}

	@Override
	public void update(long elapsedTime){
		super.update(elapsedTime);
		myEventPool.checkEvents();
	}
}
