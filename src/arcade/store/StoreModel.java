package arcade.store;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import arcade.store.account.UserShopAccount;
import arcade.store.control.Control;
import arcade.store.items.IItemInfo;
import arcade.store.organizer.IOrganizer;
import arcade.store.page.StorePage;

public class StoreModel {

	private static ResourceBundle organizerBundle = ResourceBundle.getBundle("resources.Organizers");
	private UserShopAccount currentUser;
	private Map<String, IItemInfo> storeCatalogue;
	private Control controller;
	
	public StoreModel(Map<String, IItemInfo> catalogue, Control control)
	{
		storeCatalogue = catalogue;
		controller = control;
		
		//currentUser = Security.getCurrentUser();
		
		//get store catalogue here!
	}
	
	public void registerCatalogue(Map<String, IItemInfo> catalogue)
	{
		storeCatalogue = catalogue;
	}
	
	public IItemInfo getItemInfo(String name)
	{
		return storeCatalogue.get(name);
	}
	
	
	public void processPurchase(IItemInfo item)
	{
		currentUser.purchaseItem(item);
	}
	
	public List<IItemInfo> getAllItems()
	{
		ArrayList<IItemInfo> allItems = new ArrayList<IItemInfo>();
		
		for(String key : storeCatalogue.keySet())
			allItems.add(storeCatalogue.get(key));
		
		return allItems;
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
	public List<IItemInfo> organize(String criteria, StorePage currentPage){

		String className = organizerBundle.getString(criteria);

		try {
			IOrganizer organizer = (IOrganizer) Class.forName(className).newInstance();
			return organizer.organize(getAllItems(), criteria);
		} 
		catch (Exception e)
		{
			//currentPage.paintSomething();
			//TODO: figure out what happens when this does not work
			return getAllItems();
		}	
	}

	
	
	
	
	
	
	
	
}
