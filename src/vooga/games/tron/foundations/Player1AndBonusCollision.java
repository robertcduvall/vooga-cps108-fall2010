package vooga.games.tron.foundations;
/**
 * This class handles the collision between player1 and the bonus
 * @author Meng Li,Brent Sodman,JiaQi Yan
 */
import vooga.games.tron.Players.TronPlayer;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class Player1AndBonusCollision extends BasicCollisionGroup {
	TronPlayer player1;
	
	public Player1AndBonusCollision(TronPlayer player1) {
		pixelPerfectCollision = true;
		this.player1=player1;
	}
	/**
	 * determine what happens after the collision
	 */
    @Override
	public void collided(Sprite s1, Sprite s2) {
		player1.setSpeedUp(2);
		
	}
	

}
