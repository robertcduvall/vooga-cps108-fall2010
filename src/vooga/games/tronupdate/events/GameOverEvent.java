package vooga.games.tronupdate.events;

import vooga.engine.core.Sprite;
import vooga.engine.event.IEventHandler;
import vooga.engine.state.GameState;
import vooga.engine.state.GameStateManager;
import vooga.games.tronupdate.items.TronPlayer2;

public class GameOverEvent implements IEventHandler {
	
	private GameState gameOverState;
	private GameStateManager gm;
	private Sprite player2Sprite;
	
	public GameOverEvent(Sprite player2Sprite,GameState gameOverState,GameStateManager gm){
		this.player2Sprite=player2Sprite;	
		this.gameOverState=gameOverState;
		this.gm=gm;
	}
	
	@Override
	public void actionPerformed() {
		gm.switchTo(gameOverState);
		player2Sprite.setSpeed(-1, -1);

	}

	@Override
	public boolean isTriggered() {

		return player2Sprite.getX()>700;
	}
	

}
