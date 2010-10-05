package vooga.engine.collision;

import com.golden.gamedev.object.collision.CollisionShape;

/**
 * Makes the Collision Manager work with GoldenT, takes two Collidable CollisionShape objects as parameters
 * for the detect detection.
 * @author Yang
 *
 */
public class GoldenTCollisionManager extends CollisionManager {

	public GoldenTCollisionManager() {
	}

	boolean detectCollision(Collidable object1, Collidable object2) {
		if(object1 instanceof CollisionShape && object2 instanceof CollisionShape){
			return ((CollisionShape)object1).intersects((CollisionShape)object2);
		}
		return false;
	}

}
