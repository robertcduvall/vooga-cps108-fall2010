package vooga.games.jumper;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import sun.org.mozilla.javascript.internal.Node.Jump;
import vooga.engine.resource.GameClock;
import vooga.engine.resource.ResourceHandler;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.Timer;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;

/**
 * Collision detection for Player landing on blocks
 * @author Brian
 *
 */
public class DoodleToBlockCollision extends AdvanceCollisionGroup {
	private double springVelocityMultiplier = 5.0;
	private long breakTimer = 0;
	private long breakTimerRate = 100;
	private long startBreakTime = 0;	
	private long breakTimeElapsed;
	KeyListener myKeyListener;
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
		
		/**
		 * if the player's bottom (feet) touches block
		 */
		if(super.getCollisionSide() == 8){ //if collision = doodle feet to block...
			/**
			 * if collision with normal block
			 */
			if (block.getID()==1){ 
				doodle.setVerticalSpeed(block.getVerticalSpeed()); //stand on block
			} 
			/**
			 * if collision with spring block
			 */
			else if(block.getID()==2){
				doodle.setVerticalSpeed(block.getVerticalSpeed()*springVelocityMultiplier); //bounce
			} 
			/**
			 * if collision with breaking block
			 */
			else if(block.getID()==3){
				if(startBreakTime == 0){
					startBreakTime = GameClock.getTime();
				}else if(Math.abs(breakTimeElapsed-breakTimerRate*1)<10){
					changeSpriteImage(block, "platformBreak1");
				}else if(Math.abs(breakTimeElapsed-breakTimerRate*2)<10){
					changeSpriteImage(block, "platformBreak2");
				}else if(Math.abs(breakTimeElapsed-breakTimerRate*3)<10){
					changeSpriteImage(block, "platformBreak3");
					startBreakTime = 0;
					block.setID(0);
				}
				breakTimeElapsed = GameClock.getTime()-startBreakTime;
				doodle.setVerticalSpeed(block.getVerticalSpeed()); //doodlespeed = speed of block
			}
			/**
			 * if collision with jetpack, needs work. --devon
			 */
			else if(block.getID()==4){
				doodle.setVerticalSpeed(block.getVerticalSpeed()*0.5);
				changeSpriteImage(doodle, "doodleJet");			
				doodle.setHorizontalSpeed(0);
				changeSpriteImage(block, "jetpackInvisible");				
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
	}
	
	
	public void changeSpriteImage(Sprite spr, String str){
		spr.setImage(ResourceHandler.getImage(str));
	}
}
