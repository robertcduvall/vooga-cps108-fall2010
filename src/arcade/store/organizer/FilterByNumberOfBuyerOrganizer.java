package arcade.store.organizer;

import arcade.store.items.IItemInfo;

/**
 * FilterByNumberOfBuyerOrganizer extends the class FilterOrganizer. The
 * override method compareTo compares the number of buyers of two IItemInfo
 * objects.
 * 
 * @author Drew Sternesky, Jimmy Mu, Marcus Molchany
 * 
 */
public class FilterByNumberOfBuyerOrganizer extends FilterOrganizer {

	@Override
	public boolean compareTo(IItemInfo firstItem, String filter) {
		return (firstItem.getNumberOfBuyer().compareTo(filter) != 0);
	}
}