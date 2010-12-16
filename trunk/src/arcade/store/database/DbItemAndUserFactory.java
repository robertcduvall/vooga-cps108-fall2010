package arcade.store.database;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;

import arcade.store.account.StoreUser;
import arcade.store.items.IItemInfo;
import arcade.store.items.ItemInfo;

/**
 * Instantiates ItemInfo objects and StoreUser objects from 
 * the database. Used to populate the store catalog and set
 * the current user in StoreModel.
 * 
 * @author Drew Sternesky, Marcus Molchany, Jimmy Mu
 *
 */

public class DbItemAndUserFactory {	
	
	private static StoreSqlAdapter dbAdapter = new StoreSqlAdapter();
	private static final String DELIMITER = ",";
	
	/**
	 * This method fetches a list of items in the store catalog and returns
	 * them as an array of IItemInfo objects.
	 * @return Map of String gameTitle to corresponding IItemInfo object. 
	 */
	public static Map<String, IItemInfo> getAllStoreItems() {
		List<Map<String, String>> list = dbAdapter.getAllRows(StoreDbConstants.ITEM_INFO_TABLE);
		HashMap<String, IItemInfo> answer = new HashMap<String, IItemInfo>();
		for(Map<String, String> m : list) {
			List<ImageIcon> images = getImages(m.get(StoreDbConstants.IMAGEPATHS_FIELD));
			int itemId = Integer.parseInt(m.get(StoreDbConstants.ITEM_ID_FIELD));
			String description = m.get(StoreDbConstants.DESCRIPTION_FIELD);
			String price = m.get(StoreDbConstants.PRICE_FIELD);
			String title = m.get(StoreDbConstants.TITLE_FIELD);
			String purchases = m.get(StoreDbConstants.PURCHASES_FIELD);
			String genre = m.get(StoreDbConstants.GENRE_FIELD);
			List<String> tags = parseTags(m.get(StoreDbConstants.TAGS_FIELD));
			IItemInfo item = new ItemInfo(itemId, description, price, title, purchases, images, genre, tags);
			answer.put(item.getTitle(), item);
		}
		return answer;
	}
	
	/**
	 * Parses comma-delimited string of tags into a list of tags.
	 * @param tags comma-delimited string of tags
	 * @return list of String tags
	 */
	private static List<String> parseTags(String tags) {
		String[] tagList = tags.split(DELIMITER);
		ArrayList<String> list = new ArrayList<String>();
		for(String s : tagList) list.add(s);
		return list;
	}
	
	
	/**
	 * Generates a list of ImageIcons given a comma-delimited string of 
	 * image paths
	 * @param imagePaths comma-delimited string of image paths
	 * @return list of all images associated with a game.
	 */
	private static List<ImageIcon> getImages(String imagePaths) {
		String[] images = imagePaths.split(DELIMITER);
		ArrayList<ImageIcon> icons = new ArrayList<ImageIcon>();
		for(String s : images) {
			icons.add(new ImageIcon(s));
		}
		return icons;
	}

	/**
	 * Instantiates the current user given a userId. If the userId
	 * associated with the current (lobby group's) Profile does not have
	 * an associated entry in the StoreAccounts table, it creates one with
	 * no owned games and no creddits.
	 * 
	 * @param userId unique user id
	 * @return StoreUser object representing the current user.
	 */
	public static StoreUser getUser(int userId) {
		List<Map<String, String>> list = dbAdapter.getRows(StoreDbConstants.STORE_USER_TABLE, StoreDbConstants.USER_FIELD, Integer.toString(userId));
		if(list.size() > 0) {
			Map<String, String> userMap = list.get(0);
			String id = userMap.get(StoreDbConstants.USER_FIELD);
			String type = userMap.get(StoreDbConstants.USER_TYPE_FIELD);
			double creddits =  Double.parseDouble(userMap.get(StoreDbConstants.CREDDIT_FIELD));
			String cart = userMap.get(StoreDbConstants.CART_FIELD);
			
			return getStoreUser(id, type, creddits, cart);
		
		}
		else {
			HashMap<String, String> newUser = new HashMap<String, String>();
			newUser.put(StoreDbConstants.USER_FIELD, Integer.toString(userId));
			newUser.put(StoreDbConstants.USER_TYPE_FIELD, "1");
			newUser.put(StoreDbConstants.CREDDIT_FIELD, "0.00");
			newUser.put(StoreDbConstants.CART_FIELD, "");
			dbAdapter.insert(StoreDbConstants.STORE_USER_TABLE, newUser);
			return getUser(userId);
		}
	}

	/**
	 * This method uses reflection to create the appropriate StoreUser
	 * subclass of StoreUser. 
	 * @param id
	 * @param type
	 * @param creddits
	 * @param cart
	 * @return
	 */
	private static StoreUser getStoreUser(String id, String type,
			double creddits, String cart) {
	
		String userClassName = StoreDbConstants.bundle.getString(type);
		try {
			Class userClass = Class.forName(userClassName);
			Class<?>[] paramTypes = {String.class, double.class, String.class};
			Constructor<StoreUser> constructor = userClass.getConstructor(paramTypes);
			
			StoreUser user = (StoreUser) constructor.newInstance(id, creddits, cart);
			return user;
		} 
		catch (Exception e) {
			
				throw DbExceptions.USER_TYPE_UNFOUND;
		}
	}
}
