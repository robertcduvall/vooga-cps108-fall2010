package vooga.games.zombieland;

import vooga.engine.player.control.GameEntitySprite;
import com.golden.gamedev.object.Sprite;

/**
 * Item class. Defines the functionalities of an item and common behavior. Act
 * needs to be implemented by subclasses to specify an item's function
 * 
 * @author Jimmy Mu, Aaron Choi, Yang Su
 * 
 */
public abstract class Item extends GameEntitySprite {

	private Shooter myPlayer;

	public Item(Shooter shooter, Sprite s, double x, double y) {
		super("Item", "Weapon", s);
		setX(x);
		setY(y);
		myPlayer = shooter;
	}

	/**
	 * Get the shooter that possesses this item. Used to implement specific functions of items.
	 * @return
	 */
	public Shooter getPlayer() {
		return myPlayer;
	}
	/**
	 * Defines how an item acts when picked up
	 */
	public abstract void act();
}
