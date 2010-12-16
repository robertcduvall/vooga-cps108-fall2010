package arcade.store.organizer;

import java.util.List;

import arcade.store.items.IItemInfo;

/**
 * SortOrganizer implements the interface IOrganizer. All classes that will
 * perform a sort are subclasses of SortOrganizer. The override method returns
 * an organized List of IItemInfo objects by sorting the IItemInfo List by
 * performing a compareTo against every object in the list against String
 * criteria. Subclasses may override the compareTo method so that it functions a
 * specific way.
 * 
 * @author Drew Sternesky, Jimmy Mu, Marcus Molchany
 * 
 */
public class SortOrganizer implements IOrganizer {

	@Override
	public List<IItemInfo> organize(List<IItemInfo> list, String criteria) {
		return sortListByAttribute(list);
	}

	private List<IItemInfo> sortListByAttribute(List<IItemInfo> list) {
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < list.size(); j++) {
				if (compareTo(list.get(i), list.get(j))) {
					swap(list, i, j);
				}
			}
		}

		return list;
	}

	private void swap(List<IItemInfo> list, int i, int j) {
		IItemInfo temp;
		temp = list.get(i);
		list.set(i, list.get(j));
		list.set(j, temp);
	}

	public boolean compareTo(IItemInfo firstItem, IItemInfo secondItem) {

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
