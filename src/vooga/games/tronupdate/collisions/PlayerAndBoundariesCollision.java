package vooga.games.tronupdate.collisions;
/**
 * This class handles the collision between the boundary
 * @author Meng Li,Brent Sodman,JiaQi Yan
 */
import vooga.engine.core.Game;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.engine.state.GameStateManager;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionBounds;

import vooga.games.tronupdate.events.GameOverEvent;
import vooga.games.tronupdate.events.SwitchLevelEvent;
import vooga.games.tronupdate.items.TronPlayer;

public class PlayerAndBoundariesCollision extends CollisionBounds{
	
	private Game game;
	private GameStateManager gameStateManager;
	
	public PlayerAndBoundariesCollision(int x, int y, int width,
			int height,Game game,GameStateManager gameStateManager){//,GameState gameOverState,GameStateManager gm) {
		super(x, y, width, height);
		this.game = game;
		this.gameStateManager=gameStateManager;
	}
	/**
	 * determines what happens after the collision
	 */
	@Override
	public void collided(Sprite s) {
		//s.setSpeed(-0.2, -0.2);
		//s.setActive(false); //this will prevent collided method being called many times after collision
		//gm.switchTo(gameOverState);
		s.setActive(false);
		game.playSound(Resources.getSound("explosionSound"));
		TronPlayer tronplayer = (TronPlayer)s;
		if(tronplayer.isAI()){
			gameStateManager.switchTo(gameStateManager.getGameState(2));  //computer loses,switch to a new level			
		}
		else{
			gameStateManager.switchTo(gameStateManager.getGameState(3));//human loses game, start from menu.
		}
	}

}