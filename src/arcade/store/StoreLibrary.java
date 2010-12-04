package arcade.store;

import java.util.HashMap;
import java.util.Map;

import arcade.store.page.StorePage;

public class StoreLibrary {

	private static Map<String, StorePage> pageCatalogue;
	
	public StoreLibrary()
	{
		pageCatalogue = new HashMap<String, StorePage>();
	}
	
	public static void addPage(String pageName, StorePage page)
	{
		pageCatalogue.put(pageName, page);
	}
	
	public static StorePage getPage(String pageName)
	{
		return pageCatalogue.get(pageName);
	}
	

	/**
	 * Provide a write out to an XML file to keep the current history.
	 * 
	 */
	public static void recordToHistory()
	{
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
