package vooga.engine.player.control.Event;

/**
 * This interface defines the methods that a controller class must implement in
 * order to be able to control the movement of a player. These methods are
 * called by the PlayerSprite when it updates itself in order to determine how
 * it should change its velocity and position. At the moment, it does not define
 * a way to trigger an action--this is something that could be useful to add in
 * the future.
 * 
 * @author Drew Sternesky, Marcus Molchany, Jimmy Mu
 * 
 */

public interface IPlayerController {

	/**
	 * 
	 * @return Amount to change the X-position of the Player.
	 */
	public double deltaX();

	/**
	 * 
	 * @return Amount to change the Y-position of the Player.
	 */
	public double deltaY();

	/**
	 * 
	 * @return Amount to change the X-velocity by.
	 */
	public double deltaVelocityX();

	/**
	 * 
	 * @return Amount to change the Y-velocity by.
	 */
	public double deltaVelocityY();

}
