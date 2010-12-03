package arcade.store;

import java.util.HashMap;
import java.util.Map;

import arcade.store.page.StorePage;

public class StoreLibrary {

	private static Map<String, StorePage> pageFinder;
	
	public StoreLibrary()
	{
		pageFinder = new HashMap<String, StorePage>();
	}
	
	public static void addPage(String pageName, StorePage page)
	{
		pageFinder.put(pageName, page);
	}
	
	public static StorePage getPage(String pageName)
	{
		return pageFinder.get(pageName);
	}
	


	
	
	
	
	
	
	
	
	
	
	
	
}
