package vooga.games.tronupdate.collisions;
/**
 * This class handles the collision between the boundary
 * @author Meng Li,Brent Sodman,JiaQi Yan
 */
import vooga.engine.core.Game;
import vooga.engine.resource.Resources;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionBounds;

public class PlayerAndBoundariesCollision extends CollisionBounds{
	
	private Game game;
	
	public PlayerAndBoundariesCollision(int x, int y, int width,
			int height,Game game){//,GameState gameOverState,GameStateManager gm) {
		super(x, y, width, height);
		this.game = game;

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
	}
}