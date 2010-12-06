package arcade.store.organizer;

import arcade.store.items.IItemInfo;

public class SortByGenreOrganizer extends SortOrganizer {

	@Override
	public boolean compareTo(IItemInfo firstItem, IItemInfo secondItem) {
		return (firstItem.getGenre().compareTo(secondItem.getGenre()) < 0);
	}
}
