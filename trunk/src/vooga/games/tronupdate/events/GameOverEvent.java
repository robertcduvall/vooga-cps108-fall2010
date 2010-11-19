package vooga.games.tronupdate.events;

import vooga.engine.core.BetterSprite;
import vooga.engine.event.IEventHandler;
import vooga.engine.state.GameState;
import vooga.engine.state.GameStateManager;

public class GameOverEvent implements IEventHandler {
	
	private GameState gameOverState;
	private GameStateManager gm;
	private BetterSprite player2Sprite;
	
	public GameOverEvent(BetterSprite player2Sprite,GameState gameOverState,GameStateManager gm){
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
