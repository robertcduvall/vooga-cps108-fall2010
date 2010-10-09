package vooga.games.zombieland;

import vooga.engine.player.control.GameEntitySprite;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.collision.BasicCollisionGroup;
import com.golden.gamedev.object.collision.PreciseCollisionGroup;

/**
 * 
 * This is the Zombie Zombie Collision Manager. Moves the zombies so they don't
 * stack on top of each other
 * 
 * @author Jimmy Mu, Aaron Choi, Yang Su
 * 
 */
public class ZZCollisionManager extends PreciseCollisionGroup {

	private double reset;

	public ZZCollisionManager() {
		reset = -10;
	}

	/**
	 * Process the collision and initiates post collision behaviror
	 */
	public void collided(Sprite zombie1, Sprite zombie2) {

		actOnCollision(zombie1, zombie2);
	}

	/**
	 * Process
	 * @param zombie1
	 * @param zombie2
	 */
	private void actOnCollision(Sprite zombie1, Sprite zombie2) {
		int collisionside = getCollisionSide();

		if (collisionside == BOTTOM_TOP_COLLISION)
			bottomTopCollision(zombie1, zombie2);
		if (collisionside == TOP_BOTTOM_COLLISION)
			topBottomCollision(zombie1, zombie2);
		if (collisionside == LEFT_RIGHT_COLLISION)
			leftRightCollision(zombie1, zombie2);
		if (collisionside == RIGHT_LEFT_COLLISION)
			rightLeftCollision(zombie1, zombie2);
	}

	// although leftRightCollision and rightleftcollision may seem
	// redundant..b/c same code,
	// there's subtle problem. If you pass in a right zombie in a
	// leftRightCollision param
	// left, then when you call on left.setToCurrentSprite("Right"), the zombie
	// still maintains
	// it's current sprite (assuming it's facing right)
	private void leftRightCollision(Sprite left, Sprite right) {

		Zombie leftZombie = (Zombie) left;
		leftZombie.setToCurrentSprite("Left");
		leftZombie.moveX(reset);
		leftZombie.moveY(reset);

	}

	private void rightLeftCollision(Sprite right, Sprite left) {
		Zombie leftZombie = (Zombie) left;
		leftZombie.setToCurrentSprite("Right");
		leftZombie.moveX(reset);
		leftZombie.moveY(Math.abs(reset));

	}

	private void topBottomCollision(Sprite top, Sprite bottom) {
		Zombie leftZombie = (Zombie) top;
		leftZombie.setToCurrentSprite("Right");
		leftZombie.moveX(reset);
		leftZombie.moveY(Math.abs(reset));

	}

	private void bottomTopCollision(Sprite bottom, Sprite top) {
		Zombie leftZombie = (Zombie) bottom;
		leftZombie.setToCurrentSprite("Right");
		leftZombie.moveX(reset);
		leftZombie.moveY(Math.abs(reset));

	}
}
