package engine.collision;

import java.util.List;

/**
 * Collision Manager API
 * This api is completely independent from any game engine. It handles collision 
 * detection and post-collision behavior. we allowed the game designer to customize
 * both.
 * @author Yang, Nick, Kate
 * 
 */
public abstract class CollisionManager {

	public CollisionManager() {
	}

	// the method collides() is overloaded so that it can take two objects, a
	// list and an object
	// and two lists as parameters.
	/**
	 * Process collisions between two objects. If a collision is detected, both
	 * objects are called to act according to their behavior defined in their
	 * actOnCollision method
	 * 
	 * @param object1
	 * @param object2
	 */
	public void collides(Collidable object1, Collidable object2) {
		if (detectCollision(object1, object2))
			act(object1, object2);
	}

	/**
	 * Process collisions between an object and a list of objects. If a
	 * collision is detected between any pair of objects, both objects are
	 * called to act according to their behavior defined in their actOnCollision
	 * method
	 * 
	 * @param object1
	 * @param listOfObjects
	 */
	public void collides(Collidable object1, List<Collidable> listOfObjects) {
		for (Collidable object2 : listOfObjects) {
			if (detectCollision(object1, object2))
				act(object1, object2);
		}
	}

	/**
	 * Process collisions in a list objects. If a collision is detected between
	 * any pair of objects, both objects are called to act according to their
	 * behavior defined in their actOnCollision method
	 * 
	 * @param list
	 */
	public void collides(List<Collidable> list) {
		for (int i = 0; i < list.size(); i++) {
			for (int j = i + 1; j < list.size(); j++) {
				if (detectCollision(list.get(i), list.get(j)))
					act(list.get(i), list.get(j));
			}
		}
	}

	/**
	 * Process collision between two lists of objects. If a collision is
	 * detected between any pair of objects, both objects are called to act
	 * according to their behavior defined in their actOnCollision method
	 * 
	 * @param list1
	 * @param list2
	 */
	public void collides(List<Collidable> list1, List<Collidable> list2) {
		for (int i = 0; i < list1.size(); i++) {
			for (int j = 0; j < list2.size(); j++) {
				if (i == j)
					continue;
				if (detectCollision(list1.get(i), list2.get(j)))
					act(list1.get(i), list2.get(j));
			}
		}
	}

	/**
	 * Detects collision between object1 and object2
	 * 
	 * @param object1
	 * @param object2
	 * @return true if a collision is detected
	 */
	abstract boolean detectCollision(Collidable object1, Collidable object2);

	protected void act(Collidable object1, Collidable object2) {
		// EvenListener.action();
		object1.actOnCollision(object2);
		object2.actOnCollision(object1);
	}

	/**
	 * Process border collisions
	 * 
	 * @param object
	 * @param worldWidth
	 * @param worldHeight
	 */
	public void processBorders(Collidable object, double worldWidth,
			double worldHeight) {
		processBorders(object, 0, worldHeight, 0, worldWidth);
	}

	/**
	 * Process border collisions
	 * 
	 * @param object
	 * @param top
	 * @param bottom
	 * @param left
	 * @param right
	 */
	public void processBorders(Collidable object, double top, double bottom,
			double left, double right) {
		// if (object.getX() < top || object.getX() > bottom) {
		// object.setVelocity(-object.getXVelocity(),
		// object.getYVelocity());
		// }
		// if (object.getY() < left || object.getY() > right) {
		// object.setVelocity(object.getXVelocity(),
		// -object.getYVelocity());
		// }
		processTopBorder(object, top);
		processBottomBorder(object, bottom);
		processLeftBorder(object, left);
		processRightBorder(object, right);
	}
	
	/**
	 * Process collision with top border
	 * @param object
	 * @param top
	 */
	protected void processTopBorder(Collidable object, double top) {
		if (object.getY() < top)
			negateY(object);
	}
	/**
	 * Process collision with top bottom
	 * @param object
	 * @param bottom
	 */
	protected void processBottomBorder(Collidable object, double bottom) {
		if (object.getY() > bottom)
			negateY(object);
	}
	/**
	 * Process collision with top left
	 * @param object
	 * @param left
	 */
	protected void processLeftBorder(Collidable object, double left) {
		if (object.getX() < left)
			negateX(object);
	}
	/**
	 * Process collision with top right
	 * @param object
	 * @param right
	 */
	protected void processRightBorder(Collidable object, double right) {
		if (object.getX() > right)
			negateX(object);
	}
	/**
	 * invert X component of the velocity
	 * @param object
	 */
	protected void negateX(Collidable object) {
		object.setVelocity(-object.getXVelocity(), object.getYVelocity());
	}
	/**
	 * invert Y component of the velocity
	 * @param object
	 */
	protected void negateY(Collidable object) {
		object.setVelocity(object.getXVelocity(), -object.getYVelocity());
	}
}
