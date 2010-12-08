package arcade.store;

import java.util.*;

import arcade.store.account.StoreUser;
import arcade.store.control.Control;
import arcade.store.items.DbItemAndUserFactory;
import arcade.store.items.IItemInfo;
import arcade.store.organizer.FilterByGenreOrganizer;

public class StoreModel {

	private static final String GAMES_DIRECTORY = System.getProperty("user.dir")+"/src/arcade/store/gui/resources/games";
	private static final String GAME_INFO_TABLE = "GameInfo";
	private static final String USER_INFO_TABLE = "StoreUsers";
//	private static ResourceBundle organizerBundle = ResourceBundle.getBundle("resources.Organizers");
	private StoreUser currentUser;
	private Map<String, IItemInfo> storeCatalogue;
	private Control controller;
	
	public StoreModel(Control control)
	{
		storeCatalogue = DbItemAndUserFactory.getAllItems(GAME_INFO_TABLE);
		controller = control;
		//String username = Security.getCurrentUser().getName();
//		currentUser = DbItemAndUserFactory.getUser(USER_INFO_TABLE, username);
		
	}
	
	public IItemInfo getItemInfo(String name)
	{
		return storeCatalogue.get(name);
	}
	
	
	public void processPurchase(String item)
	{
//		currentUser.purchaseItem(storeCatalogue.get(item));
	}
	
	public List<IItemInfo> filter(String genreName) {
		if(genreName == "All") {
			return getAllItems();
		}
		FilterByGenreOrganizer org = new FilterByGenreOrganizer();
		List<IItemInfo> answer = org.organize(getAllItems(), genreName);
		return answer;
	}
	
	public List<IItemInfo> getAllItems()
	{
		ArrayList<IItemInfo> allItems = new ArrayList<IItemInfo>();
		
		for(String key : storeCatalogue.keySet())
			allItems.add(storeCatalogue.get(key));
		
		return allItems;
	}
	
	public String[] getGenres() {
		Set<String> list = new HashSet<String>();
		list.add("All");
		for(String key : storeCatalogue.keySet()) {
			list.add(storeCatalogue.get(key).getGenre());
		}
		String[] returnValue = new String[list.size()];
		Iterator<String> i = list.iterator();
		for(int k=0; k<returnValue.length; k++) {
			returnValue[k] = i.next();
		}
		return returnValue;
	}
	
	public void addToCart(String itemName) {
		currentUser.getCart().add(itemName);
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
//	public List<IItemInfo> organize(String criteria){
//
//		String className = organizerBundle.getString(criteria);
//
//		try {
//			IOrganizer organizer = (IOrganizer) Class.forName(className).newInstance();
//			return organizer.organize(getAllItems(), criteria);
//		} 
//		catch (Exception e)
//		{
//			//currentPage.paintSomething();
//			//TODO: figure out what happens when this does not work
//			return getAllItems();
//		}	
//	}

	
	
	
	
	
	
	
	
}
