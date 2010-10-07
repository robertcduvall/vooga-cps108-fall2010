package vooga.games.zombieland;

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

	
	
	public void collided(Sprite zombie1, Sprite zombie2) {
		
		zombie1 = (Zombies) zombie1;
		zombie2 = (Zombies) zombie2;
		
		collided(zombie1, zombie2);
	}
	
	private void collided(Zombies z1, Zombies z2)
	{
		int collisionside = getCollisionSide();
		actOnCollisionSide(collisionside, z1, z2);
	}
	
	private void actOnCollisionSide (int collisionside, Zombies z1, Zombies z2)
	{
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
	
	private void leftRightCollision(Zombies z1, Zombies z2)
	{
		revert();
		
		
		
	}
	
	private void rightLeftCollision(Zombies z1, Zombies z2)
	{
		revert();
		
	}
	
	private void topBottomCollision(Zombies z1, Zombies z2)
	{
		revert();
		
	}
	
	private void bottomTopCollision(Zombies z1, Zombies z2)
	{
		revert();
		
		
	}
}
