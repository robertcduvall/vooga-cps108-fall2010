package arcade.store.items;

import java.util.*;

import javax.swing.ImageIcon;

import arcade.store.StoreSqlAdapter;
import arcade.store.account.StoreUser;
import arcade.util.database.MySqlAdapter;

public class DbItemAndUserFactory {
	
	private static final String usernameTable = "StoreAccounts";
	
	private static StoreSqlAdapter dbAdapter = new StoreSqlAdapter();
	
	public static Map<String, IItemInfo> getAllItems(String table) {
		List<Map<String, String>> list = dbAdapter.getAllRows(table);
		HashMap<String, IItemInfo> answer = new HashMap<String, IItemInfo>();
		for(Map<String, String> m : list) {
			String[] images = m.get("ImagePaths").split(",");
			ArrayList<ImageIcon> icons = new ArrayList<ImageIcon>();
			for(String s : images) {
				icons.add(new ImageIcon(s));
			}
			IItemInfo item = new ItemInfo(m.get("Description"), m.get("Price"), m.get("Title"),
					m.get("Purchases"), icons, m.get("Genre"));
			answer.put(item.getTitle(), item);
		}
		return answer;
	}


	public static StoreUser getUser(int userId) {
		List<Map<String, String>> list = dbAdapter.getRows(usernameTable, "Id", Integer.toString(userId));
		if(list!=null) {
			Map<String, String> userMap = list.get(0);
			return new StoreUser(userMap.get("User_Id"), Double.parseDouble(userMap.get("Creddits")),
				userMap.get("Cart"), userMap.get("GamesOwned"));
		}
		else {
			HashMap<String, String> newUser = new HashMap<String, String>();
			newUser.put("User_Id", Integer.toString(userId));
			newUser.put("Creddits", "0.00");
			newUser.put("Cart", "");
			newUser.put("GamesOwned", "");
			dbAdapter.insert(usernameTable, newUser);
			return getUser(userId);
		}
	}
}
