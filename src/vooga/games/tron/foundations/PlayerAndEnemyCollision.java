package vooga.games.tron.foundations;
/**
 * This class handles the player and enemy collision
 * @author Meng Li,Brent Sodman,JiaQi Yan
 */
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class  PlayerAndEnemyCollision extends BasicCollisionGroup {
	public PlayerAndEnemyCollision() {
		pixelPerfectCollision = true;
	}
	/**
	 * handles the collision
	 */
	public void collided(Sprite s1, Sprite s2) {	
		s1.setActive(false);   //  this doesn't work in our cases	
		s2.setActive(false);
		
	}
}
