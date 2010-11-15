package vooga.engine.player;


import java.util.List;
import vooga.engine.core.Sprite;

/**
 * PlayerSprite adds an inventory system to the engine.core.Sprite package, formerly
 * known as GameEntitySprite.
 * @author Drew Sternesky, Marcus Molchany, Jimmy Mu
 * 
 */

@SuppressWarnings("serial")
public class PlayerSprite extends Sprite {

	// general instance variables
	protected List<ItemInterface> myItemList;


	/**
	 * Causes the act method of an item to be executed and the item to be
	 * removed from the player's list.
	 * 
	 * @param i is the item to be used.
	 */
	public void useItem(ItemInterface i) {
		i.act();
		if (!i.hasMoreUses()) {
			myItemList.remove(i);
		}
	}

	/**
	 * 
	 * @return the number of items in the player's inventory.
	 */
	public int getItemListSize() {
		return myItemList.size();
	}

	/**
	 * Adds an item to the player's inventory
	 * @param i is the item to be added.
	 */
	public void addItemToList(ItemInterface i) {
		myItemList.add(i);
	}

	/**
	 * Adds multiple items to player's inventory.
	 * @param list is list of items to be added.
	 */
	public void addItemsToList(List<ItemInterface> list) {
		myItemList.addAll(list);
	}

}
