package arcade.store;

import java.util.HashMap;
import java.util.Map;

import arcade.store.organizer.IOrganizer;
import arcade.store.page.StorePage;

public class StoreLibrary {

	private static Map<String, StorePage> pageCatalogue;
	private static Map<String, IOrganizer> pageOrganizer;
	
	public StoreLibrary()
	{
		pageCatalogue = new HashMap<String, StorePage>();
		pageOrganizer = new HashMap<String, IOrganizer>();
	}
	
	public static void addPage(String pageName, StorePage page)
	{
		pageCatalogue.put(pageName, page);
	}
	
	public static StorePage getPage(String pageName)
	{
		return pageCatalogue.get(pageName);
	}
	
	public static void removePage(String pageName) {
		if (pageCatalogue.containsKey(pageName)) {
			pageCatalogue.remove(pageName);
		}
	}
	
	/**
	 * Provide a write out to an XML file to keep the current history.
	 * TODO: how do y
	 */
	public static void recordToHistory()
	{
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
