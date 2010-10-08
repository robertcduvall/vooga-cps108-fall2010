package vooga.games.jumper;


import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;

/**
 * Collision detection for Player landing on blocks
 * @author Brian
 *
 */
public class DoodleToBlockCollision extends AdvanceCollisionGroup {
	
	public DoodleToBlockCollision(){
		pixelPerfectCollision = true;
	}

	/**
	 * React if there is a collision between player and block
	 * @param doodle DoodleSprite in SpriteGroup
	 * @param block BlockSprite in SpriteGroup
	 */
	@Override
	public void collided(Sprite doodle, Sprite block) {
		doodle.setVerticalSpeed(block.getVerticalSpeed());
	}
}
