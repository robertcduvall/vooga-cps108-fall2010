package arcade.store.organizer;

import arcade.store.items.IItemInfo;

/**
 * SortByTitleOrganizer extends the class SortOrganizer. The override method
 * compareTo compares the title of two IItemInfo objects.
 * 
 * @author Drew Sternesky, Jimmy Mu, Marcus Molchany
 * 
 */
public class SortByTitleOrganizer extends SortOrganizer {

	@Override
	public boolean compareTo(IItemInfo firstItem, IItemInfo secondItem) {
		return (firstItem.getTitle().compareTo(secondItem.getTitle()) < 0);
	}
}
