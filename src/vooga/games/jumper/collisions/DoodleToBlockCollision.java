package vooga.games.jumper.collisions;


import vooga.engine.core.Game;
import vooga.engine.resource.Resources;
import vooga.games.jumper.sprites.BlockSprite;
import vooga.games.jumper.sprites.DoodleSprite;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;

/**
 * Collision detection for Player landing on blocks
 * @author Brian, Devon, Cody
 * 
 *
 */
public class DoodleToBlockCollision extends AdvanceCollisionGroup {

	private static final int TOP_COLLISION_SIDE = 8;
	private Game myGame;
	
	/**
	 * construct new Doodle to Block collision
	 * @param Game jumper
	 */
	public DoodleToBlockCollision(Game jumper){
		super();
		myGame = jumper;
		pixelPerfectCollision = true;
	}
	
	/**
	 * React if there is a collision between player and block
	 * @param doodle DoodleSprite in SpriteGroup
	 * @param block BlockSprite in SpriteGroup
	 */
	public void collided(Sprite doodle, Sprite block) {
		if(super.getCollisionSide() == TOP_COLLISION_SIDE){
			((BlockSprite) block).handleCollision((DoodleSprite) doodle);
		}
		

		}
			
	/**
	 * Changes the image of the sprite if it collides with
	 * an specific sprite.  An example is if the main character touches a 
	 * powerup, the character's image will change to reflect this.
	 * @param Sprite
	 * @param Strite
	 */
	public void changeSpriteImage(Sprite spr, String str){
		spr.setImage(Resources.getImage(str));
	}
}
