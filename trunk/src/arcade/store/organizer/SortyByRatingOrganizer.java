package arcade.store.organizer;

import arcade.store.items.IItemInfo;

public class SortyByRatingOrganizer extends SortOrganizer {

	@Override
	public boolean compareTo(IItemInfo firstItem, IItemInfo secondItem) {
		return (firstItem.getRating().compareTo(secondItem.getRating()) < 0);
	}
}