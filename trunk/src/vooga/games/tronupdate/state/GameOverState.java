package vooga.games.tronupdate.state;

import vooga.engine.core.Game;
import vooga.engine.event.EventPool;
import vooga.engine.state.GameState;
import vooga.engine.state.GameStateManager;
import vooga.games.tronupdate.events.GameOverEvent;
import vooga.games.tronupdate.events.TronGameResumeEvent;

public class GameOverState extends GameState{

	private GameStateManager gameStateManager;
	private EventPool eventPool;
	private Game game;
	
	public GameOverState(Game game, GameStateManager gameStateManager){
		this.gameStateManager = gameStateManager;
		this.game=game;
	}
	
	@Override
	public void initialize() {
		eventPool = new EventPool();
		GameOverEvent gameOverEvent = new GameOverEvent(game,gameStateManager);
		eventPool.addEvent(gameOverEvent);
	}

	

	public void update(long elapsedTime){
		super.update(elapsedTime);
	//	playerControl.update();
		eventPool.checkEvents();
	}
}
