package arcade.store.organizer;

import arcade.store.items.IItemInfo;

/**
 * FilterByPriceOrganizer extends the class FilterOrganizer. The override method
 * compareTo compares the price of two IItemInfo objects.
 * 
 * @author Drew Sternesky, Jimmy Mu, Marcus Molchany
 * 
 */
public class FilterByPriceOrganizer extends FilterOrganizer {

	@Override
	public boolean compareTo(IItemInfo firstItem, String filter) {
		return (firstItem.getPrice().compareTo(filter) != 0);
	}
}