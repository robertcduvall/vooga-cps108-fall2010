package vooga.games.jumper;


import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;

/**
 * Collision detection for Player landing on blocks
 * @author Brian
 *
 */
public class DoodleToBlockCollision extends AdvanceCollisionGroup {

	/**
	 * Create new Collision constructor
	 */
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

		System.out.println(super.getCollisionSide());
		if(super.getCollisionSide() == 8){
		doodle.setVerticalSpeed(block.getVerticalSpeed());
		}
		//How to disallow horizontally walking through blocks?  Btw, is there a more elegant way to
		//allow different actions based on different vals of getCollisionSide()?
		/* else if(super.getCollisionSide() == 2){
			doodle.setHorizontalSpeed(1);
		} else if(super.getCollisionSide() == 4){
			doodle.setHorizontalSpeed(-1);
		}
		*/
	}
	
	
}
