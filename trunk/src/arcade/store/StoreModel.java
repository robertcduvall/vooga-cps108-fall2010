package arcade.store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import arcade.core.mvc.IController;
import arcade.core.mvc.IModel;
import arcade.lobby.model.Profile;
import arcade.lobby.model.ProfileSet;
import arcade.store.account.GuestUser;
import arcade.store.account.StoreUser;
import arcade.store.database.DbItemAndUserFactory;
import arcade.store.database.StoreDbConstants;
import arcade.store.database.StoreSqlAdapter;
import arcade.store.items.IItemInfo;
import arcade.store.organizer.FilterByGenreOrganizer;
import arcade.store.privileges.PrivilegeManager;

public class StoreModel implements IModel{
	
	private static StoreUser currentUser;
	private Profile lobbyUser;
	private static Map<String, IItemInfo> storeCatalogue;
	private IController controller;
	private PrivilegeManager privilegeManager;
	private static StoreSqlAdapter dbAdapter = new StoreSqlAdapter();
	
	public StoreModel(IController control)
	{
		privilegeManager = new PrivilegeManager();
		storeCatalogue = DbItemAndUserFactory.getAllStoreItems();
		controller = control;
		lobbyUser = ProfileSet.getCurrentProfile();
		try{
			currentUser = DbItemAndUserFactory.getUser(lobbyUser.getUserId());
		}
		catch(NullPointerException e) {
			currentUser = new GuestUser();
		//	currentUser = DbItemAndUserFactory.getUser(0);
		}
	}
	
	public static IItemInfo getItemInfo(String name)
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
			IItemInfo info = storeCatalogue.get(item);
			String price = info.getPrice();
			sum += Double.parseDouble(price);
		}
		
		return sum;
	}
	
	public boolean userHasEnoughCredditsToBuyWishList()
	{
		double currentCreddits = currentUser.getCreddits();
		return (currentCreddits - getTotalUserCartCost() ) >= 0;	
	}
	
	public void addCredditsToUser(double amount) {
		setUserCreddits(currentUser.getCreddits()+amount);
	}
	
	public void setUserCreddits(double amount) {
		currentUser.setCreddits(amount);
		dbAdapter.updateCreddits(amount, currentUser.getId());
	}
	
	public void processGamePurchase(List<IItemInfo> titles) {
		dbAdapter.updatePurchaseHistory(titles, currentUser.getId());
	}
	
	public void emptyUserCart() {
		currentUser.emptyCart();
		dbAdapter.updateList(currentUser.getCart(), currentUser.getId(),StoreDbConstants.CART_FIELD);
	}
	
	public void saveCart() {
		dbAdapter.updateList(currentUser.getCart(), currentUser.getId(),StoreDbConstants.CART_FIELD);
	}
	
	public static List<Integer> getUserOwnedGames(int userId) {
		List<Integer> itemList = new ArrayList<Integer>();
		for(Map<String, String> m : pollOwnedGamesTable(userId)) {
			itemList.add(Integer.parseInt(m.get("Item_Id")));
		}
		return itemList;
	}
	
	/**
	 * Returns the boolean for whether or not the user has enough creddits to
	 * proceed with their purchase.
	 * 
	 * @return true if the user has enough creddits, false otherwise.
	 */
	public boolean userHasEnoughCreddits() {

		return !userHasEnoughCredditsToBuyWishList();
	}

	public boolean userHasNoCartItems() {
		return currentUser.getCart().isEmpty();
	}
	
	public void processBuyCart()
    {
            double userCreddits = currentUser.getCreddits();
            ArrayList<IItemInfo> gamesToBuy = new ArrayList<IItemInfo>();

            for (String title : currentUser.getCart()) {
                    IItemInfo item = getItemInfo(title);
                    double price = Double.parseDouble(item.getPrice());
                    userCreddits -= price;
                    gamesToBuy.add(item);
            }

            processGamePurchase(gamesToBuy);

            // initialize a new array for the cart!
            emptyUserCart();

            // put the creddits back!
            setUserCreddits(userCreddits);
    }

	
	public static String[] getUserOwnedGamesAsStrings(int userId) {
		List<Map<String, String>> ownedGames = pollOwnedGamesTable(userId);
		String[] answer = new String[ownedGames.size()];
		for(int k=0; k<answer.length; k++) {
			answer[k] = ownedGames.get(k).get("ItemName");
		}
		return answer;
	}
	
	public double getUserWishListBalance()
	{
		double currCredits = currentUser.getCreddits();
		double totalCost = getTotalUserCartCost();
		return (currCredits - totalCost);
	}
	
	private static List<Map<String, String>> pollOwnedGamesTable(int userId) {
		Map<String,String> condition = new HashMap<String, String>();
		condition.put("User_Id", Integer.toString(userId));
		return dbAdapter.getRows(StoreDbConstants.PURCHASE_HISTORY_TABLE, condition, "Item_Id","ItemName");
	}
	
	public static void addItemsToCart(List<IItemInfo> itemsToPurchase) {
		for(IItemInfo i : itemsToPurchase) {
			currentUser.addToCart(i.getTitle());
		}
	}
	
	public boolean checkPrivileges(String privilegeType) {
		return privilegeManager.getPermission(currentUser, privilegeType);
	}
		
}
