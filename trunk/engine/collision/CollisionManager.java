package engine.collision;

import java.util.List;

/**
 * Collision Manager API This collision manager class is abstract and is
 * intended to be extended by a subclass that specifies the mechanics for a
 * specific game. It has built-in methods to process collisions between objects
 * and between objects and borders
 * 
 * @author Yang, Nick, Kate
 * 
 */
public abstract class CollisionManager {

	/**
	 * Detects collision between object1 and object2, must be implemented in the
	 * subclass. It is up to the game designer to determine how to write a collision
	 * detection algorithm that is fit of the game he/she is trying to create in 
	 * terms of efficiency and precision
	 * 
	 * @param object1
	 *            an object that implements the interface Collidable
	 * @param object2
	 *            an object that implements the interface Collidable
	 * @return true if a collision is detected
	 */
	abstract boolean detectCollision(Collidable object1, Collidable object2);


	/**
	 * Calls the actOnCollision method for each of the object involved in a
	 * collision. It's protected so that the method can be overwritten by
	 * subclasses to customize behavior
	 * 
	 * @param object1
	 *            an object that implements the interface Collidable
	 * @param object2
	 *            an object that implements the interface Collidable
	 */
	protected void act(Collidable object1, Collidable object2) {
		// EvenListener.action();
		object1.actOnCollision(object2);
		object2.actOnCollision(object1);
	}
	
	// the method collides() is overloaded so that it can take two objects, a
	// list and an object and two lists as parameters.
	/**
	 * Process collisions between two objects. If a collision is detected, both
	 * objects are called to act according to their behavior defined in their
	 * actOnCollision method
	 * 
	 * @param object1
	 *            an object that implements the interface Collidable
	 * @param object2
	 *            an object that implements the interface Collidable
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
	 *            an object that implements the interface Collidable
	 * @param listOfObjects
	 *            a list of Collidable objects
	 */
	public void collides(Collidable object1, List<Collidable> listOfObjects) {
		for (Collidable object2 : listOfObjects) {
			if (detectCollision(object1, object2))
				act(object1, object2);
		}
	}

	/**
	 * Process collisions in a list objects. If a collision is detected between
	 * any pair of objects within the list, both objects are called to act
	 * according to their behavior defined in their actOnCollision method
	 * 
	 * @param listOfObjects
	 *            a list of Collidable objects
	 */
	public void collides(List<Collidable> listOfObjects) {
		for (int i = 0; i < listOfObjects.size(); i++) {
			for (int j = i + 1; j < listOfObjects.size(); j++) {
				if (detectCollision(listOfObjects.get(i), listOfObjects.get(j)))
					act(listOfObjects.get(i), listOfObjects.get(j));
			}
		}
	}

	/**
	 * Process collision between two lists of objects. If a collision is
	 * detected between any pair of objects, both objects are called to act
	 * according to their behavior defined in their actOnCollision method
	 * 
	 * @param list1
	 *            a list of Collidable objects
	 * @param list2
	 *            a list of Collidable objects
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

	
	//the processBorders methods is overloaded so that it can process default borders,
	//which are the four sides of the game world(provided that it is a rectangular universe).
	//It also allows for customization that restrict to an object's movement to a specific
	//area in the game
	/**
	 * Process border collisions for a given object. Bouncing is the default
	 * behavior
	 * 
	 * @param object
	 *            an object that implements the interface Collidable
	 * @param worldWidth
	 *            width of the game world
	 * @param worldHeight
	 *            height of the game world
	 */
	public void processBorders(Collidable object, double worldWidth,
			double worldHeight) {
		processBorders(object, 0, worldHeight, 0, worldWidth);
	}

	/**
	 * Process border collisions for a given object. Bouncing is the default
	 * behavior
	 * 
	 * @param object
	 *            an object that implements the interface Collidable
	 * @param top
	 *            location of the top border of the game world
	 * @param bottom
	 *            location of the bottom border of the game world
	 * @param left
	 *            location of the left border of the game world
	 * @param right
	 *            location of the right border of the game world
	 */
	public void processBorders(Collidable object, double top, double bottom,
			double left, double right) {
		if (object.getY() < top)
			processTopBorder(object, top);
		if (object.getY() > bottom)
			processBottomBorder(object, bottom);
		if (object.getX() < left)
			processLeftBorder(object, left);
		if (object.getX() > right)
			processRightBorder(object, right);
	}

	/**
	 * Process collision with top border. Bouncing is the default behavior. It's
	 * protected so it can be overwritten to customize behavior.
	 * 
	 * @param object
	 *            an object that implements the interface Collidable
	 * @param top
	 *            location of the top border of the game world
	 */
	protected void processTopBorder(Collidable object, double top) {
		negateVY(object);
	}

	/**
	 * Process collision with bottom border. Bouncing is the default behavior.
	 * It's protected so it can be overwritten to customize behavior.
	 * 
	 * @param object
	 *            an object that implements the interface Collidable
	 * @param bottom
	 *            location of the bottom border of the game world
	 */
	protected void processBottomBorder(Collidable object, double bottom) {
		negateVY(object);
	}

	/**
	 * Process collision with left border. Bouncing is the default behavior.
	 * It's protected so it can be overwritten to customize behavior.
	 * 
	 * @param object
	 *            an object that implements the interface Collidable
	 * @param left
	 *            location of the left border of the game world
	 */
	protected void processLeftBorder(Collidable object, double left) {
		negateVX(object);
	}

	/**
	 * Process collision with right border. Bouncing is the default behavior.
	 * It's protected so it can be overwritten to customize behavior.
	 * 
	 * @param object
	 *            an object that implements the interface Collidable
	 * @param right
	 *            location of the right border of the game world
	 */
	protected void processRightBorder(Collidable object, double right) {
		negateVX(object);
	}

	/**
	 * Invert X component of the velocity
	 * 
	 * @param object
	 */
	private void negateVX(Collidable object) {
		object.setVelocity(-object.getXVelocity(), object.getYVelocity());
	}

	/**
	 * Invert Y component of the velocity
	 * 
	 * @param object
	 */
	private void negateVY(Collidable object) {
		object.setVelocity(object.getXVelocity(), -object.getYVelocity());
	}
}
