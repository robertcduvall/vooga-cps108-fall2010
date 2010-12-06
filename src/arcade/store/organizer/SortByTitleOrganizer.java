package arcade.store.organizer;

import arcade.store.items.IItemInfo;

public class SortByTitleOrganizer extends SortOrganizer {

	@Override
	public boolean compareTo(IItemInfo firstItem, IItemInfo secondItem) {
		return (firstItem.getTitle().compareTo(secondItem.getTitle()) < 0);
	}
}
