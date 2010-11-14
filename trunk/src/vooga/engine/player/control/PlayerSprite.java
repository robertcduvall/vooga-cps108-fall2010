package vooga.engine.player.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vooga.engine.overlay.Stat;

import com.golden.gamedev.object.Sprite;

/**
 * PlayerSprite is designed to represent a player in a game. It keeps track of
 * score, lives, health, rank, and an item list, in addition to the features
 * provided by GameEntitySprite, such as a name and the ability to use multiple
 * Sprites at different times to represent this player. PlayerSprites contain an
 * IPlayerController instance variable that can control the movement of the
 * PlayerSprite if an IPlayerController class has been written. We provide an
 * example of a KeyboardController.
 * 
 * @author Drew Sternesky, Marcus Molchany, Jimmy Mu
 * 
 */

@SuppressWarnings("serial")
public class PlayerSprite extends GameEntitySprite {

	// general instance variables
	protected List<ItemSprite> myItemList;
	protected Map<String, Stat<?>> myStatMap;

	/**
	 * 
	 * @param name
	 *            is any name you'd like to associate with a player.
	 * @param stateName
	 *            is the name that will be associated with the Sprite parameter
	 *            (to switch to it).
	 * @param s
	 *            is a Sprite that will represent this player.
	 * @param control
	 *            is a class that implements the IPlayerController interface
	 *            (for controlling player speed and movement).
	 */
	public PlayerSprite(String name, String stateName, Sprite s) {
		super(name, stateName, s);
		myItemList = new ArrayList<ItemSprite>();
		myStatMap = new HashMap<String, Stat<?>>();
	}

	/**
	 * Updates player's current sprite.
	 */
	@Override
	public void update(long elapsedTime) {
		super.update(elapsedTime);
	}

	/**
	 * Checks whether a value is inbound
	 */
	private boolean isInBound(double currPosition, double min, double max) {
		return (currPosition > min && currPosition < max);
	}

	/**
	 * Causes the act method of an item to be executed and the item to be
	 * removed from the player's list.
	 * 
	 * @param i
	 *            is the item to be used.
	 */
	public void useItem(ItemSprite i) {
		i.act();
		if (!i.hasMoreUses()) {
			myItemList.remove(i);
		}
	}

	public int getItemListSize() {
		return myItemList.size();
	}

	public void addItemToList(ItemSprite i) {
		myItemList.add(i);
	}

	public void addItemsToList(List<ItemSprite> list) {
		myItemList.addAll(list);
	}

	public void addStat(String statName, Stat<?> stat) {
		myStatMap.put(statName, stat);
	}

	public Stat<?> getStat(String statName) {
		return myStatMap.get(statName);
	}

}
