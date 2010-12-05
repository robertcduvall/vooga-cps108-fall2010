package arcade.store.organizer;

import java.util.ArrayList;
import java.util.List;

import arcade.store.page.StorePage;

public class SearchByTagOrganizer implements IOrganizer {

	@Override
	public List<StorePage> organize(List<StorePage> list, String criteria) {
		List<StorePage> organizedList = new ArrayList<StorePage>();

		searchForTag(list, criteria, organizedList);

		return organizedList;
	}

	private void searchForTag(List<StorePage> list, String criteria,
			List<StorePage> organizedList) {
		for (StorePage storePage : list) {
			checkTagInStorePage(criteria, organizedList, storePage);
		}
	}

	private void checkTagInStorePage(String criteria,
			List<StorePage> organizedList, StorePage storePage) {
		if (storePage.getTags().contains(criteria)) {
			organizedList.add(storePage);
		}
	}

}
