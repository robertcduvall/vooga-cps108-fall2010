package engine.collision;

/**
 * This interface must be implemented by active/movable objects involved in a game to ensure 
 * that the collision manager is able to interact with the objects and detect collisions correctly and 
 * properly. It allows a game designer to specify how each object behaves upon collision, possibly
 * define different behavior for different types of objects that the current one collides with. It 
 * also forces the implementation of useful methods that are necessary in calculation post collision
 * behavior.
 * @author Yang, Nick, Kate
 */
public interface Collidable {
	/**
	 * This method is called whenever an object comes into contact with another. It determines
	 * the post collision behavior of this object according to what kind of object it collides with.
	 * This can be customized when an object implements it and allows for the implementation to 
	 * extract information about the object it collides with.
	 * @param object the Collidable object that the current object has collided with
	 */
	public void actOnCollision(Collidable object);
	
	/**
	 * @return x component of the velocity of this object
	 */
	public double getXVelocity();
	/**
	 * @return y component of the velocity of this object
	 */
	public double getYVelocity();
	/**
	 * Set the velocity of this object
	 * @param vx x component of the velocity
	 * @param vy y component of the velocity
	 */
	public void setVelocity(double vx, double vy);
	
	/**
	 * @return x coordinate of this object
	 */
	public double getX();
	/**
	 * @return y coordinate of this object
	 */
	public double getY();
	/**
	 * Set the location of this object
	 * @param x x coordinate 
	 * @param y y coordinate
	 */
	public void setLocation(double x, double y);
}
