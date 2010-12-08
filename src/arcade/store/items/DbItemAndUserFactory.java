package arcade.store.items;

import java.util.*;

import javax.swing.ImageIcon;

import arcade.store.StoreSqlAdapter;
import arcade.store.account.StoreUser;
import arcade.util.database.MySqlAdapter;

public class DbItemAndUserFactory {
	
	private static final String host = "voogaarcade.db.7093929.hostedresource.com";
	private static final String dbName = "voogaarcade";
	private static final String user = dbName;
	private static final String pass = "Vooga108";
	
	
	private static StoreSqlAdapter dbAdapter = new StoreSqlAdapter(host, dbName, user, pass);
	
	public static Map<String, IItemInfo> getAllItems(String table) {
		List<Map<String, String>> list = dbAdapter.getAllRows(table);
		HashMap<String, IItemInfo> answer = new HashMap<String, IItemInfo>();
		for(Map<String, String> m : list) {
			String[] images = m.get("imagepaths").split(",");
			ArrayList<ImageIcon> icons = new ArrayList<ImageIcon>();
			for(String s : images) {
				icons.add(new ImageIcon(s));
			}
			IItemInfo item = new ItemInfo(m.get("description"), m.get("price"), m.get("title"),
					m.get("purchases"), m.get("rating"), icons, m.get("genre"));
			answer.put(item.getTitle(), item);
		}
		return answer;
	}


	public static StoreUser getUser(String table, String name) {
		List<Map<String, String>> list = dbAdapter.getRows(table, "username", name);
		if(list!=null) {
			Map<String, String> userMap = list.get(0);
			return new StoreUser(userMap.get("username"), Double.parseDouble(userMap.get("creddits")),
					Integer.parseInt(userMap.get("time_creddits")), userMap.get("cart"), userMap.get("owned_games"));
		}
		else {
			HashMap<String, String> newUser = new HashMap<String, String>();
			newUser.put("username", name);
			newUser.put("creddits", "0.00");
			newUser.put("time_creddits", "0");
			newUser.put("cart", "");
			newUser.put("owned_games", "");
			dbAdapter.insert(table, newUser);
			return getUser(table, name);
		}
	}
}
