package arcade.store.organizer;

import arcade.store.items.IItemInfo;

public class SortByPriceOrganizer extends SortOrganizer {

	@Override
	public boolean compareTo(IItemInfo firstItem, IItemInfo secondItem) {
		return (firstItem.getPrice().compareTo(secondItem.getPrice()) < 0);
	}
}