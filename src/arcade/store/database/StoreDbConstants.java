package arcade.store.database;

import java.util.ResourceBundle;

public class StoreDbConstants {

	private static final ResourceBundle bundle = ResourceBundle.getBundle("arcade.store.database.DatabaseResources");
	
	public static final String CREDDIT_FIELD = bundle.getString("credditField");
	public static final String CART_FIELD = bundle.getString("cartField");
	public static final String USER_FIELD = bundle.getString("userField");
	public static final String OWNED_GAMES_FIELD = bundle.getString("ownedGamesField");
	public static final String STORE_USER_TABLE = bundle.getString("storeUserTable");
	public static final String PURCHASE_HISTORY_TABLE = bundle.getString("purchaseHistoryTable");
	public static final String ITEM_INFO_TABLE = bundle.getString("itemInfoTable");
	public static final String DESCRIPTION_FIELD = bundle.getString("descriptionField");
	public static final String PRICE_FIELD = bundle.getString("priceField");
	public static final String TITLE_FIELD = bundle.getString("titleField");
	public static final String PURCHASES_FIELD = bundle.getString("purchasesField");
	public static final String GENRE_FIELD = bundle.getString("genreField");
	public static final String IMAGEPATHS_FIELD = bundle.getString("imagepathsField");
	public static final String ITEMNAME_FIELD = bundle.getString("itemnameField");
	public static final String PURCHASE_HISTORY_USERID_FIELD = bundle.getString("purchaseHistoryUserIdField");
	
}
