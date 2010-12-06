package arcade.store.organizer;

import java.util.List;

import arcade.store.items.IItemInfo;

public class FilterOrganizer implements IOrganizer {

	@Override
	public List<IItemInfo> organize(List<IItemInfo> list, String criteria) {
		return sortListByAttribute(list, criteria);
	}

	private List<IItemInfo> sortListByAttribute(List<IItemInfo> list, String criteria) {
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