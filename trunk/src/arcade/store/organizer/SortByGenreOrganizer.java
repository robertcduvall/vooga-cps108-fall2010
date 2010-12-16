package arcade.store.organizer;

import arcade.store.items.IItemInfo;

/**
 * SortByGenreOrganizer extends the class SortOrganizer. The override method
 * compareTo compares the genre of two IItemInfo objects.
 * 
 * @author Drew Sternesky, Jimmy Mu, Marcus Molchany
 * 
 */
public class SortByGenreOrganizer extends SortOrganizer {

	@Override
	public boolean compareTo(IItemInfo firstItem, IItemInfo secondItem) {
		return (firstItem.getGenre().compareTo(secondItem.getGenre()) < 0);
	}
}
