package arcade.store.organizer;

import arcade.store.items.IItemInfo;

public class FilterByNumberOfBuyerOrganizer extends FilterOrganizer {

	@Override
	public boolean compareTo(IItemInfo firstItem, String filter) {
		return (firstItem.getNumberOfBuyer().compareTo(filter) != 0);
	}
}