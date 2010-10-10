package vooga.games.jumper;


import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;

/**
 * Collision detection for Player landing on blocks
 * @author Brian
 *
 */
public class DoodleToBlockCollision extends AdvanceCollisionGroup {
	private double springVelocityMultiplier = 15.0;
	
	/**
	 * Create new Collision constructor
	 */
	private String myAction;
	public DoodleToBlockCollision(){
		pixelPerfectCollision = true;
		String myAction = new String("stand");
	}
	

	/**
	 * React if there is a collision between player and block
	 * @param doodle DoodleSprite in SpriteGroup
	 * @param block BlockSprite in SpriteGroup
	 */
	@Override
	public void collided(Sprite doodle, Sprite block) {

		if(super.getCollisionSide() == 8){ //if collision = doodle feet to block...
			
			if (block.getID()==1){ //if collision with normal block....
				doodle.setVerticalSpeed(block.getVerticalSpeed()); //stand on block
			} else if(block.getID()==2){ //if collision with springBlock...
				doodle.setVerticalSpeed(block.getVerticalSpeed()*springVelocityMultiplier); //bounce
			} /*else if(block.getID()==3){ //if collision with breakingBlock...
				doodle.setVerticalSpeed(block.getVerticalSpeed()); //doodlespeed = speed of block
			}*/
		}
		//How to disallow horizontally walking through blocks?  Btw, is there a more elegant way to
		//allow different actions based on different vals of getCollisionSide()? --devon
		/* else if(super.getCollisionSide() == 2){
			doodle.setHorizontalSpeed(1);
		} else if(super.getCollisionSide() == 4){
			doodle.setHorizontalSpeed(-1);
		}
		*/
	}
	
	
	
}
