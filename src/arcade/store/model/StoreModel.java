package arcade.store.model;

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
import arcade.store.account.StoreUser;
import arcade.store.database.DbItemAndUserFactory;
import arcade.store.database.StoreDbConstants;
import arcade.store.database.StoreSqlAdapter;
import arcade.store.items.IItemInfo;
import arcade.store.organizer.FilterByGenreOrganizer;
import arcade.store.privileges.PrivilegeManager;

/**
 * 
 * This class represents the core back end of the store. StoreModel 
 * manages the store catalog stored in the database, filters and sorts
 * these items, and processes actions taken by the current StoreUser such as 
 * purchasing and adding games to cart. Finally, it handles privilege
 * checking to tell GUI pages whether or not to make certain buttons 
 * visible.
 * The methods getUserOwnedGames and getUserOwnedGamesAsStrings are also
 * found in this class. These methods are made available to other groups 
 * such as wall, who can then allow users only to comment on their owned
 * games.
 * 
 * @author Drew Sternesky, Jimmy Mu, Marcus Molchany
 *
 */

public class StoreModel implements IModel{
	
	private static StoreUser currentUser;
	private Profile lobbyUser;
	private static Map<String, IItemInfo> storeCatalogue;
	private IController controller;
	private PrivilegeManager privilegeManager;
	private static StoreSqlAdapter dbAdapter = new StoreSqlAdapter();
	
	/**
	 * Instantiates new StoreModel. Fetches the correct current user by accessing
	 * the lobby's current Profile object and looking up the corresponding user ID.
	 * @param control main controller object.
	 */
	public StoreModel(IController control)
	{
		privilegeManager = new PrivilegeManager();
		storeCatalogue = DbItemAndUserFactory.getAllStoreItems();
		controller = control;
		refreshUser();
	}
	
	/**
	 * Called when tabs are selected. Ensures that the current
	 * store user always corresponds to the current lobby user.
	 */
	public void refreshUser() {
		lobbyUser = ProfileSet.getCurrentProfile();
		currentUser = DbItemAndUserFactory.getUser(lobbyUser.getUserId());
	}
	
	/**
	 * Allows you to look up an IItemInfo object given its Title string.
	 * @param String title of game
	 * @return IItemInfo corresponding to that particular game.
	 */
	public static IItemInfo getItemInfo(String name)
	{
		return storeCatalogue.get(name);
	}
	
	/**
	 * Filters IItemInfo objects based on their genres. Used in the
	 * MainPageTab in the store.
	 * @param name of genre to filter by
	 * @return list of IItemInfo objects whose genres match the given genre.
	 */
	public List<IItemInfo> filter(String genreName) {
		if(genreName.equals("All")) {
			return getAllItems();
		}
		FilterByGenreOrganizer organizer = new FilterByGenreOrganizer();
		List<IItemInfo> answer = organizer.organize(getAllItems(), genreName);
		return answer;
	}
	
	/**
	 * 
	 * @return Contents of store catalog as a list of IItemInfo objects.
	 */
	public List<IItemInfo> getAllItems()
	{
		ArrayList<IItemInfo> allItems = new ArrayList<IItemInfo>();
		
		for(String key : storeCatalogue.keySet())
			allItems.add(storeCatalogue.get(key));
		
		return allItems;
	}
	
	
	/**
	 * Examines the store catalog and returns list of distinct genres.
	 * @return String array of each unique genre found in the store catalog.
	 */
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
	
	/**
	 * Provides access to current user's account.
	 * 
	 * @return the currently active store account.
	 */
	public StoreUser getCurrentUserAccount()
	{
		return currentUser;
	}
	
	/**
	 * Gets the current lobby profile that the StoreModel contains.
	 * The reason for this is that it maintains consistence between the
	 * StoreUser and Profile. 
	 * @return current user's Profile
	 */
	public Profile getCurrentLobbyProfile() 
	{
		return lobbyUser;
	}
	
	/**
	 * Adds given creddits to user and updates database info.
	 * @param amount to add.
	 */
	public void addCredditsToUser(double amount) {
		setUserCreddits(currentUser.getCreddits()+amount);
	}
	
	/**
	 * This method returns the balance of the current User
	 * @return
	 */
	public double getUserCreddits()
	{
		return currentUser.getCreddits();
	}
	
