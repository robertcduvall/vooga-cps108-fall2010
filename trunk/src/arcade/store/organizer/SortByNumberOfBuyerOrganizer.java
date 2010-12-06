package arcade.store.organizer;

import arcade.store.items.IItemInfo;

public class SortByNumberOfBuyerOrganizer extends SortOrganizer {

	@Override
	public boolean compareTo(IItemInfo firstItem, IItemInfo secondItem) {
		return (firstItem.getNumberOfBuyer().compareTo(
				secondItem.getNumberOfBuyer()) < 0);
	}
}