package arcade.store.organizer;

import java.util.List;

import arcade.store.items.IItemInfo;

/**
 * SearchOrganizer implements the interface IOrganizer. All classes that will
 * perform a search are subclasses of SearchOrganizer.
 * 
 * @author Drew Sternesky, Jimmy Mu, Marcus Molchany
 * 
 */
public class SearchOrganizer implements IOrganizer {

	@Override
	public List<IItemInfo> organize(List<IItemInfo> list, String criteria) {
		return null;
	}

}
