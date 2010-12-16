package arcade.store.database;

import arcade.store.items.IItemInfo;
import arcade.util.database.Constants;
import arcade.util.database.MySqlAdapter;
import java.util.*;

/**
 * This is a Sql adapter that extends the adapter provided in the util
 * package. It encapsulates some common database operations that pertain
 * specifically to the Store, such as updateCreddits, updatePurchaseHistory,
 * and updateList.
 * 
 * @author Drew Sternesky, Jimmy Mu, Marcus Molchany
 *
 */
public class StoreSqlAdapter extends MySqlAdapter {
	
	public StoreSqlAdapter() {
		super(Constants.HOST, Constants.DBNAME, Constants.USER, Constants.PASSWORD);
	}
	
	/**
	 * Gets all rows in a table.
	 * @param tableName name of table to get all rows of.
	 * @return list of mappings from column names to values.
	 */
	public List<Map<String, String>> getAllRows(String tableName) {
		return super.getColumns(tableName);
	}

	/**
	 * Sets a new value for a user's creddits in the store account table.
	 * @param newCreddits new creddit balance for the user.
	 * @param userId unique id of current user.
	 * @return whether database operation succeeded.
	 */
	public boolean updateCreddits(double newCreddits, String userId) {
		Map<String, String> row = new HashMap<String, String>();
		row.put(StoreDbConstants.CREDDIT_FIELD, Double.toString(newCreddits));
		return super.update(StoreDbConstants.STORE_USER_TABLE, StoreDbConstants.USER_FIELD, userId, row);
	}
	
	/**
	 * Used to update a list within the store accounts table, such as owned games or cart.
	 * @param newInfo List of Strings that is the new list to be stored.
	 * @param userId unique id of user for whom the list will be updated.
	 * @param listField the field in the database where the new list should be inserted.
	 * @return
	 */
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
	
	/**
	 * Used to conveniently add information to the purchase history table. Called
	 * when a user makes a purchase.
	 * @param games list of ItemInfo objects representing the purchased games
	 * @param userId unique id of user who purchased the games
	 * @return whether or not database operation succeeded
	 */
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