	/**
	 * Sets user creddits as the specified amount and updates database.
	 * @param amount the new creddit balance
	 */
	public void setUserCreddits(double amount) {
		currentUser.setCreddits(amount);
		dbAdapter.updateCreddits(amount, currentUser.getId());
	}
	
	/**
	 * Empties the current user's cart and saves information to database.
	 */
	public void emptyUserCart() {
		currentUser.emptyCart();
		dbAdapter.updateList(currentUser.getCart(), currentUser.getId(),StoreDbConstants.CART_FIELD);
	}
	
	/**
	 * Saves current user's cart to the database.
	 */
	public void saveUserCart() {
		dbAdapter.updateList(currentUser.getCart(), currentUser.getId(),StoreDbConstants.CART_FIELD);
	}
	
	
	/**
	 * 
	 * @return whether or not the current user's cart contains any items.
	 */
	public boolean cartIsEmpty() {
		return currentUser.getCart().isEmpty();
	}
	
	/**
	 * Handles buying all the items in a user's cart. Adjusts creddit balance
	 * and writes game purchase information to the PurchaseHistory table.
	 */
	public void processBuyCart()
    {
            ArrayList<IItemInfo> gamesToBuy = new ArrayList<IItemInfo>();

            for (String title : currentUser.getCart()) {
                    IItemInfo item = getItemInfo(title);
                    gamesToBuy.add(item);
            }

            dbAdapter.updatePurchaseHistory(gamesToBuy, currentUser.getId());
            setUserCreddits(currentUser.getCreddits()-totalCartCost());
            emptyUserCart();
    }
	
	
	/**
	 * Returns a list of a user's owned games.
	 * @param userId ID of user whose games should be returned.
	 * @return String array of user's owned games Titles.
	 */
	public static String[] getUserOwnedGamesAsStrings(int userId) {
		List<Map<String, String>> ownedGames = pollOwnedGamesTable(userId);
		String[] answer = new String[ownedGames.size()];
		for(int k=0; k<answer.length; k++) {
			answer[k] = ownedGames.get(k).get(StoreDbConstants.ITEMNAME_FIELD);
		}
		return answer;
	}
	
	/**
	 * Gets a list of games that a given user owns.
	 * @param userId user whose games should be fetched.
	 * @return a list of integers that correspond to the game ID's of the user's owned games.
	 */
	public static List<Integer> getUserOwnedGames(int userId) {
		List<Integer> itemList = new ArrayList<Integer>();
		for(Map<String, String> m : pollOwnedGamesTable(userId)) {
			itemList.add(Integer.parseInt(m.get(StoreDbConstants.PURCHASED_ITEMS_ID_FIELD)));
		}
		return itemList;
	}

	
	/**
	 * Gets the ItemID and Title for each game in a user's owned games list
	 * from the database.
	 * @param userId id of user to get owned game info for.
	 * @return ItemID and Title for each owned game.
	 */
	private static List<Map<String, String>> pollOwnedGamesTable(int userId) {
		Map<String,String> condition = new HashMap<String, String>();
		condition.put("User_Id", Integer.toString(userId));
		return dbAdapter.getRows(StoreDbConstants.PURCHASE_HISTORY_TABLE, 
				condition, StoreDbConstants.PURCHASED_ITEMS_ID_FIELD,StoreDbConstants.ITEMNAME_FIELD);
	}
	
	
	/**
	 * 
	 * @return whether or not the current user has enough creddits to
	 * purchase the contents of their cart.
	 */
	public boolean notEnoughCreddits() {
		return totalCartCost() > currentUser.getCreddits();
	}
	
	/**
	 * 
	 * @return a sum of the prices of all the items in the current user's cart.
	 */
	public double totalCartCost() {
		double total = 0;
		for(String s : currentUser.getCart()) {
			IItemInfo i = getItemInfo(s);
			total+=Double.parseDouble(i.getPrice());
		}
		return total;
	}

	
	/**
	 * Checks whether a permission associated with the current user's
	 * account type is true or false. For example, a guest user's "purchase"
	 * permission will be false. 
	 * 
	 * @param privilegeType String that refers to the privilege type, e.g. "purchase"
	 * or "addCreddits"
	 * @return whether the privilege is true or false
	 */
	public boolean checkPrivileges(String privilegeType) {
		return privilegeManager.getPermission(currentUser, privilegeType);
	}


		
}
