package arcade.store;

import java.util.*;

import arcade.core.mvc.IController;
import arcade.core.mvc.IModel;
import arcade.lobby.model.Profile;
import arcade.lobby.model.ProfileSet;
import arcade.store.account.StoreUser;
import arcade.store.control.MainController;
import arcade.store.items.DbItemAndUserFactory;
import arcade.store.items.IItemInfo;
import arcade.store.organizer.FilterByGenreOrganizer;

public class StoreModel implements IModel{
	
	private StoreUser currentUser;
	private Profile lobbyUser;
	private Map<String, IItemInfo> storeCatalogue;
	private IController controller;
	
	public StoreModel(IController control)
	{
		storeCatalogue = DbItemAndUserFactory.getAllStoreItems();
		controller = control;
		lobbyUser = ProfileSet.currentProfile;
		currentUser = DbItemAndUserFactory.getUser(0);
	}
	
	public IItemInfo getItemInfo(String name)
	{
		return storeCatalogue.get(name);
	}
	
	
	public List<IItemInfo> filter(String genreName) {
		if(genreName.equals("All")) {
			return getAllItems();
		}
		
		FilterByGenreOrganizer organizer = new FilterByGenreOrganizer();
		List<IItemInfo> answer = organizer.organize(getAllItems(), genreName);
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
		currentUser.addToCart(itemName);
	}

	@Override
	public void setController(IController control) {
		
		controller = control;
		
	}
	
	public StoreUser getCurrentUserAccount()
	{
		return currentUser;
	}
	
	public double getTotalUserCartCost()
	{
		List<String> wishList = currentUser.getCart();
		
		double sum = 0;
		
		for(String item : wishList)
		{
			// TODO: r u sure there's not any formatting error with names?!?!
			IItemInfo info = storeCatalogue.get(item);
			String price = info.getPrice();
			sum += Double.parseDouble(price);
		}
		
		return sum;
	}
	
	public boolean userHasEnoughCredditsToBuyWishList()
	{
		double currentCreddits = Double.parseDouble(currentUser.getCreddits());
		return (currentCreddits - getTotalUserCartCost() ) >= 0;	
	}
	
}
