package arcade.store.database;

import java.util.ResourceBundle;


/**
 * This class contains a list of constants related to the database.
 * Mostly, this holds references to different field names and table names
 * within the database. 
 * 
 * @author Drew Sternesky, Jimmy Mu, Marcus Molchany
 *
 */
public class StoreDbConstants {

	public static final ResourceBundle bundle = ResourceBundle.getBundle("arcade.store.database.DatabaseResources");
	
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
	public static final String DATE_FIELD = bundle.getString("dateField");
	public static final String ITEM_ID_FIELD = bundle.getString("itemIdField");
	public static final String USER_TYPE_FIELD = bundle.getString("userTypeField");
	public static final String PURCHASED_ITEMS_ID_FIELD = bundle.getString("purchasedItemsIdField");
	public static final String TAGS_FIELD = bundle.getString("tagsField");

}
