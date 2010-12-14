package arcade.store;

import arcade.store.items.IItemInfo;
import arcade.util.database.MySqlAdapter;
import java.util.*;


public class StoreSqlAdapter extends MySqlAdapter {
	

	public static final String HOST = "voogaarcade.db.7093929.hostedresource.com";
	public static final String DBNAME = "voogaarcade";
	public static final String USER = DBNAME;
	public static final String PASS = "Vooga108";
	public static final String CREDDIT_FIELD = "Creddits";
	public static final String CART_FIELD = "Cart";
	public static final String USER_FIELD = "Id";
	public static final String OWNED_GAMES_FIELD = "GamesOwned";
	public static final String STORE_USER_TABLE = "StoreAccounts";
	public static final String PURCHASE_HISTORY_TABLE = "PurchaseHistory";
	public static final String ITEM_INFO_TABLE = "GameInfo";

	
	public StoreSqlAdapter() {
		super(HOST, DBNAME, USER, PASS);
	}
	
	public List<Map<String, String>> getAllRows(String tableName) {
		return super.getRows("SELECT * FROM "+tableName);
	}

	public boolean updateCreddits(double newCreddits, String userId) {
		Map<String, String> row = new HashMap<String, String>();
		row.put(CREDDIT_FIELD, Double.toString(newCreddits));
		return super.update(STORE_USER_TABLE, USER_FIELD, userId, row);
	}
	
	
	public boolean updateList(List<String> newCart, String userId, String listField) {
		Map<String, String> row = new HashMap<String, String>();
		StringBuilder builder = new StringBuilder();
		for(String s : newCart) {
			builder.append(s);
			builder.append(",");
		}
		String newCartRow = builder.toString();
		row.put(listField, newCartRow);
		return super.update(STORE_USER_TABLE, USER_FIELD, userId, row);
	}
	
	public boolean updatePurchaseHistory(List<IItemInfo> games, String userId) {
		for(IItemInfo i : games) {
			Map<String, String> row = new HashMap<String, String>();
			row.put("User_Id", userId);
			row.put("ItemName", i.getTitle());
		    java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
			row.put("Date", sqlDate.toString());
			row.put("Price", i.getPrice());
			super.insert(PURCHASE_HISTORY_TABLE, row);
		}
		return true;
	}
	
}
