package arcade.store.organizer;

import arcade.store.items.IItemInfo;

public class FilterByGenreOrganizer extends FilterOrganizer {

	@Override
	public boolean compareTo(IItemInfo firstItem, String filter) {
		return (firstItem.getGenre().compareTo(filter) != 0);
	}
}