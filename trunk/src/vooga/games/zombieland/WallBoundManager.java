package vooga.games.zombieland;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionBounds;

/*
 * This is the general manager to keep GameEntity inside the game
 * Author: Jimmy Mu, Aaron Choi, Yang Su
 */

public class WallBoundManager extends CollisionBounds{

	public WallBoundManager(Background bg) {
		super(bg);

	}
	
	public void collided(Sprite gameobject) {
		
		if(isCollisionSide(BOTTOM_COLLISION));
		
		if(isCollisionSide(TOP_COLLISION));
		
		if(isCollisionSide(LEFT_COLLISION));
		
		if(isCollisionSide(RIGHT_COLLISION));
		
	}

}
