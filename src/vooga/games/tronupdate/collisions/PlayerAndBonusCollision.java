package vooga.games.tronupdate.collisions;
/**
 * This class handles the collision between player1 and the bonus
 * @author Meng Li,Brent Sodman,JiaQi Yan
 */
import vooga.games.tronupdate.items.TronPlayer;
import vooga.games.tronupdate.items.SpeedUpBonus;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class PlayerAndBonusCollision extends BasicCollisionGroup {
	//TronPlayer player1;
	
	public PlayerAndBonusCollision() {
		pixelPerfectCollision = true;
		//this.player1=player1;
	}
	/**
	 * determine what happens after the collision
	 */
    @Override
	public void collided(Sprite s1, Sprite s2) {
		SpeedUpBonus bonus = (SpeedUpBonus)s2;
		bonus.act();
		TronPlayer player = (TronPlayer)s1;
		player.setSpeedUp(1);
	}
	

}
