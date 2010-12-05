package arcade.store.organizer;

import java.util.List;

import arcade.store.page.GamePage;

public interface Organizer {

	public List<IOrganizablePage> organize(List<IOrganizablePage> list);
	
	
}
