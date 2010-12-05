package arcade.store;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import arcade.store.organizer.IOrganizer;
import arcade.store.page.StorePage;

public class StoreLibrary {

	private static ResourceBundle organizerBundle = ResourceBundle.getBundle("resources.Organizers");

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

	public static void removePage(String pageName) {
		if (pageCatalogue.containsKey(pageName)) {
			pageCatalogue.remove(pageName);
		}
	}

	
	
	/**
	 * Provide a write out to a file to keep the current history.
	 * TODO: how do y
	 */
	public static void recordToHistory()
	{


	}


	/**
	 * This method uses reflection to obtain the currentPage. If reflection does not work, then 
	 * then this method gets the currentPage and paints the message does not work!! TODO: fill
	 * that part in!
	 * @param list
	 * @param criteria
	 * @param currentPage
	 * @return
	 */
	public static List<StorePage> organize(List<StorePage> list, String criteria, StorePage currentPage){

		String className = organizerBundle.getString(criteria);

		try {
			IOrganizer organizer = (IOrganizer) Class.forName(className).newInstance();
			return organizer.organize(list, criteria);
		} 
		catch (Exception e)
		{
			//currentPage.paintSomething();
			//TODO: figure out what happens when this does not work
			return list;
		}	
	}











}
