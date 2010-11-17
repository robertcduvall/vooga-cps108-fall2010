package vooga.games.jumper;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


import vooga.engine.resource.Resources;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.Timer;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;

/**
 * Collision detection for Player landing on blocks
 * @author Brian
 * 
 *
 */
public class DoodleToBlockCollision extends AdvanceCollisionGroup {
//	KeyListener myKeyListener;
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

	
	public void collided(Sprite doodle, Sprite block) {
		if(super.getCollisionSide() == 8){
			((BlockSprite) block).handleCollision((DoodleSprite) doodle);
		}
		

		}
		//How to disallow horizontally walking through blocks?  Btw, is there a more elegant way to
		//allow different actions based on different vals of getCollisionSide()? --devon
		/* else if(super.getCollisionSide() == 2){
			doodle.setHorizontalSpeed(1);
		} else if(super.getCollisionSide() == 4){
			doodle.setHorizontalSpeed(-1);
		}
		*/	
	public void changeSpriteImage(Sprite spr, String str){
		spr.setImage(Resources.getImage(str));
	}
}
