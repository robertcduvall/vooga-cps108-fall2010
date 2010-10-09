package vooga.games.tron.foundations;
/**
 * This class handles the collision between player2 and the bonus
 * @author Meng Li,Brent Sodman,JiaQi Yan
 */
import vooga.games.tron.Players.TronPlayer;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class Player2AndBonusCollision extends BasicCollisionGroup {
	TronPlayer player2;
	
	public Player2AndBonusCollision(TronPlayer player2) {
		pixelPerfectCollision = true;
	    this.player2=player2;
	}
	/**
	 * determines what happens after the collision
	 */
	@Override
	public void collided(Sprite s1, Sprite s2) {
		player2.setSpeedUp(2);

	}


}


