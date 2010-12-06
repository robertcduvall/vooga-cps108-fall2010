package arcade.store.organizer;

import arcade.store.items.IItemInfo;

public class FilterByPriceOrganizer extends FilterOrganizer {

	@Override
	public boolean compareTo(IItemInfo firstItem, String filter) {
		return (firstItem.getPrice().compareTo(filter) != 0);
	}
}