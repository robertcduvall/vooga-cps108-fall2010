package arcade.store.organizer;

import arcade.store.items.IItemInfo;

/**
 * FilterByTitleOrganizer extends the class FilterOrganizer. The override method
 * compareTo compares the title of two IItemInfo objects.
 * 
 * @author Drew Sternesky, Jimmy Mu, Marcus Molchany
 * 
 */
public class FilterByTitleOrganizer extends FilterOrganizer {

	@Override
	public boolean compareTo(IItemInfo firstItem, String filter) {
		return (firstItem.getTitle().compareTo(filter) != 0);
	}
}