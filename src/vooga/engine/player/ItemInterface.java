package vooga.engine.player;

/**
 * Interface that allows an object to be used with the Player inventory system.
 * 
 * @author Drew Sternesky, Marcus Molchany, Jimmy Mu
 *
 */
public interface ItemInterface {
	
	/**
	 * Returns true if the item can be used again and should remain in the player's inventory, false if not.
	 * @return if item can be used again.
	 */
	public boolean hasMoreUses();
	
	/**
	 * Defines an action that is triggered when an item is used.
	 */
	public void act();

}
