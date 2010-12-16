package arcade.store.organizer;

import java.util.ArrayList;
import java.util.List;

import arcade.store.items.IItemInfo;

/**
 * SearchByTagOrganizer extends the class SearchOrganizer. The override method
 * organize searches through every item in the IItemInfo List and checks if that
 * item's tags match the String criteria.
 * 
 * @author Drew Sternesky, Jimmy Mu, Marcus Molchany
 * 
 */
public class SearchByTagOrganizer extends SearchOrganizer {

	@Override
	public List<IItemInfo> organize(List<IItemInfo> list, String criteria) {
		List<IItemInfo> organizedList = new ArrayList<IItemInfo>();

		searchForTag(list, criteria, organizedList);

		return organizedList;
	}

	private void searchForTag(List<IItemInfo> list, String criteria,
			List<IItemInfo> organizedList) {
		for (IItemInfo itemInfo : list) {
			checkTagInStorePage(criteria, organizedList, itemInfo);
		}
	}

	private void checkTagInStorePage(String criteria,
			List<IItemInfo> organizedList, IItemInfo itemInfo) {
		if (itemInfo.getTags().contains(criteria)) {
			organizedList.add(itemInfo);
		}
	}

}
