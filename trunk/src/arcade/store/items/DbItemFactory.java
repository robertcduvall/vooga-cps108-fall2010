package arcade.store.items;

import java.util.*;

import javax.swing.ImageIcon;

import arcade.lobby.model.MySqlAdapter;
import arcade.store.StoreSqlAdapter;

public class DbItemFactory {
	
	private static final String host = "voogaarcade.db.7093929.hostedresource.com";
	private static final String dbName = "voogaarcade";
	private static final String user = dbName;
	private static final String pass = "Vooga108";
	
	
	private static StoreSqlAdapter dbAdapter = new StoreSqlAdapter(host, dbName, user, pass);
	
	public static Map<String, IItemInfo> getAllItems(String table) {
		List<Map<String, String>> list = dbAdapter.getAllRows(table);
		System.out.println(list.size());
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

}
