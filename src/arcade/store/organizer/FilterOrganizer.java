package arcade.store.organizer;

import java.util.List;

import arcade.store.items.IItemInfo;

/**
 * FilterOrganizer implements the interface IOrganizer. All classes that will
 * perform a filter are subclasses of FilterOrganizer. The override method
 * returns a filtered List of IItemInfo objects by seraching the IItemInfo List
 * and removing every object in the list that does not match String criteria.
 * Subclasses may override the compareTo method so that it functions a specific
 * way.
 * 
 * @author Drew Sternesky, Jimmy Mu, Marcus Molchany
 * 
 */
public class FilterOrganizer implements IOrganizer {

	@Override
	public List<IItemInfo> organize(List<IItemInfo> list, String criteria) {
		return sortListByAttribute(list, criteria);
	}

	private List<IItemInfo> sortListByAttribute(List<IItemInfo> list,
			String criteria) {
		for (int i = 0; i < list.size(); i++) {
			if (compareTo(list.get(i), criteria)) {
				list.remove(i);
				i--;
			}
		}

		return list;
	}

	public boolean compareTo(IItemInfo firstItem, String filter) {

		/*
		 * The result is a negative integer if this String object
		 * lexicographically precedes the argument string. The result is a
		 * positive integer if this String object lexicographically follows the
		 * argument string. The result is zero if the strings are equal;
		 * compareTo returns 0 exactly when the equals(Object) method would
		 * return true.
		 */
		return false;
	}

}