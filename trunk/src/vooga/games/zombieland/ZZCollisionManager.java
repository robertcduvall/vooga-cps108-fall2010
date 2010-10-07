package vooga.games.zombieland;

import vooga.engine.player.control.GameEntitySprite;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.collision.BasicCollisionGroup;
import com.golden.gamedev.object.collision.PreciseCollisionGroup;

/*
 * This is the Zombie Zombie Collision Manager
 * Authors: Jimmy Mu, Aaron Choi, Yang Su
 * 
 */
public class ZZCollisionManager extends PreciseCollisionGroup{

	
	@Override
	public void collided(Sprite zombie1, Sprite zombie2) {
				
		actOnCollision(zombie1, zombie2);
	}
	
	
	private void actOnCollision( Sprite z1, Sprite z2)
	{
		int collisionside = getCollisionSide();
		
		if( collisionside == BOTTOM_TOP_COLLISION)
			bottomTopCollision(z1, z2);
		if( collisionside == TOP_BOTTOM_COLLISION)
			topBottomCollision(z1, z2);
		if( collisionside == LEFT_RIGHT_COLLISION)
			leftRightCollision(z1, z2);
		if( collisionside == RIGHT_LEFT_COLLISION)
			rightLeftCollision(z1, z2);
	}
	
	private void revert()
	{
		revertPosition1();
		revertPosition2();
	}
	
	
	//although leftRightCollision and rightleftcollision may seem redundant..b/c same code,
	//there's subtle problem. If you pass in a right zombie in a leftRightCollision param
	//left, then when you call on left.setToCurrentSprite("Right"), the zombie still maintains
	//it's current sprite (assuming it's facing right)
	private void leftRightCollision(Sprite left, Sprite right)
	{
		revert();
		
		((Zombie) left).setToCurrentSprite("Right");
		double leftVelocity = left.getHorizontalSpeed();
		leftVelocity = -leftVelocity ;
		left.move(leftVelocity, 0);
		
		((Zombie) right).setToCurrentSprite("Left");
		double rightVelocity = right.getHorizontalSpeed();
		rightVelocity = -rightVelocity ;
		right.move(rightVelocity, 0);
		
	}
	
	private void rightLeftCollision(Sprite right, Sprite left)
	{
		revert();
		
		((Zombie) left).setToCurrentSprite("Right");
		double leftVelocity = left.getHorizontalSpeed();
		leftVelocity = -leftVelocity ;
		left.move(leftVelocity, 0);
		
		((Zombie) right).setToCurrentSprite("Left");
		double rightVelocity = right.getHorizontalSpeed();
		rightVelocity = -rightVelocity ;
		right.move(rightVelocity, 0);
			
	}
	
	private void topBottomCollision(Sprite top, Sprite bottom)
	{
		revert();
		
		((Zombie) top).setToCurrentSprite("Down");
		double topVelocity = top.getVerticalSpeed();
		topVelocity = -topVelocity;
		top.move(0, topVelocity);
		
		((Zombie) bottom).setToCurrentSprite("Up");
		double bottomVelocity = bottom.getVerticalSpeed();
		bottomVelocity = -bottomVelocity;
		bottom.move(0, bottomVelocity);
		
	}
	
	private void bottomTopCollision(Sprite bottom, Sprite top)
	{
		revert();
		
		((Zombie) top).setToCurrentSprite("Down");
		double topVelocity = top.getVerticalSpeed();
		topVelocity = -topVelocity;
		top.move(0, topVelocity);
		
		((Zombie) bottom).setToCurrentSprite("Up");
		double bottomVelocity = bottom.getVerticalSpeed();
		bottomVelocity = -bottomVelocity;
		bottom.move(0, bottomVelocity);
		
	}
}
