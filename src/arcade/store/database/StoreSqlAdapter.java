package arcade.store.database;

import arcade.store.items.IItemInfo;
import arcade.util.database.Constants;
import arcade.util.database.MySqlAdapter;
import java.util.*;


public class StoreSqlAdapter extends MySqlAdapter {
	
	public StoreSqlAdapter() {
		super(Constants.HOST, Constants.DBNAME, Constants.USER, Constants.PASSWORD);
	}
	
	public List<Map<String, String>> getAllRows(String tableName) {
		return super.getRows("SELECT * FROM "+tableName);
	}

	public boolean updateCreddits(double newCreddits, String userId) {
		Map<String, String> row = new HashMap<String, String>();
		row.put(StoreDbConstants.CREDDIT_FIELD, Double.toString(newCreddits));
		return super.update(StoreDbConstants.STORE_USER_TABLE, StoreDbConstants.USER_FIELD, userId, row);
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
		return super.update(StoreDbConstants.STORE_USER_TABLE, StoreDbConstants.USER_FIELD, userId, row);
	}
	
	public boolean updatePurchaseHistory(List<IItemInfo> games, String userId) {
		for(IItemInfo i : games) {
			Map<String, String> row = new HashMap<String, String>();
			row.put("Item_Id", Integer.toString(i.getId()));
			row.put("User_Id", userId);
			row.put("ItemName", i.getTitle());
		    java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
			row.put("Date", sqlDate.toString());
			row.put("Price", i.getPrice());
			super.insert(StoreDbConstants.PURCHASE_HISTORY_TABLE, row);
		}
		return true;
	}
	
}
