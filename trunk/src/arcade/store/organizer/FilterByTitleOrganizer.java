package arcade.store.organizer;

import arcade.store.items.IItemInfo;

public class FilterByTitleOrganizer extends FilterOrganizer {

	@Override
	public boolean compareTo(IItemInfo firstItem, String filter) {
		return (firstItem.getTitle().compareTo(filter) != 0);
	}
}