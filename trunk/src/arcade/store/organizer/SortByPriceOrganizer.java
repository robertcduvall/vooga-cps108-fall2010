package arcade.store.organizer;

import arcade.store.items.IItemInfo;

/**
 * SortByPriceOrganizer extends teh class SortOrganizer. The override method
 * compareTo compares the price of two IItemInfo objects.
 * 
 * @author Drew Sternesky, Jimmy Mu, Marcus Molchany
 * 
 */
public class SortByPriceOrganizer extends SortOrganizer {

	@Override
	public boolean compareTo(IItemInfo firstItem, IItemInfo secondItem) {
		return (firstItem.getPrice().compareTo(secondItem.getPrice()) < 0);
	}
}