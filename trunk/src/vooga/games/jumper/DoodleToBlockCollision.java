package vooga.games.jumper;


import sun.org.mozilla.javascript.internal.Node.Jump;
import vooga.engine.resource.GameClock;
import vooga.engine.resource.ResourceHandler;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;

/**
 * Collision detection for Player landing on blocks
 * @author Brian
 *
 */
public class DoodleToBlockCollision extends AdvanceCollisionGroup {
	private double springVelocityMultiplier = 5.0;
	private long breakTimer = 0;
	private long breakTimerRate = 1000;
	
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
			} else if(block.getID()==3){ //if collision with breakingBlock...
				doodle.setVerticalSpeed(block.getVerticalSpeed()); //doodlespeed = speed of block
				System.out.println("id = 3");
				/*GameClock.createMarker("breakTimeMarker");
				while(GameClock.getMarkerAge("breakTimeMarker")<breakTimerRate*4){
					if(GameClock.getMarkerAge("breakTimeMarker")==breakTimerRate*1){
						block.setImage(ResourceHandler.getImage("platformBreak1"));
						breakTimer++;
						System.out.println("first imagechange");
					}else if(GameClock.getMarkerAge("breakTimeMarker")==breakTimerRate*2){
						block.setImage(ResourceHandler.getImage("platformBreak1"));
						breakTimer++;
						System.out.println("first imagechange");
					}

					
				}*/
				/*
				if(breakTimer==breakTimerRate*1){
					block.setImage(ResourceHandler.getImage("platformBreak1"));
					breakTimer++;
					System.out.println("first imagechange");

				}else if(breakTimer==breakTimerRate*2){
					block.setImage(ResourceHandler.getImage("platformBreak2"));
					breakTimer++;
					System.out.println("second image change");

				}else if(breakTimer==breakTimerRate*3){
					block.setImage(ResourceHandler.getImage("platformBreak3"));
					block.setID(4);
					breakTimer++;
					System.out.println("third image change,id = 4");

				}else{
					doodle.setVerticalSpeed(block.getVerticalSpeed()); //doodlespeed = speed of block
					breakTimer++;
					System.out.println("chillin on broken block" + breakTimer);

				}*/
				doodle.setVerticalSpeed(block.getVerticalSpeed()); //doodlespeed = speed of block
				block.setImage(ResourceHandler.getImage("platformBreak1"));
				block.setImage(ResourceHandler.getImage("platformBreak2"));
				block.setImage(ResourceHandler.getImage("platformBreak3"));
				block.setID(4); //broken block

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
	
	
	
}
