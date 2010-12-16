package arcade.store.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;

import arcade.store.account.StoreUser;
import arcade.store.items.IItemInfo;
import arcade.store.items.ItemInfo;

public class DbItemAndUserFactory {	
	
	private static StoreSqlAdapter dbAdapter = new StoreSqlAdapter();
	
	public static Map<String, IItemInfo> getAllStoreItems() {
		List<Map<String, String>> list = dbAdapter.getAllRows(StoreDbConstants.ITEM_INFO_TABLE);
		HashMap<String, IItemInfo> answer = new HashMap<String, IItemInfo>();
		for(Map<String, String> m : list) {
			String[] images = m.get(StoreDbConstants.IMAGEPATHS_FIELD).split(",");
			ArrayList<ImageIcon> icons = new ArrayList<ImageIcon>();
			for(String s : images) {
				icons.add(new ImageIcon(s));
			}
			IItemInfo item = new ItemInfo(Integer.parseInt(m.get(StoreDbConstants.ITEM_ID_FIELD)), m.get(StoreDbConstants.DESCRIPTION_FIELD), m.get(StoreDbConstants.PRICE_FIELD), m.get(StoreDbConstants.TITLE_FIELD),
					m.get(StoreDbConstants.PURCHASES_FIELD), icons, m.get(StoreDbConstants.GENRE_FIELD));
			answer.put(item.getTitle(), item);
		}
		return answer;
	}


	public static StoreUser getUser(int userId) {
		List<Map<String, String>> list = dbAdapter.getRows(StoreDbConstants.STORE_USER_TABLE, StoreDbConstants.USER_FIELD, Integer.toString(userId));
		Map<String,String> conditional = new HashMap<String,String>();
		conditional.put(StoreDbConstants.PURCHASE_HISTORY_USERID_FIELD, Integer.toString(userId));
		List<Map<String, String>> ownedGames = dbAdapter.getRows(StoreDbConstants.PURCHASE_HISTORY_TABLE,conditional,StoreDbConstants.ITEMNAME_FIELD);
		if(list!=null) {
			Map<String, String> userMap = list.get(0);
			return new StoreUser(userMap.get(StoreDbConstants.USER_FIELD),userMap.get(StoreDbConstants.USER_TYPE_FIELD),  Double.parseDouble(userMap.get(StoreDbConstants.CREDDIT_FIELD)),
				userMap.get(StoreDbConstants.CART_FIELD));
		}
		else {
			HashMap<String, String> newUser = new HashMap<String, String>();
			newUser.put(StoreDbConstants.USER_FIELD, Integer.toString(userId));
			newUser.put(StoreDbConstants.USER_TYPE_FIELD, "2");
			newUser.put(StoreDbConstants.CREDDIT_FIELD, "0.00");
			newUser.put(StoreDbConstants.CART_FIELD, "");
			dbAdapter.insert(StoreDbConstants.STORE_USER_TABLE, newUser);
			return getUser(userId);
		}
	}
}
