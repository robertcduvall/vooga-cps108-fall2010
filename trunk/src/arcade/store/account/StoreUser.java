package arcade.store.account;

import java.util.*;

import arcade.store.database.StoreDbConstants;
import arcade.store.database.StoreSqlAdapter;
import arcade.store.items.IItemInfo;

/**
 * StoreUser contains the information for a user using the store. This
 * information is the users id, name, total creddits, a list of the games they
 * own and a list of their shopping cart.
 * 
 * @author Drew Sternesky, Jimmy Mu, Marcus Molchany
 * 
 */
public class StoreUser {

	private final static String DEFAULT_NAME = "Guest User";
	private final static String SPLIT_CART_STRING = ",";
	private int id;
	private String name;
	private double creddits;
	private List<String> ownedGames;
	private List<String> cart;

	private static StoreSqlAdapter dbAdapter = new StoreSqlAdapter();

	/**
	 * Constructor for StoreUser. This sets the users names to the parameter
	 * name, their total creddits to the parameter creddits, their car to the
	 * parameter cart and their ownedGames to the parameter ownedGames.
	 * 
	 * @param name
	 * @param creddits
	 * @param cart
	 * @param ownedGames
	 */
	public StoreUser(String name, double creddits, String cart,
			List<String> ownedGames) {
		this.name = name;
		this.creddits = creddits;
		this.cart = new ArrayList<String>();
		this.ownedGames = new ArrayList<String>();
		String[] cartList = cart.split(SPLIT_CART_STRING);
		for (String s : cartList) {
			if (s.length() > 1)
				this.cart.add(s.trim());
		}
		this.ownedGames = ownedGames;
	}

	/**
	 * Default constructor for a StoreUser. This sets the users name to the
	 * DEFAULT_NAME constant, their total creddits to 0, their cart to an empty
	 * String and their ownedGames to a new ArrayList of Strings.
	 */
	public StoreUser() {
		this(DEFAULT_NAME, 0, "", new ArrayList<String>());
	}

	public String getName() {
		return name;
	}

	public String getCreddits() {
		return Double.toString(creddits);
	}

	public List<String> getCart() {
		return cart;
	}

	public List<String> getOwnedGames() {
		return ownedGames;
	}

	/**
	 * Takes the parameter newCreddits and sets the StoreUsers creddits to this
	 * value. Then it updates the database with the StoreUsers current creddits.
	 * 
	 * @param newCreddits
	 *            the new value for the StoreUser's creddits
	 */
	public void updateToCreddits(double newCreddits) {
		creddits = newCreddits;
		dbAdapter.updateCreddits(creddits, Integer.toString(id));
	}

	/**
	 * Takes the parameter amount and adds this value to the StoreUsers current
	 * creddits, passing this sum to the updateToCreddtis method.
	 * 
	 * @param amount
	 *            the value to add to the StoreUser's creddits
	 */
	public void addCreddits(double amount) {
		updateToCreddits(amount + creddits);
	}

	/**
	 * Takes the parameter titles and iterates over all of its IItemInfo
	 * objects, adding each one to the StoreUsers ownedGames List.
	 * 
	 * @param titles
	 *            the list of items to add to the StoreUser's ownedGames list
	 */
	public void addGames(List<IItemInfo> titles) {
		ArrayList<IItemInfo> games = new ArrayList<IItemInfo>();
		for (IItemInfo i : titles) {
			ownedGames.add(i.getTitle());
			games.add(i);
		}
		dbAdapter.updatePurchaseHistory(games, Integer.toString(id));
	}

	/**
	 * Makes the StoreUser's cart a new ArrayList of Strings and updates the
	 * database with this List.
	 */
	public void emptyCart() {
		cart = new ArrayList<String>();
		dbAdapter.updateList(cart, Integer.toString(id),
				StoreDbConstants.CART_FIELD);
	}

	/**
	 * Takes in the parameter title and removes that item from the StoreUser's
	 * cart.
	 * 
	 * @param title
	 *            the item to remove from the StoreUser's cart
	 */
	public void removeTitleFromCart(String title) {
		cart.remove(title);
	}

	/**
	 * Takes in the parameter name and adds this item to the StoreUser's cart.
	 * 
	 * @param name
	 *            the item to add to the StoreUser's cart
	 */
	public void addToCart(String name) {
		cart.add(name);
	}

	/**
	 * Updates the database with the StoreUser's current cart.
	 */
	
	//TODO: put dbAdapter in Store Model
	public void saveCart() {
		dbAdapter.updateList(cart, Integer.toString(id),
				StoreDbConstants.CART_FIELD);
	}

	public String getId() {
		return Integer.toString(id);
	}

}
