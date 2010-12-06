package arcade.store.organizer;

import arcade.store.items.IItemInfo;

public class FilterByRatingOrganizer extends FilterOrganizer {

	@Override
	public boolean compareTo(IItemInfo firstItem, String filter) {
		return (firstItem.getRating().compareTo(filter) != 0);
	}
}