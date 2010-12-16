package arcade.store.organizer;

import arcade.store.items.IItemInfo;

/**
 * FilterByGenreOrganizer extends the class FilterOrganizer. The override method
 * compareTo compares the genre of two IItemInfo objects.
 * 
 * @author Drew Sternesky, Jimmy Mu, Marcus Molchany
 * 
 */
public class FilterByGenreOrganizer extends FilterOrganizer {

	@Override
	public boolean compareTo(IItemInfo firstItem, String filter) {
		return (firstItem.getGenre().compareTo(filter) != 0);
	}
}