package vooga.games.tron.foundations;
/**
 * This class handles the collision between the boundary
 * @author Meng Li,Brent Sodman,JiaQi Yan
 */
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionBounds;

class PlayerAndBoundariesCollision extends CollisionBounds{
	public PlayerAndBoundariesCollision(int x, int y, int width,
			int height) {
		super(x, y, width, height);
	}
	/**
	 * determines what happens after the collision
	 */
	@Override
	public void collided(Sprite s) {
		s.setActive(false); //this will prevent collided method being called many times after collision
	}
}