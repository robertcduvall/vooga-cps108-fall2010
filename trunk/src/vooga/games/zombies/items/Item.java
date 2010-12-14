package vooga.games.zombies.items;

import vooga.engine.core.BetterSprite;
import vooga.games.zombies.Shooter;

import com.golden.gamedev.object.Sprite;

/**
 * Item class. Defines the functionalities of an item and common behavior. Act
 * needs to be implemented by subclasses to specify an item's function
 * 
 * @author Jimmy Mu, Aaron Choi, Yang Su
 * 
 */
@SuppressWarnings("serial")
public abstract class Item extends BetterSprite {

	private Shooter myPlayer;

	public Item(Shooter shooter, Sprite s, double x, double y) {
		super("Item", s);
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
