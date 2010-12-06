package arcade.store.organizer;

import java.util.List;

import arcade.store.items.IItemInfo;

public class SortByGenreOrganizer extends SortOrganizer {

	@Override
	public List<IItemInfo> organize(List<IItemInfo> list, String criteria) {

		return sortListByGenre(list);
	}

	private List<IItemInfo> sortListByGenre(List<IItemInfo> list) {
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
		IItemInfo tempItem;

		/*
		 * The result is a negative integer if this String object
		 * lexicographically precedes the argument string. The result is a
		 * positive integer if this String object lexicographically follows the
		 * argument string. The result is zero if the strings are equal;
		 * compareTo returns 0 exactly when the equals(Object) method would
		 * return true.
		 */
		return (firstItem.getGenre().compareTo(secondItem.getGenre()) < 0);
	}
}
