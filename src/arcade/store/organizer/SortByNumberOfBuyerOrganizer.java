package arcade.store.organizer;

import arcade.store.items.IItemInfo;

/**
 * SortByNumberOfBuyerOrganizer extends the class SortOrganizer. The override
 * method compareTo compares the number or buyers of two IItemInfo objects.
 * 
 * @author Drew Sternesky, Jimmy Mu, Marcus Molchany
 * 
 */
public class SortByNumberOfBuyerOrganizer extends SortOrganizer {

	@Override
	public boolean compareTo(IItemInfo firstItem, IItemInfo secondItem) {
		return (firstItem.getNumberOfBuyer().compareTo(
				secondItem.getNumberOfBuyer()) < 0);
	}
}